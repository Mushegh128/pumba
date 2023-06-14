package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
