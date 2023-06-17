package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.facade.UserIdentifyInformationFacade;
import am.automobile.pumba.core.service.OrderService;
import com.automobile.pumba.data.transfer.request.OrderRequest;
import com.automobile.pumba.data.transfer.response.OrderResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private final UserIdentifyInformationFacade userIdentifyInformationFacade;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest orderRequest,
            HttpServletRequest request) {
        IpAddress ipAddress = userIdentifyInformationFacade.save(request);
        orderService.createOrder(orderRequest, ipAddress);
        return ResponseEntity.ok().build();
    }
}
