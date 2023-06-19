package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.OrderJoinRequest;
import com.automobile.pumba.data.transfer.response.OrderJoinRequestResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderJoinRequestMapper {

    private final ModelMapper modelMapper;

    public OrderJoinRequestResponse toResponse(OrderJoinRequest orderJoinRequest) {
        return modelMapper.map(orderJoinRequest, OrderJoinRequestResponse.class);
    }
}
