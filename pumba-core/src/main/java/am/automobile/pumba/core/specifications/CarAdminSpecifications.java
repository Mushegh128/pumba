package am.automobile.pumba.core.specifications;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.specifications.filter.CarAdminFilterSpecifications;
import com.automobile.pumba.data.transfer.request.CarAdminFilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public record CarAdminSpecifications<T>(CarAdminFilterRequest carFilterRequest,
                                        User user) implements Specification<T>, CarAdminFilterSpecifications<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        addRequestedPredicate(root, criteriaBuilder, predicates);
        addMyAddedPredicate(root, criteriaBuilder, predicates);
        addInAuctionPredicate(root, criteriaBuilder, predicates);
        addInTransitPredicate(root, criteriaBuilder, predicates);
        addHasArrivedPredicate(root, criteriaBuilder, predicates);
        addIsPublicPredicate(root, criteriaBuilder, predicates);

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }
}
