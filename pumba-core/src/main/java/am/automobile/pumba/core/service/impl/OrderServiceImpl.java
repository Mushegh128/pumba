package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.Car;
import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.entity.Order;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.OrderMapper;
import am.automobile.pumba.core.repository.OrderRepository;
import am.automobile.pumba.core.service.CarService;
import am.automobile.pumba.core.service.OrderService;
import com.automobile.pumba.data.transfer.model.OrderStatus;
import com.automobile.pumba.data.transfer.request.OrderRequest;
import com.automobile.pumba.data.transfer.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CarService carService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest, IpAddress ipAddress) {
        Car car = carService.findById(orderRequest.getCar());
        Order order = orderMapper.toEntity(orderRequest);
        order.setCar(car);
        order.setIpAddress(ipAddress);
        order.setOrderStatus(OrderStatus.PENDING);
        Order save = orderRepository.save(order);
        return orderMapper.toResponse(save);
    }

    @Override
    public Order findById(long orderId) {
        log.info("Find by Order id: {}", orderId);
        return orderRepository.findById(orderId).orElseThrow(() -> {
            throw new EntityNotFoundException("Order with id: " + orderId + " NOT FOUND");
        });
    }
}
