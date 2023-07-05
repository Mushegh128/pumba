package am.automobile.pumba.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Builder
@Entity
@Table(name = "order_join_request")
public class OrderJoinRequest extends AbstractEntity {

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Order order;

    @ManyToOne
    private User approveUser;

    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne
    private User requestSenderUser;
    private boolean approve;
    private boolean cancel;

    private LocalDateTime approveAt;
    private LocalDateTime cancelAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
}
