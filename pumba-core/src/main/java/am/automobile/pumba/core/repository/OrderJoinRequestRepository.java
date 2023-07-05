package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.OrderJoinRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderJoinRequestRepository extends JpaRepository<OrderJoinRequest, Long> {

    Optional<OrderJoinRequest> findByOrder_IdAndRequestSenderUser_IdAndApproveFalseAndCancelFalse(long orderId, long userId);

    Optional<OrderJoinRequest> findByIdAndApproveFalseAndCancelFalse(long id);

    List<OrderJoinRequest> findAllByIdNotAndOrder_IdAndApproveFalseAndCancelFalse(long id, long orderId);

    List<OrderJoinRequest> findAllByOrder_IdOrderByCreateAtDesc(long orderId);

    Page<OrderJoinRequest> findAllByApproveFalseAndCancelFalseOrderByCreateAt(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE OrderJoinRequest o SET o.cancel = true, o.cancelAt = current_timestamp() WHERE o.requestSenderUser.id = :userId AND o.approve = false AND o.cancel = false")
    void cancelAllOrdersRequestByUserId(long userId);

    @Modifying
    @Transactional
    @Query("UPDATE OrderJoinRequest r SET r.cancel = false, r.cancelAt = current_timestamp() WHERE r.id <> :orderJoinRequestId AND r.order.id = :orderId AND r.approve = false AND r.cancel = false")
    void cancelAllOrderJoinRequestsExceptById(long orderJoinRequestId, long orderId);

}
