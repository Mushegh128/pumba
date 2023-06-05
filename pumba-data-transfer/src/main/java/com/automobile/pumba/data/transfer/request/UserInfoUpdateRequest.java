package com.automobile.pumba.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
