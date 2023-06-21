package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.OrderJoinRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderJoinRequestRepository extends JpaRepository<OrderJoinRequest, Long> {

    Optional<OrderJoinRequest> findByOrder_IdAndRequestSenderUser_IdAndApproveFalseAndCancelFalse(long orderId, long userId);

    Optional<OrderJoinRequest> findByIdAndApproveFalseAndCancelFalse(long id);

    List<OrderJoinRequest> findByIdNotAndApproveFalseAndCancelFalse(long id);

    List<OrderJoinRequest> findAllByOrder_IdOrderByCreateAtDesc(long orderId);

    Page<OrderJoinRequest> findAllByApproveFalseAndCancelFalseOrderByCreateAt(Pageable pageable);
}
