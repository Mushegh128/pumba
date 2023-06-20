package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.CarEngineType;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.CarEngineTypeRequest;
import com.automobile.pumba.data.transfer.response.CarEngineTypeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarEngineTypeMapper implements BaseMapper<CarEngineType, CarEngineTypeRequest, CarEngineTypeResponse> {

    private final ModelMapper modelMapper;

    @Override
    public CarEngineType toEntity(CarEngineTypeRequest carEngineTypeRequest) {
        return modelMapper.map(carEngineTypeRequest, CarEngineType.class);
    }

    @Override
    public CarEngineTypeResponse toResponse(CarEngineType carEngineType) {
        return modelMapper.map(carEngineType, CarEngineTypeResponse.class);
    }

    public void updateFromRequest(CarEngineTypeRequest carEngineTypeRequest, CarEngineType carEngineType) {
        modelMapper.map(carEngineTypeRequest, carEngineType);
    }
}
