package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IpAddressRepository extends JpaRepository<IpAddress, Long> {

    Optional<IpAddress> findByIpAddress(String address);
}
