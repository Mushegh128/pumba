package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.Order;
import com.automobile.pumba.data.transfer.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Page<Order> findAllByOrderStatusNot(Pageable pageable, OrderStatus orderStatus);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.manager = null WHERE o.manager.id = :managerId")
    void expelAllOrdersManagerByManagerId(long managerId);
}
