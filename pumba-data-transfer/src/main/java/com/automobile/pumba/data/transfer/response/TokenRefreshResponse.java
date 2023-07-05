package com.automobile.pumba.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenRefreshResponse {

    private String accessToken;
    private String refreshToken;
}
