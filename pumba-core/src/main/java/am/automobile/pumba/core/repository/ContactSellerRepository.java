package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.ContactSeller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactSellerRepository extends JpaRepository<ContactSeller, Long> {

    Optional<ContactSeller> findByIdAndIsDeleteIsFalse(long id);

    Page<ContactSeller> findAllByIsDeleteIsFalse(Pageable pageable);
}
