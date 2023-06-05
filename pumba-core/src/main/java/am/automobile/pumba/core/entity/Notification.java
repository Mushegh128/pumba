package am.automobile.pumba.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "notification")
public class Notification extends AbstractEntity {

    @ManyToOne
    private User user;
    private String message;

    @CreationTimestamp
    @Column(name = "sent_date_time", nullable = false, updatable = false)
    private LocalDateTime sentDateTime;

    private boolean isRead;
}
