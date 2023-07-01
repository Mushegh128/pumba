package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.AuthenticatedException;
import am.automobile.pumba.core.exception.UserEmailConflictException;
import am.automobile.pumba.core.mapper.UserMapper;
import am.automobile.pumba.core.mapper.UserRegistrationMapper;
import am.automobile.pumba.core.repository.UserRepository;
import am.automobile.pumba.core.service.AuthService;
import am.automobile.pumba.core.util.JwtTokenUtil;
import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.model.UserRole;
import com.automobile.pumba.data.transfer.request.PasswordChangeRequest;
import com.automobile.pumba.data.transfer.request.UserAuthRequest;
import com.automobile.pumba.data.transfer.request.UserRegistrationRequest;
import com.automobile.pumba.data.transfer.response.UserAuthResponse;
import com.automobile.pumba.data.transfer.response.UserRegistrationResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRegistrationMapper userRegistrationMapper;
    private final UserMapper userMapper;

    @Override
    public UserAuthResponse auth(UserAuthRequest userAuthRequest) {
        log.info("Request from user {} to get authenticated", userAuthRequest.getUsername());
        Optional<User> optionalUser = userRepository.findByEmail(userAuthRequest.getUsername());

        if (optionalUser.isEmpty()) {
            throw new AuthenticatedException(userAuthRequest.getUsername() + ": Incorrect credentials provided for authentication");
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(userAuthRequest.getPassword(), user.getPassword()) || !user.isEnabled()) {
            throw new AuthenticatedException(userAuthRequest.getUsername() + ": Incorrect credentials provided for authentication");
        }

        log.info("Successfully retrieved user by email: {}", userAuthRequest.getUsername());

        String token = jwtTokenUtil.generateToken(userAuthRequest.getUsername());
//        String refreshToken = jwtTokenUtil.refreshToken(userAuthRequest.getUsername());

        return UserAuthResponse.builder()
                .token(token)
//                .refreshToken(refreshToken)
                .user(userMapper.toResponse(user))
                .build();
    }

    @Override
    public UserRegistrationResponse registration(UserRegistrationRequest userRequest) {
        log.info("Request from user {} to registration", userRequest.getEmail());
        Optional<User> byEmail = userRepository.findByEmail(userRequest.getEmail());
        if (byEmail.isPresent()) {
            throw new UserEmailConflictException("User with email: " + userRequest.getEmail() + " already exists");
        }
        User user = userRegistrationMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEnabled(true);
        user.setDeleted(false);
        user.setPermissions(userRequest.getPermissions());
        userRepository.save(user);
        log.info("Succeed registered user by email: {}", userRequest.getEmail());
        return userRegistrationMapper.toResponse(user);
    }

    @Override
    public void changePassword(PasswordChangeRequest passwordChangeRequest, User currentUser) {
        log.info("Request to change password for user: {}", currentUser.getEmail());

        if (!passwordEncoder.matches(passwordChangeRequest.getCurrentPassword(), currentUser.getPassword())) {
            throw new AuthenticatedException("Incorrect current password");
        }

        String newPassword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
        currentUser.setPassword(newPassword);
        userRepository.save(currentUser);

        log.info("Password changed successfully for user: {}", currentUser.getEmail());
    }

    @Override
    public UserAuthResponse refreshToken(String token) {
        return null;
    }

    @PostConstruct
    private void createInitialAdminUser() {
        String adminEmail = "admin@gmail.com";
        Optional<User> userOptional = userRepository.findByEmail(adminEmail);
        if (userOptional.isEmpty()) {
            UserRegistrationRequest userRequest = UserRegistrationRequest.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .permissions(EnumSet.allOf(UserPermission.class))
                    .email(adminEmail)
                    .phone("+37466666666")
                    .build();
            User user = userRegistrationMapper.toEntity(userRequest);
            user.setDeleted(false);
            user.setEnabled(true);
            user.setPassword("$2a$10$8NXc6KhYOE4gxnIemF7uZuCD887BvhMPoEmZmNjH7myA8ent19nJS");
            user.setRole(UserRole.ADMIN);
            userRepository.save(user);
        }
    }
}
