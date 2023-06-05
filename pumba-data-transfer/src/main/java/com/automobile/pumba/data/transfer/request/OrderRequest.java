package com.automobile.pumba.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String message;
    private String phoneNumber;
}
