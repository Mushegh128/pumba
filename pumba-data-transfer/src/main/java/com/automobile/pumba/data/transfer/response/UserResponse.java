package com.automobile.pumba.data.transfer.response;

import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private Set<UserPermission> permissions;
}
