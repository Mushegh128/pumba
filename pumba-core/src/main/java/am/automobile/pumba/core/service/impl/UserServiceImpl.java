package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.exception.UserNotFoundException;
import am.automobile.pumba.core.mapper.UserInfoUpdateMapper;
import am.automobile.pumba.core.mapper.UserMapper;
import am.automobile.pumba.core.repository.UserRepository;
import am.automobile.pumba.core.service.UserService;
import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.model.UserRole;
import com.automobile.pumba.data.transfer.request.UserInfoUpdateRequest;
import com.automobile.pumba.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInfoUpdateMapper userInfoUpdateMapper;
    private final UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        log.info("Find by email user");
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new EntityNotFoundException("User with email: " + email + " NOT FOUND");
        });
    }

    @Override
    public User findById(long id) {
        log.info("Find by id user");
        return userRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("User with id: " + id + " NOT FOUND");
        });
    }

    @Override
    public void addUserPermission(long userId, UserPermission userPermission) {
        User user = findById(userId);

        Set<UserPermission> permissions = user.getPermissions();
        if (permissions == null) {
            permissions = new HashSet<>();
        }
        permissions.add(userPermission);
        user.setPermissions(permissions);
        userRepository.save(user);
    }

    @Override
    public void addUserPermissions(long userId, List<UserPermission> userPermissions) {
        User user = findById(userId);

        Set<UserPermission> permissions = user.getPermissions();
        if (permissions == null) {
            permissions = new HashSet<>();
        }
        permissions.addAll(userPermissions);
        user.setPermissions(permissions);
        userRepository.save(user);
    }

    @Override
    public void updateUserPermissions(long userId, List<UserPermission> userPermissions) {
        User user = findById(userId);

        Set<UserPermission> permissions = new HashSet<>(user.getPermissions());
        permissions.addAll(userPermissions);
        user.setPermissions(permissions);
        userRepository.save(user);
    }

    @Override
    public void changeUserRole(long userId, UserRole newUserRole) {
        User user = findById(userId);
        if (newUserRole != null && !user.getRole().equals(newUserRole)) {
            user.setRole(newUserRole);
            userRepository.save(user);
        }
    }

    @Override
    public void blockUser(long userId) {
        User user = findById(userId);
        if (!user.isBlocked()) {
            user.setBlocked(true);
            user.setBlockedAt(LocalDate.now());
            userRepository.save(user);
        }
    }

    @Override
    public void unblock(long userId) {
        User user = findById(userId);
        if (user.isBlocked()) {
            user.setBlocked(false);
            userRepository.save(user);
        }
    }

    @Override
    public void updateUserInfo(long userId, UserInfoUpdateRequest userInfoUpdateRequest) {
        User user = findById(userId);
        userInfoUpdateMapper.updateUserFromDto(userInfoUpdateRequest, user);
    }

    @Override
    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            return userMapper.toResponse(user);
        }
        throw new UserNotFoundException("Currently authenticated user not found");
    }
}
