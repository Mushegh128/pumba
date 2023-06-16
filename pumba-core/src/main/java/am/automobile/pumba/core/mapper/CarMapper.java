package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.Car;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarMapper implements BaseMapper<Car, CarRequest, CarResponse> {

    private final ModelMapper modelMapper;

    @Value("${pumba.baseUrl}")
    private String baseUrl;

    @Override
    public Car toEntity(CarRequest carRequest) {
        return modelMapper.map(carRequest, Car.class);
    }

    @Override
    public CarResponse toResponse(Car car) {
        modelMapper.typeMap(Car.class, CarResponse.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMake().getId(), CarResponse::setCarMake);
                    mapper.map(src -> src.getModel().getId(), CarResponse::setCarModel);
                    mapper.map(src -> src.getFuelType().getId(), CarResponse::setFuelType);
                    mapper.map(src -> src.getTransmission().getId(), CarResponse::setTransmission);
                    mapper.map(src -> src.getEngineType().getId(), CarResponse::setEngineType);
                    mapper.map(src -> src.getDrivetrainType().getId(), CarResponse::setDrivetrainType);
                    mapper.map(src -> src.getContactPhone().getId(), CarResponse::setContactPhone);
                    mapper.map(src -> src.getContactEmail().getId(), CarResponse::setContactEmail);
                });

        CarResponse carResponse = modelMapper.map(car, CarResponse.class);
        carResponse.setCarMake(car.getMake() != null ? car.getMake().getId() : null);
        carResponse.setCarModel(car.getModel() != null ? car.getModel().getId() : null);
        carResponse.setFuelType(car.getFuelType() != null ? car.getFuelType().getId() : null);
        carResponse.setTransmission(car.getTransmission() != null ? car.getTransmission().getId() : null);
        carResponse.setEngineType(car.getEngineType() != null ? car.getEngineType().getId() : null);
        carResponse.setDrivetrainType(car.getDrivetrainType() != null ? car.getDrivetrainType().getId() : null);
        carResponse.setContactPhone(car.getContactPhone() != null ? car.getContactPhone().getId() : null);
        carResponse.setContactEmail(car.getContactEmail() != null ? car.getContactEmail().getId() : null);
        carResponse.setBaseImage(baseUrl + "/car/image/" + car.getBaseImage());
        return carResponse;
    }
}
