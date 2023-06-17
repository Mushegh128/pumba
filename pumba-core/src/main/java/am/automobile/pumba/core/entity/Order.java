package am.automobile.pumba.core.entity;

import com.automobile.pumba.data.transfer.model.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String streetAddress;
    private String address;
    private String postcodeZip;
    private String city;
    private String province;
    private String phoneNumber;
    private String email;
    private String message;

    @ManyToOne
    private User manager;
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(updatable = false, nullable = false)
    private IpAddress ipAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
}
