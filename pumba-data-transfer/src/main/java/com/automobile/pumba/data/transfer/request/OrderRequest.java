package com.automobile.pumba.data.transfer.request;

import jakarta.validation.constraints.Min;
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
    private String companyName;
    private String country;
    private String streetAddress;
    private String postcodeZip;
    private String city;
    private String province;
    private String phoneNumber;
    private String email;
    private String message;
    @Min(1)
    private Long car;
}
