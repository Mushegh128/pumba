package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.entity.Order;
import com.automobile.pumba.data.transfer.request.OrderRequest;
import com.automobile.pumba.data.transfer.response.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest, IpAddress ipAddress);

    Order findById(long orderId);
}
