package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.Order;
import com.automobile.pumba.data.transfer.dto.UserIdentificationDTO;
import com.automobile.pumba.data.transfer.request.OrderRequest;

public interface OrderService {

    void createOrder(OrderRequest orderRequest, UserIdentificationDTO userIdentificationDTO);

    Order findById(long orderId);
}
