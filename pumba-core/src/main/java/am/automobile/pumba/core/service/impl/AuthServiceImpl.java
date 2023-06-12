package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.AuthenticatedException;
import am.automobile.pumba.core.exception.UserEmailConflictException;
import am.automobile.pumba.core.mapper.UserMapper;
import am.automobile.pumba.core.mapper.UserRegistrationMapper;
import am.automobile.pumba.core.repository.UserRepository;
import am.automobile.pumba.core.service.AuthService;
import am.automobile.pumba.core.util.JwtTokenUtil;
import com.automobile.pumba.data.transfer.request.UserAuthRequest;
import com.automobile.pumba.data.transfer.request.UserRegistrationRequest;
import com.automobile.pumba.data.transfer.response.UserAuthResponse;
import com.automobile.pumba.data.transfer.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

        if (optionalUser.isEmpty()
                || !passwordEncoder.matches(userAuthRequest.getPassword(), optionalUser.get().getPassword())) {
            throw new AuthenticatedException(userAuthRequest.getUsername() + ": Provided wrong credentials for authentication");
        }
        log.info("Succeed get user by email: {}", userAuthRequest.getUsername());
        return UserAuthResponse.builder()
                .token(jwtTokenUtil.generateToken(userAuthRequest.getUsername()))
                .user(userMapper.toResponse(optionalUser.get()))
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
        userRepository.save(user);
        log.info("Succeed registered user by email: {}", userRequest.getEmail());
        return userRegistrationMapper.toResponse(user);
    }
}
