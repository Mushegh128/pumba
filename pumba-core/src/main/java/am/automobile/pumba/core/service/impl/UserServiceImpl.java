package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.UserInfoUpdateMapper;
import am.automobile.pumba.core.repository.UserRepository;
import am.automobile.pumba.core.service.UserService;
import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.model.UserRole;
import com.automobile.pumba.data.transfer.request.UserInfoUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserService userService;
    private final UserInfoUpdateMapper userInfoUpdateMapper;

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
        User user = userService.findById(userId);

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
        User user = userService.findById(userId);

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
        User user = userService.findById(userId);

        Set<UserPermission> permissions = new HashSet<>(user.getPermissions());
        permissions.addAll(userPermissions);
        user.setPermissions(permissions);
        userRepository.save(user);
    }

    @Override
    public void changeUserRole(long userId, UserRole newUserRole) {
        User user = userService.findById(userId);
        if (newUserRole != null && !user.getRole().equals(newUserRole)) {
            user.setRole(newUserRole);
            userRepository.save(user);
        }
    }

    @Override
    public void blockUser(long userId) {
        User user = userService.findById(userId);
        if (!user.isBlocked()) {
            user.setBlocked(true);
            user.setBlockedAt(LocalDate.now());
            userRepository.save(user);
        }
    }

    @Override
    public void unblock(long userId) {
        User user = userService.findById(userId);
        if (user.isBlocked()) {
            user.setBlocked(false);
            userRepository.save(user);
        }
    }

    @Override
    public void updateUserInfo(long userId, UserInfoUpdateRequest userInfoUpdateRequest) {
        User user = userService.findById(userId);
        userInfoUpdateMapper.updateUserFromDto(userInfoUpdateRequest, user);
    }
}
