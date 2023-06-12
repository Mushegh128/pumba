package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.service.AuthService;
import com.automobile.pumba.data.transfer.request.UserAuthRequest;
import com.automobile.pumba.data.transfer.request.UserRegistrationRequest;
import com.automobile.pumba.data.transfer.response.UserAuthResponse;
import com.automobile.pumba.data.transfer.response.UserRegistrationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthEndpoint {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> userAuth(@Valid @RequestBody UserAuthRequest userAuthRequest) {
        UserAuthResponse auth = authService.auth(userAuthRequest);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserRegistrationResponse register = authService.registration(userRegistrationRequest);
        return ResponseEntity.ok(register);
    }
}
