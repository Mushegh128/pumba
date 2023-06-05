package com.automobile.pumba.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String message;
    private String phoneNumber;
}
