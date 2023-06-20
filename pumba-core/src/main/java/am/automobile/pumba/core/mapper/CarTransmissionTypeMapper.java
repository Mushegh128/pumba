package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.CarTransmissionType;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.CarTransmissionTypeRequest;
import com.automobile.pumba.data.transfer.response.CarTransmissionTypeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarTransmissionTypeMapper implements BaseMapper<CarTransmissionType, CarTransmissionTypeRequest, CarTransmissionTypeResponse> {

    private final ModelMapper modelMapper;

    @Override
    public CarTransmissionType toEntity(CarTransmissionTypeRequest carTransmissionTypeRequest) {
        return modelMapper.map(carTransmissionTypeRequest, CarTransmissionType.class);
    }

    @Override
    public CarTransmissionTypeResponse toResponse(CarTransmissionType carTransmissionType) {
        return modelMapper.map(carTransmissionType, CarTransmissionTypeResponse.class);
    }

    public void updateFromRequest(CarTransmissionTypeRequest carTransmissionTypeRequest, CarTransmissionType carTransmissionType) {
        modelMapper.map(carTransmissionTypeRequest, carTransmissionType);
    }
}
