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
public class OrderJoinRequestResponse {

    private OrderResponse order;
    private UserResponse approveUser;
    private UserResponse requestSenderUser;
    private boolean approve;
    private boolean cancel;
    private LocalDateTime approveAt;
    private LocalDateTime cancelAt;
    private LocalDateTime createAt;
}
