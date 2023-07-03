package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.User;
import com.automobile.pumba.data.transfer.request.PasswordChangeRequest;
import com.automobile.pumba.data.transfer.request.TokenRefreshRequest;
import com.automobile.pumba.data.transfer.request.UserAuthRequest;
import com.automobile.pumba.data.transfer.request.UserRegistrationRequest;
import com.automobile.pumba.data.transfer.response.TokenRefreshResponse;
import com.automobile.pumba.data.transfer.response.UserAuthResponse;
import com.automobile.pumba.data.transfer.response.UserRegistrationResponse;

/**
 * A service interface for authenticating and registering users.
 */
public interface AuthService {

    /**
     * Authenticates a user based on the provided authentication request and returns a response containing the user's
     * authentication token and user details.
     *
     * @param userAuthRequest the authentication request containing the user's email and password
     * @return a UserAuthResponse object containing the user's authentication token and user details
     */
    UserAuthResponse auth(UserAuthRequest userAuthRequest);

    /**
     * Registers a new user based on the provided registration request and returns a response containing the newly
     * registered user's details.
     *
     * @param userRegistrationRequest the registration request containing the new user's details
     * @return a UserRegistrationResponse object containing the newly registered user's details
     */
    UserRegistrationResponse registration(UserRegistrationRequest userRegistrationRequest);

    void changePassword(PasswordChangeRequest passwordChangeRequest, User currentUser);

    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefresh);
}
