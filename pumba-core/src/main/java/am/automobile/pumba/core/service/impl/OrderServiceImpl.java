package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.Car;
import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.entity.Order;
import am.automobile.pumba.core.entity.OrderStatusHistory;
import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.OrderJoinRequestMapper;
import am.automobile.pumba.core.mapper.OrderMapper;
import am.automobile.pumba.core.mapper.OrderStatusHistoryMapper;
import am.automobile.pumba.core.repository.OrderJoinRequestRepository;
import am.automobile.pumba.core.repository.OrderRepository;
import am.automobile.pumba.core.repository.OrderStatusHistoryRepository;
import am.automobile.pumba.core.service.CarService;
import am.automobile.pumba.core.service.OrderJoinRequestService;
import am.automobile.pumba.core.service.OrderService;
import am.automobile.pumba.core.specifications.OrderSpecifications;
import com.automobile.pumba.data.transfer.model.OrderStatus;
import com.automobile.pumba.data.transfer.model.UserRole;
import com.automobile.pumba.data.transfer.request.OrderFilterRequest;
import com.automobile.pumba.data.transfer.request.OrderRequest;
import com.automobile.pumba.data.transfer.response.OrderHistoryResponse;
import com.automobile.pumba.data.transfer.response.OrderJoinRequestResponse;
import com.automobile.pumba.data.transfer.response.OrderResponse;
import com.automobile.pumba.data.transfer.response.OrderStatusHistoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CarService carService;
    private final OrderJoinRequestService orderJoinRequestService;
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final OrderJoinRequestRepository orderJoinRequestRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderJoinRequestMapper orderJoinRequestMapper;
    private final OrderStatusHistoryMapper orderStatusHistoryMapper;

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

    @Override
    public void changeStatus(long orderId, OrderStatus orderStatus, User currentUser) {
        Order order = findById(orderId);

        if (order.getOrderStatus() == orderStatus) {
            return;
        }

        UserRole currentUserRole = currentUser.getRole();
        UserRole managerRole = order.getManager() != null ? order.getManager().getRole() : null;

        if (currentUserRole != UserRole.ADMIN &&
                (managerRole == null || !managerRole.equals(currentUserRole)) &&
                order.getManager().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("User does not have access to change the order status.");
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        OrderStatusHistory statusHistory = OrderStatusHistory.builder()
                .status(orderStatus)
                .order(order)
                .user(currentUser)
                .build();
        orderStatusHistoryRepository.save(statusHistory);
    }

    @Override
    public Page<OrderResponse> findAll(Pageable pageable, OrderFilterRequest orderFilterRequest) {
        Page<Order> orderPage;
        if (orderFilterRequest.isEmpty()) {
            orderPage = orderRepository.findAllByOrderStatusNot(pageable, OrderStatus.SPAM);
        } else {
            OrderSpecifications<Order> filterSpecifications = new OrderSpecifications<>(orderFilterRequest);
            orderPage = orderRepository.findAll(filterSpecifications, pageable);
        }
        return orderPage.map(orderMapper::toResponse);
    }

    @Override
    public void joinOrderManager(long orderId, User currentUser) {
        Order order = findById(orderId);
        if (currentUser.getRole().equals(UserRole.ADMIN)) {
            order.setManager(currentUser);
        } else if (currentUser.getRole().equals(UserRole.OPERATOR)) {
            orderJoinRequestService.openRequest(currentUser, order);
        } else {
            throw new AccessDeniedException("User not access for join order with manager");
        }
        orderRepository.save(order);
    }

    @Override
    public OrderHistoryResponse findAllHistoryByOrderId(Long orderId) {

        List<OrderStatusHistoryResponse> orderStatusHistoryResponses = orderStatusHistoryRepository.findAllByOrder_IdOrderByCreateAtDesc(orderId).stream()
                .map(orderStatusHistoryMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<OrderJoinRequestResponse> orderJoinRequestResponses = orderJoinRequestRepository.findAllByOrder_IdOrderByCreateAtDesc(orderId).stream()
                .map(orderJoinRequestMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));

        return OrderHistoryResponse.builder()
                .orderStatusHistories(orderStatusHistoryResponses)
                .orderJoinRequests(orderJoinRequestResponses)
                .build();
    }
}
