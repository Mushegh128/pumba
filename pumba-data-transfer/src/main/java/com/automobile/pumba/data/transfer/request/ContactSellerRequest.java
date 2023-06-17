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
public class ContactSellerRequest {

    private String name;
    private String phone;
    private String email;
    private String message;
    @Min(1)
    private Long car;
}
