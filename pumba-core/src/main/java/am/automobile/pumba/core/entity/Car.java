package am.automobile.pumba.core.entity;

import com.automobile.pumba.data.transfer.model.CarTracking;
import com.automobile.pumba.data.transfer.model.MileageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car extends AbstractEntity {

    @JoinColumn(nullable = false)
    @ManyToOne
    private User owner;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    private CarMake make;
    @ManyToOne
    private CarModel model;
    private Integer year;
    private String color;
    private Integer cylinders;

    private Integer mileage;
    @Enumerated(EnumType.STRING)
    private MileageType mileageType;
    private BigDecimal price;
    private String vin;
    private String licensePlate;

    @ManyToOne
    private CarFuelType fuelType;
    private Integer numberOfDoors;
    private Integer numberOfSeats;
    @ManyToOne
    private CarTransmissionType transmission;

    @ManyToOne
    private CarEngineType engineType;
    @ManyToOne
    private CarDrivetrainType drivetrainType;
    @ManyToOne
    private ContactPhone contactPhone;
    @ManyToOne
    private ContactEmail contactEmail;
    private String description;

    private String location;
    private String videoLink;
    private String baseImage;
    @OneToMany(mappedBy = "car")
    private List<CarImage> imageDetails;
    @Enumerated(EnumType.STRING)
    private CarTracking tracking;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDate createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDate updateAt;

    private Boolean isPublic;
    private Boolean requested;
    private Boolean isApproved;
}
