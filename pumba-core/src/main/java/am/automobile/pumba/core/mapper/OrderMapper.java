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
    private final UserMapper userMapper;
    private final CarMapper carMapper;

    @Override
    public Order toEntity(OrderRequest orderRequest) {
        return modelMapper.map(orderRequest, Order.class);
    }

    @Override
    public OrderResponse toResponse(Order order) {
        modelMapper.typeMap(Order.class, OrderResponse.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getManager().getId(), OrderResponse::setManager);
                    mapper.map(src -> src.getCar().getId(), OrderResponse::setCar);
                });

        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        if (order.getManager() != null) {
            orderResponse.setManager(userMapper.toResponse(order.getManager()));
        }
        orderResponse.setCar(carMapper.toResponse(order.getCar()));
        return orderResponse;
    }
}
