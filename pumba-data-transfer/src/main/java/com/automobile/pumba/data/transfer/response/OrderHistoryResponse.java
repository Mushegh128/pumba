package com.automobile.pumba.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistoryResponse {

    private List<OrderJoinRequestResponse> orderJoinRequests;
    private List<OrderStatusHistoryResponse> orderStatusHistories;
}
