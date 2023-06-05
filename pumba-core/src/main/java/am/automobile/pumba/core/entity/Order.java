package am.automobile.pumba.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
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
    private String email;
    private String message;
    private String phoneNumber;
    private String ipAddress;
    private String userAgent;

    private boolean isDeleted;
    private LocalDateTime deletedAt;

    private boolean isBin;
    private LocalDateTime binAt;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDate createAt;
}
