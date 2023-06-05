package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.Order;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.OrderMapper;
import am.automobile.pumba.core.repository.OrderRepository;
import am.automobile.pumba.core.service.OrderService;
import com.automobile.pumba.data.transfer.dto.UserIdentificationDTO;
import com.automobile.pumba.data.transfer.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void createOrder(OrderRequest orderRequest, UserIdentificationDTO userIdentificationDTO) {

        Order order = orderMapper.toEntity(orderRequest);
        order.setIpAddress(userIdentificationDTO.getIpAddress());
        order.setUserAgent(userIdentificationDTO.getUserAgent());
        orderRepository.save(order);
    }

    @Override
    public Order findById(long orderId) {
        log.info("Find by Order id: {}", orderId);
        return orderRepository.findById(orderId).orElseThrow(() -> {
            throw new EntityNotFoundException("Order with id: " + orderId + " NOT FOUND");
        });
    }
}
