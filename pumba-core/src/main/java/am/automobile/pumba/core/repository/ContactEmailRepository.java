package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.ContactEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactEmailRepository extends JpaRepository<ContactEmail, Long> {
}
