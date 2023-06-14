package com.automobile.pumba.data.transfer.response;

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
public class UserProfileDetailsResponse {

    private String firstName;
    private String lastName;
    private String phone;
    private String avatar;
    private String aboutUser;
}
