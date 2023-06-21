package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.User;
import com.automobile.pumba.data.transfer.request.UserProfileDetailsRequest;
import com.automobile.pumba.data.transfer.response.UserProfileDetailsResponse;
import com.automobile.pumba.data.transfer.response.UserResponse;

import java.util.List;

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
    User findByEmail(String email);

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return the found user, or null if it doesn't exist
     */
    User findById(long id);

    User getCurrentUser();

    UserProfileDetailsResponse changeProfileDetailsRequest(UserProfileDetailsRequest userProfileDetailsRequest);

    List<UserResponse> findAll();
}
