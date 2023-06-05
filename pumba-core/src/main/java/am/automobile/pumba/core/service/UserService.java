package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.User;
import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.model.UserRole;
import com.automobile.pumba.data.transfer.request.UserInfoUpdateRequest;

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

    void addUserPermission(long userId, UserPermission userPermission);

    void addUserPermissions(long userId, List<UserPermission> userPermissions);

    void updateUserPermissions(long userId, List<UserPermission> userPermissions);

    void changeUserRole(long userId, UserRole newUserRole);

    void blockUser(long userId);

    void unblock(long userId);

    void updateUserInfo(long userId, UserInfoUpdateRequest userInfoUpdateRequest);

}
