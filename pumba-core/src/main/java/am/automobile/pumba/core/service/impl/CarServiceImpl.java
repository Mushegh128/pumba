package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.Car;
import am.automobile.pumba.core.entity.CarDrivetrainType;
import am.automobile.pumba.core.entity.CarEngineType;
import am.automobile.pumba.core.entity.CarFuelType;
import am.automobile.pumba.core.entity.CarMake;
import am.automobile.pumba.core.entity.CarModel;
import am.automobile.pumba.core.entity.CarTransmissionType;
import am.automobile.pumba.core.entity.ContactEmail;
import am.automobile.pumba.core.entity.ContactPhone;
import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.CarMapper;
import am.automobile.pumba.core.repository.CarRepository;
import am.automobile.pumba.core.service.CarImageService;
import am.automobile.pumba.core.service.CarMetaDataService;
import am.automobile.pumba.core.service.CarService;
import am.automobile.pumba.core.service.ContactService;
import am.automobile.pumba.core.service.UserService;
import am.automobile.pumba.core.specifications.CarAdminSpecifications;
import am.automobile.pumba.core.specifications.CarSpecifications;
import am.automobile.pumba.core.util.IOUtil;
import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.request.CarAdminFilterRequest;
import com.automobile.pumba.data.transfer.request.CarFilterRequest;
import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarDetailResponse;
import com.automobile.pumba.data.transfer.response.CarResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ContactService contactService;
    private final CarMetaDataService carMetaDataService;
    private final CarImageService carImageService;
    private final UserService userService;
    private final IOUtil ioUtil;
    private final CarMapper carMapper;

    @Value("${pumba.path.image.car}")
    private String imageSaveFolderPath;

    @Override
    public CarResponse createCar(CarRequest carRequest) {
        Car car = carMapper.toEntity(carRequest);
        car.setDeleted(false);
        User currentUser = userService.getCurrentUser();
        car.setOwner(currentUser);

        updateCarAttributes(car, carRequest);

        if (carRequest.getIsPublic() == null) {
            car.setIsPublic(false);
        }
        car.setIsApproved(false);
        Car save = carRepository.save(car);

        if (carRequest.getImagesDetails() != null && carRequest.getImagesDetails().size() > 0) {
            carImageService.saveAll(car.getId(), carRequest.getImagesDetails());
        }

        return carMapper.toResponse(save);
    }

    @Override
    public CarResponse editCar(CarRequest carRequest, long carId) {
        User currentUser = userService.getCurrentUser();
        Car car;
        if (currentUser.getPermissions().contains(UserPermission.MANAGE_ALL_CARS_UPDATE)) {
            car = findById(carId);
        } else {
            car = findByIdAndOwnerId(carId, currentUser.getId());
        }

        updateCarAttributes(car, carRequest);

        Car save = carRepository.save(car);

        if (carRequest.getImagesDetails() != null && carRequest.getImagesDetails().size() > 0) {
            carImageService.saveAll(car.getId(), carRequest.getImagesDetails());
        }

        return carMapper.toResponse(save);
    }

    @Override
    public CarResponse findByIdAndAccess(long id) {
        Car car = null;
        try {
            User currentUser = userService.getCurrentUser();
            Optional<Car> carOptional = carRepository.findByIdAndOwner_IdAndDeletedFalse(id, currentUser.getId());

            if (currentUser.getPermissions().contains(UserPermission.VIEW_All_CARS) || carOptional.isPresent()) {
                car = findById(id);
            }
        } catch (RuntimeException e) {
            car = findByIdAndIsPublicTrueAndIsApprovedTrue(id);
        }
        return carMapper.toResponse(car);
    }

    @Override
    public CarResponse approveById(long id) {
        Car car = findById(id);
        car.setIsApproved(true);
        Car save = carRepository.save(car);
        return carMapper.toResponse(save);
    }

    @Override
    public CarResponse cancelById(long id) {
        Car car = findById(id);
        car.setIsApproved(false);
        car.setIsPublic(false);
        car.setDeleted(true);
        Car save = carRepository.save(car);
        return carMapper.toResponse(save);
    }

    @Override
    public String saveImage(MultipartFile multipartFile) {
        return ioUtil.saveImage(imageSaveFolderPath, multipartFile);
    }

    @Override
    public byte[] getImage(String fileName) {
        return ioUtil.getAllBytesByUrl(imageSaveFolderPath, fileName);
    }

    @Override
    public Car findById(long id) {
        log.info("Find User by ID: {}", id);
        return carRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with id: " + id + " not found"));
    }

    @Override
    public Car findByIdAndIsPublicTrueAndIsApprovedTrue(long id) {
        log.info("Find User by ID: {}", id);
        return carRepository.findByIdAndIsPublicTrueAndIsApprovedTrueAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with id: " + id + " not found"));
    }

    @Override
    public Car findByIdAndOwnerId(long id, long ownerId) {
        log.info("Find User by ID: {}", id);
        return carRepository.findByIdAndOwner_IdAndDeletedFalse(id, ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Car with id: " + id + " not found"));
    }

    @Override
    public Page<CarResponse> findAllForAdmin(Pageable pageable, CarAdminFilterRequest carFilterRequest) {
        User currentUser = userService.getCurrentUser();
        Page<Car> carPage;
        if (carFilterRequest.getAll() != null && carFilterRequest.getAll().equals(true)) {
            if (currentUser.getPermissions().contains(UserPermission.VIEW_All_CARS)) {
                carPage = carRepository.findAllByDeletedFalse(pageable);
            } else {
                carPage = carRepository.findAllByOwner_IdAndDeletedFalse(currentUser.getId(), pageable);
            }
        } else {
            CarAdminSpecifications<Car> filterSpecifications = new CarAdminSpecifications<>(carFilterRequest, currentUser);
            carPage = carRepository.findAll(filterSpecifications, pageable);
        }
        return carPage.map(carMapper::toResponse);
    }

    @Override
    public Page<CarDetailResponse> findAllFilter(Pageable pageable, CarFilterRequest carFilterRequest) {
        CarSpecifications<Car> filterSpecifications = new CarSpecifications<>(carFilterRequest);
        Page<Car> carPage = carRepository.findAll(filterSpecifications, pageable);
        return carPage.map(carMapper::toResponseDetail);
    }

    private void updateCarAttributes(Car car, CarRequest carRequest) {
        carMapper.updateFromRequest(carRequest, car);

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
        if (carRequest.getContactPhone() != null) {
            ContactPhone contactPhoneById = contactService.findContactPhoneById(carRequest.getContactPhone());
            car.setContactPhone(contactPhoneById);
        }
        if (carRequest.getContactEmail() != null) {
            ContactEmail contactEmailById = contactService.findContactEmailById(carRequest.getContactEmail());
            car.setContactEmail(contactEmailById);
        }
    }
}
