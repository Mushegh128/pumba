package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.service.AuthService;
import com.example.pumba.data.transfer.request.UserAuthRequest;
import com.example.pumba.data.transfer.request.UserRegistrationRequest;
import com.example.pumba.data.transfer.response.UserAuthResponse;
import com.example.pumba.data.transfer.response.UserRegistrationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthEndpoint {

    private final AuthService authService;

    @GetMapping("/login")
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
