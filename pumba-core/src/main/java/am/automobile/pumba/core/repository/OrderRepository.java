package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
