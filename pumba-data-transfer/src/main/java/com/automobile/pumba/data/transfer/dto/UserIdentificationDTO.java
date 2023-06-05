package com.automobile.pumba.data.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserIdentificationDTO {

    private String ipAddress;
    private String userAgent;
}
