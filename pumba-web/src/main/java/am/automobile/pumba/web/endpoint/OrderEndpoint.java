package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.facade.UserIdentifyInformationFacade;
import am.automobile.pumba.core.service.OrderJoinRequestService;
import am.automobile.pumba.core.service.OrderService;
import am.automobile.pumba.core.service.UserService;
import com.automobile.pumba.data.transfer.model.OrderStatus;
import com.automobile.pumba.data.transfer.request.OrderFilterRequest;
import com.automobile.pumba.data.transfer.request.OrderRequest;
import com.automobile.pumba.data.transfer.response.OrderResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderEndpoint {

    private final OrderService orderService;
    private final OrderJoinRequestService orderJoinRequestService;
    private final UserService userService;
    private final UserIdentifyInformationFacade userIdentifyInformationFacade;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest orderRequest,
            HttpServletRequest request) {
        IpAddress ipAddress = userIdentifyInformationFacade.save(request);
        orderService.createOrder(orderRequest, ipAddress);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
    @PostMapping("/manager/join/{orderId}")
    public void joinOrder(@PathVariable long orderId) {
        User currentUser = userService.getCurrentUser();
        orderService.joinOrderManager(orderId, currentUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/manager/approve/{orderJoinRequestId}")
    public void approveOrder(@PathVariable long orderJoinRequestId) {
        orderJoinRequestService.approveOrderRequest(orderJoinRequestId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping("/manager/change-status/{orderId}")
    public void changeOrderStatus(@PathVariable long orderId, @RequestBody OrderStatus orderStatus) {
        User currentUser = userService.getCurrentUser();
        orderService.changeStatus(orderId, orderStatus, currentUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/manager/cansel/{orderJoinRequestId}")
    public void canselOrder(@PathVariable long orderJoinRequestId) {
        orderJoinRequestService.canselOrderRequest(orderJoinRequestId);
    }

    @PostMapping("/list")
    public ResponseEntity<?> findAll(
            @PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestBody OrderFilterRequest orderFilterRequest) {
        return ResponseEntity.ok(orderService.findAll(pageable, orderFilterRequest));
    }
}
