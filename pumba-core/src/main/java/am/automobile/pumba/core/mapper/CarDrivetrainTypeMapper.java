package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.CarDrivetrainType;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.CarDrivetrainTypeRequest;
import com.automobile.pumba.data.transfer.response.CarDrivetrainTypeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarDrivetrainTypeMapper implements BaseMapper<CarDrivetrainType, CarDrivetrainTypeRequest, CarDrivetrainTypeResponse> {

    private final ModelMapper modelMapper;

    @Override
    public CarDrivetrainType toEntity(CarDrivetrainTypeRequest carDrivetrainTypeRequest) {
        return modelMapper.map(carDrivetrainTypeRequest, CarDrivetrainType.class);
    }

    @Override
    public CarDrivetrainTypeResponse toResponse(CarDrivetrainType carDrivetrainType) {
        return modelMapper.map(carDrivetrainType, CarDrivetrainTypeResponse.class);
    }

    public void updateFromRequest(CarDrivetrainTypeRequest carDrivetrainTypeRequest, CarDrivetrainType carDrivetrainType) {
        modelMapper.map(carDrivetrainTypeRequest, carDrivetrainType);
    }
}
