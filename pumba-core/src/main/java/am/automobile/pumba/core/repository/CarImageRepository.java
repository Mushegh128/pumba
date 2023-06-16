package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
    
}
