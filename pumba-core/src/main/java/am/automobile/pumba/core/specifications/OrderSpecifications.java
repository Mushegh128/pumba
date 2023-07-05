package am.automobile.pumba.core.specifications;

import com.automobile.pumba.data.transfer.request.OrderFilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrderSpecifications<T> implements Specification<T> {

    private final OrderFilterRequest orderFilterRequest;

    public OrderSpecifications(OrderFilterRequest orderFilterRequest) {
        this.orderFilterRequest = orderFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (orderFilterRequest.getManager() != null) {
            predicates.add(criteriaBuilder.equal(root.get("manager").get("id"), orderFilterRequest.getManager()));
        }

        if (orderFilterRequest.getCar() != null) {
            predicates.add(criteriaBuilder.equal(root.get("car").get("id"), orderFilterRequest.getCar()));
        }

        if (orderFilterRequest.getFirstName() != null && !orderFilterRequest.getFirstName().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + orderFilterRequest.getFirstName() + "%"));
        }

        if (orderFilterRequest.getLastName() != null && !orderFilterRequest.getLastName().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + orderFilterRequest.getLastName() + "%"));
        }

        if (orderFilterRequest.getCompanyName() != null && !orderFilterRequest.getCompanyName().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("companyName"), "%" + orderFilterRequest.getCompanyName() + "%"));
        }

        if (orderFilterRequest.getCountry() != null && !orderFilterRequest.getCountry().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("country"), "%" + orderFilterRequest.getCountry() + "%"));
        }

        if (orderFilterRequest.getStreetAddress() != null && !orderFilterRequest.getStreetAddress().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("streetAddress"), "%" + orderFilterRequest.getStreetAddress() + "%"));
        }

        if (orderFilterRequest.getAddress() != null && !orderFilterRequest.getAddress().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("address"), "%" + orderFilterRequest.getAddress() + "%"));
        }

        if (orderFilterRequest.getPostcodeZip() != null && !orderFilterRequest.getPostcodeZip().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("postcodeZip"), "%" + orderFilterRequest.getPostcodeZip() + "%"));
        }

        if (orderFilterRequest.getCity() != null && !orderFilterRequest.getCity().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("city"), "%" + orderFilterRequest.getCity() + "%"));
        }

        if (orderFilterRequest.getProvince() != null && !orderFilterRequest.getProvince().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("province"), "%" + orderFilterRequest.getProvince() + "%"));
        }

        if (orderFilterRequest.getPhoneNumber() != null && !orderFilterRequest.getPhoneNumber().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("phoneNumber"), "%" + orderFilterRequest.getPhoneNumber() + "%"));
        }

        if (orderFilterRequest.getEmail() != null && !orderFilterRequest.getEmail().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("email"), "%" + orderFilterRequest.getEmail() + "%"));
        }

        if (orderFilterRequest.getIpAddress() != null && !orderFilterRequest.getIpAddress().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("ipAddress").get("ipAddress"), "%" + orderFilterRequest.getIpAddress() + "%"));
        }

        if (orderFilterRequest.getOrderStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("orderStatus"), orderFilterRequest.getOrderStatus()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
