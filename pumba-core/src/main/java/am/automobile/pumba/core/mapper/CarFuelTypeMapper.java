package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.CarFuelType;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.CarFuelTypeRequest;
import com.automobile.pumba.data.transfer.response.CarFuelTypeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarFuelTypeMapper implements BaseMapper<CarFuelType, CarFuelTypeRequest, CarFuelTypeResponse> {

    private final ModelMapper modelMapper;


    @Override
    public CarFuelType toEntity(CarFuelTypeRequest carFuelTypeRequest) {
        return modelMapper.map(carFuelTypeRequest, CarFuelType.class);
    }

    @Override
    public CarFuelTypeResponse toResponse(CarFuelType carFuelType) {
        return modelMapper.map(carFuelType, CarFuelTypeResponse.class);
    }
}
