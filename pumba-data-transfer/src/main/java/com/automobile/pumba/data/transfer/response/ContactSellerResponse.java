package com.automobile.pumba.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactSellerResponse {

    private String name;
    private String phone;
    private String email;
    private String message;
    private LocalDateTime createAt;
    private CarResponse car;
}
