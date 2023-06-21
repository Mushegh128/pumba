package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.UserMapper;
import am.automobile.pumba.core.mapper.UserProfileDetailsUpdateMapper;
import am.automobile.pumba.core.repository.UserRepository;
import am.automobile.pumba.core.service.UserService;
import com.automobile.pumba.data.transfer.request.UserProfileDetailsRequest;
import com.automobile.pumba.data.transfer.response.UserProfileDetailsResponse;
import com.automobile.pumba.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileDetailsUpdateMapper userProfileDetailsUpdateMapper;
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
        log.info("Find User by ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found"));
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return findByEmail(email);
    }

    @Override
    public UserProfileDetailsResponse changeProfileDetailsRequest(UserProfileDetailsRequest userProfileDetailsRequest) {
        User user = getCurrentUser();
        userProfileDetailsUpdateMapper.updateUserFromDto(userProfileDetailsRequest, user);
        User save = userRepository.save(user);
        return userProfileDetailsUpdateMapper.toResponse(save);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
}
