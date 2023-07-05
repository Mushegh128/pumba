package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
}
