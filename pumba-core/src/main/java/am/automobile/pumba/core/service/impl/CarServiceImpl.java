package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.Car;
import am.automobile.pumba.core.entity.CarDrivetrainType;
import am.automobile.pumba.core.entity.CarEngineType;
import am.automobile.pumba.core.entity.CarFuelType;
import am.automobile.pumba.core.entity.CarMake;
import am.automobile.pumba.core.entity.CarModel;
import am.automobile.pumba.core.entity.CarTransmissionType;
import am.automobile.pumba.core.mapper.CarMapper;
import am.automobile.pumba.core.repository.CarRepository;
import am.automobile.pumba.core.service.CarMetaDataService;
import am.automobile.pumba.core.service.CarService;
import am.automobile.pumba.core.util.IOUtil;
import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMetaDataService carMetaDataService;
    private final IOUtil ioUtil;
    private final CarMapper carMapper;
    @Value("${pumba.path.image.car}")
    private String imageSaveFolderPath;

    @Override
    public CarResponse createCar(CarRequest carRequest) {
        Car car = carMapper.toEntity(carRequest);
        if (carRequest.getCarMake() != null) {
            CarMake carMakeById = carMetaDataService.findCarMakeById(carRequest.getCarMake());
            car.setMake(carMakeById);
        }
        if (carRequest.getCarModel() != null) {
            CarModel carModelById = carMetaDataService.findCarModelById(carRequest.getCarModel());
            car.setModel(carModelById);
        }
        if (carRequest.getFuelType() != null) {
            CarFuelType carFuelTypeById = carMetaDataService.findCarFuelTypeById(carRequest.getFuelType());
            car.setFuelType(carFuelTypeById);
        }
        if (carRequest.getTransmission() != null) {
            CarTransmissionType carTransmissionTypeById = carMetaDataService.findCarTransmissionTypeById(carRequest.getTransmission());
            car.setTransmission(carTransmissionTypeById);
        }
        if (carRequest.getEngineType() != null) {
            CarEngineType carEngineTypeById = carMetaDataService.findCarEngineTypeById(carRequest.getEngineType());
            car.setEngineType(carEngineTypeById);
        }
        if (carRequest.getDrivetrainType() != null) {
            CarDrivetrainType carDrivetrainTypeById = carMetaDataService.findCarDrivetrainTypeById(carRequest.getDrivetrainType());
            car.setDrivetrainType(carDrivetrainTypeById);
        }
        car.setIsPublic(false);
        car.setIsApproved(false);
        Car save = carRepository.save(car);
        return carMapper.toResponse(save);
    }

    @Override
    public String saveImage(MultipartFile multipartFile) {
        return ioUtil.saveImage(imageSaveFolderPath, multipartFile);
    }
}
