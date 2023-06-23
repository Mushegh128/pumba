package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.Car;
import am.automobile.pumba.core.entity.CarImage;
import am.automobile.pumba.core.repository.CarImageRepository;
import am.automobile.pumba.core.service.CarImageService;
import am.automobile.pumba.core.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarImageServiceImpl implements CarImageService {

    private final CarImageRepository carImageRepository;

    @Value("${pumba.baseUrl}")
    private String baseUrl;
    @Autowired
    @Lazy
    private CarService carService;

    @Override
    public void saveAll(long carId, List<String> imageUrls) {
        Car car = carService.findById(carId);
        List<CarImage> carImages = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            CarImage carImage = CarImage.builder()
                    .car(car)
                    .imageUrl(imageUrl)
                    .build();
            carImages.add(carImage);
        }

        carImageRepository.saveAll(carImages);
    }

    @Override
    public List<String> findAllUrlByCarId(long carId) {
        return carImageRepository.findAllByCar_IdAndCar_IsApprovedTrueAndCar_IsPublicTrue(carId)
                .stream().map(carImage -> baseUrl + "/car/image/" + carImage.getImageUrl())
                .collect(Collectors.toList());
    }
}
