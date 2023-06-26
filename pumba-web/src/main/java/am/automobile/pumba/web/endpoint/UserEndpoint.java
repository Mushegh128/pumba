package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.mapper.UserMapper;
import am.automobile.pumba.core.service.UserService;
import com.automobile.pumba.data.transfer.request.UserProfileDetailsRequest;
import com.automobile.pumba.data.transfer.request.UserUpdateRequest;
import com.automobile.pumba.data.transfer.response.UserProfileDetailsResponse;
import com.automobile.pumba.data.transfer.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/user")
public class UserEndpoint {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/current-user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/profile-details-change")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserProfileDetailsResponse> profileDetailsChange(@Valid @RequestBody UserProfileDetailsRequest userProfileDetailsRequest) {
        return ResponseEntity.ok(userService.changeProfileDetailsRequest(userProfileDetailsRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUserById(@PathVariable long id, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userUpdateRequest));
    }
}
