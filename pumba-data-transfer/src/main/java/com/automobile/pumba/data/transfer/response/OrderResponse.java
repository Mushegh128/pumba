package com.automobile.pumba.data.transfer.response;

import com.automobile.pumba.data.transfer.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {

    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String streetAddress;
    private String address;
    private String postcodeZip;
    private String city;
    private String province;
    private String phoneNumber;
    private String email;
    private String message;
    private LocalDateTime createAt;
    private OrderStatus orderStatus;
    private UserResponse manager;
    private CarResponse car;
}
