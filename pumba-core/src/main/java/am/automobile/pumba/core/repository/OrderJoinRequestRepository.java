package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.OrderJoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderJoinRequestRepository extends JpaRepository<OrderJoinRequest, Long> {

    Optional<OrderJoinRequest> findByOrder_IdAndRequestSenderUser_IdAndApproveFalseAndCanselFalse(long orderId, long userId);

    Optional<OrderJoinRequest> findByIdAndApproveFalseAndCanselFalse(long id);

    List<OrderJoinRequest> findAllByIdLessThanEqualAndApproveFalseAndCanselFalse(long id);
}
