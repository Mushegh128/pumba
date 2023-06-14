package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.CarMake;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.CarMakeRequest;
import com.automobile.pumba.data.transfer.response.CarMakeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarMakeMapper implements BaseMapper<CarMake, CarMakeRequest, CarMakeResponse> {

    private final ModelMapper modelMapper;

    @Override
    public CarMake toEntity(CarMakeRequest carMakeRequest) {
        return modelMapper.map(carMakeRequest, CarMake.class);
    }

    @Override
    public CarMakeResponse toResponse(CarMake carMake) {
        return modelMapper.map(carMake, CarMakeResponse.class);
    }
}
