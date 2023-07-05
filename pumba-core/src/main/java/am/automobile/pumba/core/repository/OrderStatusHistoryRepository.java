package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {

    List<OrderStatusHistory> findAllByOrder_IdOrderByCreateAtDesc(long orderId);
}
