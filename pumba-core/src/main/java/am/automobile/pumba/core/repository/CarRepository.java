package am.automobile.pumba.core.repository;

import am.automobile.pumba.core.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    @Modifying
    @Query("UPDATE Car c SET c.fuelType = null WHERE c.fuelType.id = :fuelTypeId")
    void updateFuelTypeToNull(Long fuelTypeId);

    @Modifying
    @Query("UPDATE Car c SET c.drivetrainType = null WHERE c.drivetrainType.id = :drivetrainTypeId")
    void updateDrivetrainTypeToNull(Long drivetrainTypeId);

    @Modifying
    @Query("UPDATE Car c SET c.engineType = null WHERE c.engineType.id = :engineId")
    void updateEngineTypeToNull(Long engineId);

    @Modifying
    @Query("UPDATE Car c SET c.make = null WHERE c.make.id = :makeId")
    void updateMakeToNull(Long makeId);

    @Modifying
    @Query("UPDATE Car c SET c.model = null WHERE c.model.id = :modelId")
    void updateModelToNull(Long modelId);

    @Modifying
    @Query("UPDATE Car c SET c.transmission = null WHERE c.transmission.id = :transmissionId")
    void updateTransmissionToNull(Long transmissionId);

    @Modifying
    @Query("UPDATE Car c SET c.contactEmail = null WHERE c.contactEmail.id = :contactEmailId")
    void updateContactEmailToNull(Long contactEmailId);

    @Modifying
    @Query("UPDATE Car c SET c.contactPhone = null WHERE c.contactPhone.id = :contactPhoneId")
    void updateContactPhoneIdToNull(Long contactPhoneId);
}
