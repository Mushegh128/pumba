package am.automobile.pumba.core.specifications.filter;

import am.automobile.pumba.core.entity.User;
import com.automobile.pumba.data.transfer.model.CarTracking;
import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.request.CarAdminFilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public interface CarAdminFilterSpecifications<T> {

    default void addIsNotApprovedPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        Boolean isNotApproved = carFilterRequest().getIsNotApproved();
        if (isNotApproved != null && isNotApproved.equals(true) && user().getPermissions().contains(UserPermission.MANAGE_CAR_APPROVE)) {
            predicates.add(criteriaBuilder.isFalse(root.get("isApproved")));
        } else {
            predicates.add(criteriaBuilder.isTrue(root.get("isApproved")));
        }
    }

    default void addInAuctionPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        Boolean inAuction = carFilterRequest().getInAuction();
        if (inAuction != null) {
            Predicate predicate;
            if (inAuction) {
                predicate = criteriaBuilder.equal(root.get("tracking"), CarTracking.IN_AUCTION);
            } else {
                predicate = criteriaBuilder.notEqual(root.get("tracking"), CarTracking.IN_AUCTION);
            }
            predicates.add(predicate);
        }
    }

    default void addInTransitPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        Boolean inTransit = carFilterRequest().getInTransit();
        if (inTransit != null) {
            Predicate predicate;
            if (inTransit) {
                predicate = criteriaBuilder.equal(root.get("tracking"), CarTracking.IN_TRANSIT);
            } else {
                predicate = criteriaBuilder.notEqual(root.get("tracking"), CarTracking.IN_TRANSIT);
            }
            predicates.add(predicate);
        }
    }

    default void addHasArrivedPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        Boolean hasArrived = carFilterRequest().getHasArrived();
        if (hasArrived != null) {
            Predicate predicate;
            if (hasArrived) {
                predicate = criteriaBuilder.equal(root.get("tracking"), CarTracking.HAS_ARRIVED);
            } else {
                predicate = criteriaBuilder.notEqual(root.get("tracking"), CarTracking.HAS_ARRIVED);
            }
            predicates.add(predicate);
        }
    }

    default void addIsPublicPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        Boolean isPublic = carFilterRequest().getIsPublic();
        if (isPublic != null) {
            predicates.add(criteriaBuilder.equal(root.get("isPublic"), isPublic));
        }
    }

    CarAdminFilterRequest carFilterRequest();

    User user();
}
