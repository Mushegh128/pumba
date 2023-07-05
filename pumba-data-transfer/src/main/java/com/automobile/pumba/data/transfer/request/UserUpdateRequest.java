package com.automobile.pumba.data.transfer.request;

import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.model.UserRoleGroupB;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequest {

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private UserRoleGroupB role;
    private Set<UserPermission> permissions;
    @NotNull
    private Boolean isBlocked;
}
