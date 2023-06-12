package am.automobile.pumba.core.entity;

import com.automobile.pumba.data.transfer.model.MileageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car extends AbstractEntity {

    @ManyToOne
    private CarMake make;
    @ManyToOne
    private CarModel model;
    private int year;
    private String color;

    private int mileage;
    @Enumerated(EnumType.STRING)
    private MileageType mileageType;
    private BigDecimal price;
    private String vin;
    private String licensePlate;

    @ManyToOne
    private CarFuelType fuelType;
    private int numberOfDoors;
    private int numberOfSeats;
    @ManyToOne
    private CarTransmissionType transmission;

    @ManyToOne
    private CarEngineType engineType;
    @ManyToOne
    private CarDrivetrainType drivetrainType;
    private String description;

    private String place;
    private boolean inAuction;
    private boolean hasArrived;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDate createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDate updateAt;

    private boolean isPublic;
}
