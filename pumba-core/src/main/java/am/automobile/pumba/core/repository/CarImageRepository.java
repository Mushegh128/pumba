package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {

    List<CarImage> findAllByCar_IdAndCar_IsApprovedTrueAndCar_IsPublicTrue(long carId);
}
