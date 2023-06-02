package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.UserNotFoundException;
import am.automobile.pumba.core.repository.UserRepository;
import am.automobile.pumba.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByEmail(final String email) {
        log.info("Find by email user");
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UserNotFoundException("User with email: " + email + " NOT FOUND");
        });
    }
}
