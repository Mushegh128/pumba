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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/manager/join/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR') and hasAuthority('MANAGE_ORDER_JOIN')")
    public void joinOrder(@PathVariable long orderId) {
        User currentUser = userService.getCurrentUser();
        orderService.joinOrderManager(orderId, currentUser);
    }

    @GetMapping("/manager/history/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR') and hasAuthority('MANAGE_ORDER_VIEW_HISTORY')")
    public ResponseEntity<?> findAllOrderHistory(@PathVariable long orderId) {
        return ResponseEntity.ok(orderService.findAllHistoryByOrderId(orderId));
    }

    @PostMapping("/manager/approve/{orderJoinRequestId}")
    @PreAuthorize("hasAnyRole('ADMIN','OPERATOR') and hasAuthority('MANAGE_ORDER_APPROVE')")
    public void approveOrder(@PathVariable long orderJoinRequestId) {
        orderJoinRequestService.approveOrderRequest(orderJoinRequestId);
    }

    @PostMapping("/manager/change-status/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and hasAuthority('MANAGE_ORDER_STATUS')")
    public void changeOrderStatus(@PathVariable long orderId, @RequestParam OrderStatus orderStatus) {
        User currentUser = userService.getCurrentUser();
        orderService.changeStatus(orderId, orderStatus, currentUser);
    }

    @PostMapping("/manager/cancel/{orderJoinRequestId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and hasAuthority('MANAGE_ORDER_CANCEL')")
    public void cancelOrder(@PathVariable long orderJoinRequestId) {
        orderJoinRequestService.cancelOrderRequest(orderJoinRequestId);
    }

    @PostMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and hasAuthority('MANAGE_ORDER_VIEW')")
    public ResponseEntity<?> findAll(
            @PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestBody OrderFilterRequest orderFilterRequest) {
        return ResponseEntity.ok(orderService.findAll(pageable, orderFilterRequest));
    }

    @GetMapping("/request-list")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and hasAuthority('MANAGE_ORDER_REQUEST')")
    public ResponseEntity<?> findAllRequest(
            @PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(orderService.findAllRequest(pageable));
    }
}
