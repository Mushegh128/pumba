package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.service.AuthService;
import am.automobile.pumba.core.service.UserService;
import com.automobile.pumba.data.transfer.request.PasswordChangeRequest;
import com.automobile.pumba.data.transfer.request.UserAuthRequest;
import com.automobile.pumba.data.transfer.request.UserRegistrationRequest;
import com.automobile.pumba.data.transfer.response.UserAuthResponse;
import com.automobile.pumba.data.transfer.response.UserRegistrationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/auth")
public class AuthEndpoint {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> userAuth(@Valid @RequestBody UserAuthRequest userAuthRequest) {
        UserAuthResponse auth = authService.auth(userAuthRequest);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserRegistrationResponse register = authService.registration(userRegistrationRequest);
        return ResponseEntity.ok(register);
    }

    // todo
    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest) {
        User currentUser = userService.getCurrentUser();
        authService.changePassword(passwordChangeRequest, currentUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/validate")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> validateToken() {
        return ResponseEntity.ok().build();
    }
}
