package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.entity.Order;
import am.automobile.pumba.core.entity.User;
import com.automobile.pumba.data.transfer.model.OrderStatus;
import com.automobile.pumba.data.transfer.request.OrderFilterRequest;
import com.automobile.pumba.data.transfer.request.OrderRequest;
import com.automobile.pumba.data.transfer.response.OrderHistoryResponse;
import com.automobile.pumba.data.transfer.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest, IpAddress ipAddress);

    Order findById(long orderId);

    void changeStatus(long orderId, OrderStatus orderStatus, User currentlyUser);

    Page<OrderResponse> findAll(Pageable pageable, OrderFilterRequest orderFilterRequest);

    void joinOrderManager(long orderId, User currentUser);

    OrderHistoryResponse findAllHistoryByOrderId(Long orderId);
}
