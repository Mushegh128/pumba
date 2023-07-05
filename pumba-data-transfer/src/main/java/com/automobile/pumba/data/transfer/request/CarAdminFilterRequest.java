package com.automobile.pumba.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarAdminFilterRequest {

    private Boolean all;
    private Boolean isNotApproved;
    private Boolean myAdded;
    private Boolean inAuction;
    private Boolean inTransit;
    private Boolean hasArrived;
    private Boolean isPublic;
}
