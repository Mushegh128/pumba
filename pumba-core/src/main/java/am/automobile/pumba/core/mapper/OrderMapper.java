package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.Order;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.OrderRequest;
import com.automobile.pumba.data.transfer.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper implements BaseMapper<Order, OrderRequest, OrderResponse> {

    private final ModelMapper modelMapper;

    @Override
    public Order toEntity(OrderRequest orderRequest) {
        return modelMapper.map(orderRequest, Order.class);
    }

    @Override
    public OrderResponse toResponse(Order order) {
        return modelMapper.map(order, OrderResponse.class);
    }
}
