package am.automobile.pumba.core.specifications;

import am.automobile.pumba.core.specifications.filter.CarFilterSpecifications;
import com.automobile.pumba.data.transfer.request.CarFilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public record CarSpecifications<T>(CarFilterRequest carFilterRequest)
        implements Specification<T>, CarFilterSpecifications {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (carFilterRequest.getKeyword() != null && !carFilterRequest.getKeyword().isBlank()
                && carFilterRequest.getKeyword().length() >= 2) {
            String keyword = "%" + carFilterRequest.getKeyword() + "%";
            Predicate titlePredicate = criteriaBuilder.like(root.get("title"), keyword);
            Predicate descriptionPredicate = criteriaBuilder.like(root.get("description"), keyword);
            predicates.add(criteriaBuilder.or(titlePredicate, descriptionPredicate));
        }

        if (carFilterRequest.getTracking() != null) {
            predicates.add(criteriaBuilder.equal(root.get("tracking"), carFilterRequest.getTracking()));
        }
        if (carFilterRequest.getDrivetrainType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("drivetrainType").get("id"), carFilterRequest.getDrivetrainType()));
        }
        if (carFilterRequest.getEngineType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("engineType").get("id"), carFilterRequest.getEngineType()));
        }
        if (carFilterRequest.getTransmission() != null) {
            predicates.add(criteriaBuilder.equal(root.get("transmission").get("id"), carFilterRequest.getTransmission()));
        }
        if (carFilterRequest.getFuelType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("fuelType").get("id"), carFilterRequest.getFuelType()));
        }
        if (carFilterRequest.getCarModel() != null) {
            predicates.add(criteriaBuilder.equal(root.get("carModel").get("id"), carFilterRequest.getCarModel()));
        }
        if (carFilterRequest.getCarMake() != null) {
            predicates.add(criteriaBuilder.equal(root.get("make").get("id"), carFilterRequest.getCarMake()));
        }

        if (carFilterRequest.getMinPrice() != null && carFilterRequest.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.between(root.get("price"), carFilterRequest.getMinPrice(), carFilterRequest.getMaxPrice()));
        } else if (carFilterRequest.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), carFilterRequest.getMinPrice()));
        } else if (carFilterRequest.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), carFilterRequest.getMaxPrice()));
        }

        if (carFilterRequest.getMinMileage() != null && carFilterRequest.getMaxMileage() != null) {
            predicates.add(criteriaBuilder.between(root.get("mileage"), carFilterRequest.getMinMileage(), carFilterRequest.getMaxMileage()));
        } else if (carFilterRequest.getMinMileage() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("mileage"), carFilterRequest.getMinMileage()));
        } else if (carFilterRequest.getMaxMileage() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("mileage"), carFilterRequest.getMaxMileage()));
        }

        if (carFilterRequest.getYear() != null) {
            predicates.add(criteriaBuilder.equal(root.get("year"), carFilterRequest.getYear()));
        }
        predicates.add(criteriaBuilder.isTrue(root.get("isPublic")));
        predicates.add(criteriaBuilder.isTrue(root.get("isApproved")));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
