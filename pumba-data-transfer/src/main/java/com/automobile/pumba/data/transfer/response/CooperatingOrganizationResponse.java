package com.automobile.pumba.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CooperatingOrganizationResponse {

    private String name;
    private String logo;
    private String description;
    private String website;
}
