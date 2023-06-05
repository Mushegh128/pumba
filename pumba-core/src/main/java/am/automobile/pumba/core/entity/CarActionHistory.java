package am.automobile.pumba.core.entity;

import com.automobile.pumba.data.transfer.model.CarActionType;
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

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_action_history")
public class CarActionHistory extends AbstractEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    @Enumerated(EnumType.STRING)
    private CarActionType actionType;

    @CreationTimestamp
    @Column(name = "action_date_time", nullable = false, updatable = false)
    private LocalDate actionDateTime;
}
