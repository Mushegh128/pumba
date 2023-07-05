package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.OrderStatusHistory;
import com.automobile.pumba.data.transfer.response.OrderStatusHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusHistoryMapper {

    private final ModelMapper modelMapper;

    public OrderStatusHistoryResponse toResponse(OrderStatusHistory orderStatusHistory) {
        return modelMapper.map(orderStatusHistory, OrderStatusHistoryResponse.class);
    }
}
