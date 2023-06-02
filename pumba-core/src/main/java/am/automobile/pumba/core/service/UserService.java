package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.User;

/**
 * The UserService interface provides methods for managing and retrieving users.
 */
public interface UserService {

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find
     * @return the found user, or null if it doesn't exist
     */
    User findByEmail(final String email);
}
