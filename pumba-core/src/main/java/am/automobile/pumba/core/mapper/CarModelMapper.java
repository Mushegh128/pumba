package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.CarModel;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.CarModelRequest;
import com.automobile.pumba.data.transfer.response.CarModelResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarModelMapper implements BaseMapper<CarModel, CarModelRequest, CarModelResponse> {

    private final ModelMapper modelMapper;

    @Override
    public CarModel toEntity(CarModelRequest carModelRequest) {
        return modelMapper.map(carModelRequest, CarModel.class);
    }

    @Override
    public CarModelResponse toResponse(CarModel carModel) {
        return modelMapper.map(carModel, CarModelResponse.class);
    }

    public void updateFromRequest(CarModelRequest carModelRequest, CarModel carModel) {
        modelMapper.map(carModelRequest, carModel);
    }
}
