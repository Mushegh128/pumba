package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.Order;
import am.automobile.pumba.core.entity.OrderJoinRequest;
import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.repository.OrderJoinRequestRepository;
import am.automobile.pumba.core.service.OrderJoinRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderJoinRequestServiceImpl implements OrderJoinRequestService {

    private final OrderJoinRequestRepository orderJoinRequestRepository;

    @Override
    public void openRequest(User user, Order order) {
        Optional<OrderJoinRequest> orderJoinRequestOptional = findByOrderIdAndUserId(order.getId(), user.getId());
        if (orderJoinRequestOptional.isPresent()) {
            return;
        }
        OrderJoinRequest joinRequest = OrderJoinRequest.builder()
                .requestSenderUser(user)
                .order(order)
                .approve(false)
                .cansel(false)
                .build();
        orderJoinRequestRepository.save(joinRequest);
    }

    @Override
    @Transactional
    public void approveOrderRequest(long orderJoinRequestId) {
        OrderJoinRequest orderJoinRequest = findByIdAndApproveFalseAndCanselFalse(orderJoinRequestId);
        orderJoinRequest.setApprove(true);
        orderJoinRequest.setApproveAt(LocalDateTime.now());
        orderJoinRequestRepository.save(orderJoinRequest);
        Order order = orderJoinRequest.getOrder();
        order.setManager(orderJoinRequest.getRequestSenderUser());

        List<OrderJoinRequest> orderAllJoinRequestsByOrderId = orderJoinRequestRepository
                .findAllByIdLessThanEqualAndApproveFalseAndCanselFalse(orderJoinRequestId);

        if (orderAllJoinRequestsByOrderId.isEmpty()) {
            return;
        }

        orderAllJoinRequestsByOrderId = orderAllJoinRequestsByOrderId.stream()
                .peek(joinRequest -> {
                    joinRequest.setCansel(false);
                    joinRequest.setCanselAt(LocalDateTime.now());
                })
                .collect(Collectors.toList());

        orderJoinRequestRepository.saveAll(orderAllJoinRequestsByOrderId);
    }

    @Override
    public void canselOrderRequest(long orderJoinRequestId) {
        OrderJoinRequest orderJoinRequest = findByIdAndApproveFalseAndCanselFalse(orderJoinRequestId);
        orderJoinRequest.setCansel(true);
        orderJoinRequest.setCanselAt(LocalDateTime.now());
        orderJoinRequestRepository.save(orderJoinRequest);
    }

    private Optional<OrderJoinRequest> findByOrderIdAndUserId(long orderId, long userId) {
        return orderJoinRequestRepository.findByOrder_IdAndRequestSenderUser_IdAndApproveFalseAndCanselFalse(orderId, userId);

    }

    private OrderJoinRequest findByIdAndApproveFalseAndCanselFalse(long id) {
        return orderJoinRequestRepository.findByIdAndApproveFalseAndCanselFalse(id).orElseThrow(() -> {
            throw new EntityNotFoundException("NOT FOUND");
        });
    }
}
