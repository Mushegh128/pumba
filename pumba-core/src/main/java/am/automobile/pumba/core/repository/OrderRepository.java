package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.Order;
import com.automobile.pumba.data.transfer.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Page<Order> findAllByOrderStatusNot(Pageable pageable, OrderStatus orderStatus);
}
