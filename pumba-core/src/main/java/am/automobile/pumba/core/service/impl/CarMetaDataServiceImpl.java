package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.CarDrivetrainType;
import am.automobile.pumba.core.entity.CarEngineType;
import am.automobile.pumba.core.entity.CarFuelType;
import am.automobile.pumba.core.entity.CarMake;
import am.automobile.pumba.core.entity.CarModel;
import am.automobile.pumba.core.entity.CarTransmissionType;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.CarDrivetrainTypeMapper;
import am.automobile.pumba.core.mapper.CarEngineTypeMapper;
import am.automobile.pumba.core.mapper.CarFuelTypeMapper;
import am.automobile.pumba.core.mapper.CarMakeMapper;
import am.automobile.pumba.core.mapper.CarModelMapper;
import am.automobile.pumba.core.mapper.CarTransmissionTypeMapper;
import am.automobile.pumba.core.repository.CarDrivetrainTypeRepository;
import am.automobile.pumba.core.repository.CarEngineTypeRepository;
import am.automobile.pumba.core.repository.CarFuelTypeRepository;
import am.automobile.pumba.core.repository.CarMakeRepository;
import am.automobile.pumba.core.repository.CarModelRepository;
import am.automobile.pumba.core.repository.CarTransmissionTypeRepository;
import am.automobile.pumba.core.service.CarMetaDataService;
import com.automobile.pumba.data.transfer.request.CarDrivetrainTypeRequest;
import com.automobile.pumba.data.transfer.request.CarEngineTypeRequest;
import com.automobile.pumba.data.transfer.request.CarFuelTypeRequest;
import com.automobile.pumba.data.transfer.request.CarMakeRequest;
import com.automobile.pumba.data.transfer.request.CarModelRequest;
import com.automobile.pumba.data.transfer.request.CarTransmissionTypeRequest;
import com.automobile.pumba.data.transfer.response.CarDrivetrainTypeResponse;
import com.automobile.pumba.data.transfer.response.CarEngineTypeResponse;
import com.automobile.pumba.data.transfer.response.CarFuelTypeResponse;
import com.automobile.pumba.data.transfer.response.CarMakeResponse;
import com.automobile.pumba.data.transfer.response.CarMetadataResponse;
import com.automobile.pumba.data.transfer.response.CarModelResponse;
import com.automobile.pumba.data.transfer.response.CarTransmissionTypeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarMetaDataServiceImpl implements CarMetaDataService {

    private final CarDrivetrainTypeMapper carDrivetrainTypeMapper;
    private final CarEngineTypeMapper carEngineTypeMapper;
    private final CarMakeMapper carMakeMapper;
    private final CarModelMapper carModelMapper;
    private final CarFuelTypeMapper carFuelTypeMapper;
    private final CarTransmissionTypeMapper carTransmissionTypeMapper;
    private final CarDrivetrainTypeRepository carDrivetrainTypeRepository;
    private final CarEngineTypeRepository carEngineTypeRepository;
    private final CarMakeRepository carMakeRepository;
    private final CarModelRepository carModelRepository;
    private final CarFuelTypeRepository carFuelTypeRepository;
    private final CarTransmissionTypeRepository carTransmissionTypeRepository;

    @Override
    public CarDrivetrainTypeResponse createCarDrivetrainType(CarDrivetrainTypeRequest carDrivetrainTypeRequest) {
        CarDrivetrainType carDrivetrainType = carDrivetrainTypeMapper.toEntity(carDrivetrainTypeRequest);
        CarDrivetrainType save = carDrivetrainTypeRepository.save(carDrivetrainType);
        return carDrivetrainTypeMapper.toResponse(save);
    }

    @Override
    public CarEngineTypeResponse createCarEngineType(CarEngineTypeRequest carEngineTypeRequest) {
        CarEngineType carEngineType = carEngineTypeMapper.toEntity(carEngineTypeRequest);
        CarEngineType save = carEngineTypeRepository.save(carEngineType);
        return carEngineTypeMapper.toResponse(save);
    }

    @Override
    public CarMakeResponse createCarMake(CarMakeRequest carMakeRequest) {
        CarMake carMake = carMakeMapper.toEntity(carMakeRequest);
        CarMake save = carMakeRepository.save(carMake);
        return carMakeMapper.toResponse(save);
    }

    @Override
    public CarModelResponse createCarModel(CarModelRequest carModelRequest) {
        CarModel carModel = carModelMapper.toEntity(carModelRequest);
        CarModel save = carModelRepository.save(carModel);
        return carModelMapper.toResponse(save);
    }

    @Override
    public CarFuelTypeResponse createCarFuelType(CarFuelTypeRequest carFuelTypeRequest) {
        CarFuelType carFuelType = carFuelTypeMapper.toEntity(carFuelTypeRequest);
        CarFuelType save = carFuelTypeRepository.save(carFuelType);
        return carFuelTypeMapper.toResponse(save);
    }

    @Override
    public CarTransmissionTypeResponse createCarTransmissionType(CarTransmissionTypeRequest carTransmissionTypeRequest) {
        CarTransmissionType carTransmissionType = carTransmissionTypeMapper.toEntity(carTransmissionTypeRequest);
        CarTransmissionType save = carTransmissionTypeRepository.save(carTransmissionType);
        return carTransmissionTypeMapper.toResponse(save);
    }

    @Override
    public List<CarDrivetrainTypeResponse> findAllCarDrivetrainTypes() {
        List<CarDrivetrainType> carDrivetrainTypes = carDrivetrainTypeRepository.findAll();
        return carDrivetrainTypes.stream()
                .map(carDrivetrainTypeMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<CarEngineTypeResponse> findAllCarEngineTypes() {
        List<CarEngineType> carEngineTypes = carEngineTypeRepository.findAll();
        return carEngineTypes.stream()
                .map(carEngineTypeMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<CarMakeResponse> findAllCarMakes() {
        List<CarMake> carMakes = carMakeRepository.findAll();
        return carMakes.stream()
                .map(carMakeMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<CarModelResponse> findAllCarModels() {
        List<CarModel> carModels = carModelRepository.findAll();
        return carModels.stream()
                .map(carModelMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<CarFuelTypeResponse> findAllCarFuelTypes() {
        List<CarFuelType> carFuelTypes = carFuelTypeRepository.findAll();
        return carFuelTypes.stream()
                .map(carFuelTypeMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<CarTransmissionTypeResponse> findAllCarTransmissionTypes() {
        List<CarTransmissionType> carTransmissionTypes = carTransmissionTypeRepository.findAll();
        return carTransmissionTypes.stream()
                .map(carTransmissionTypeMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public CarMetadataResponse findAll() {
        return CarMetadataResponse.builder()
                .carModels(findAllCarModels())
                .carMakes(findAllCarMakes())
                .carEngineTypes(findAllCarEngineTypes())
                .carFuelTypes(findAllCarFuelTypes())
                .carDrivetrainTypes(findAllCarDrivetrainTypes())
                .carTransmissionTypes(findAllCarTransmissionTypes())
                .build();
    }

    @Override
    public CarDrivetrainType findCarDrivetrainTypeById(long id) {
        log.info("Find CarDrivetrainType by ID: {}", id);
        return carDrivetrainTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarDrivetrainType with id: " + id + " not found"));
    }

    @Override
    public CarEngineType findCarEngineTypeById(long id) {
        log.info("Find CarEngineType by ID: {}", id);
        return carEngineTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarEngineType with id: " + id + " not found"));
    }

    @Override
    public CarMake findCarMakeById(long id) {
        log.info("Find CarMake by ID: {}", id);
        return carMakeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarMake with id: " + id + " not found"));
    }

    @Override
    public CarModel findCarModelById(long id) {
        log.info("Find CarModel by ID: {}", id);
        return carModelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarModel with id: " + id + " not found"));
    }

    @Override
    public CarFuelType findCarFuelTypeById(long id) {
        log.info("Find CarFuelType by ID: {}", id);
        return carFuelTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarFuelType with id: " + id + " not found"));
    }

    @Override
    public CarTransmissionType findCarTransmissionTypeById(long id) {
        log.info("Find CarTransmissionType by ID: {}", id);
        return carTransmissionTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarTransmissionType with id: " + id + " not found"));
    }

}
