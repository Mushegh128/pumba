package am.automobile.pumba.core.security;

import am.automobile.pumba.core.entity.User;
import com.example.pumba.data.transfer.model.UserPermission;
import com.example.pumba.data.transfer.model.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A custom user class that extends Spring Security's User class. This class contains additional information about the
 * current user that is not included in the standard User class, such as the User object itself and the user's ID.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private static final String ROLE_PREFIX = "ROLE_";

    private final User user;

    /**
     * Constructs a new CurrentUser object based on the specified User object.
     *
     * @param user the User object to use for constructing the CurrentUser
     */
    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), getAuthorities(user.getPermissions(), user.getRole()));
        this.user = user;
    }

    /**
     * Returns the User object associated with the current user.
     *
     * @return the User object associated with the current user
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the ID of the current user.
     *
     * @return the ID of the current user
     */
    public long getId() {
        return user.getId();
    }

    private static List<SimpleGrantedAuthority> getAuthorities(Set<UserPermission> permissions, UserRole role) {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        permissions.forEach(authority ->
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.name().toLowerCase())));
        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.name()));
        return grantedAuthorities;
    }
}
