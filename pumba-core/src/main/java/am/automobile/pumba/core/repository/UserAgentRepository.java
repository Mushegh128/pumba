package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.UserAgent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAgentRepository extends JpaRepository<UserAgent, Long> {
}
