package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.Order;
import am.automobile.pumba.core.entity.User;

public interface OrderJoinRequestService {

    void openRequest(User user, Order order);

    void approveOrderRequest(long orderJoinRequestId);

    void cancelOrderRequest(long orderJoinRequestId);

    void cancelAllOrdersRequestByUserId(long userId);
}
