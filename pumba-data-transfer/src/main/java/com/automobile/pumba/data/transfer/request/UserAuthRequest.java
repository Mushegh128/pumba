package com.automobile.pumba.data.transfer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAuthRequest {

    @NotBlank(message = "Email is required")
    @Size(min = 2, max = 50, message = "erkar e shat")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
