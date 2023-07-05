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
public class OrderStatusHistoryResponse {

    private UserResponse user;
    private OrderResponse order;
    private OrderStatus status;
    private LocalDateTime createAt;
}
