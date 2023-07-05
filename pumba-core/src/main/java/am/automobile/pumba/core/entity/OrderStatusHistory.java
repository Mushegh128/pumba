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
import lombok.Builder;
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
@Builder
@Table(name = "order_status_history")
public class OrderStatusHistory extends AbstractEntity {

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private OrderStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
}
