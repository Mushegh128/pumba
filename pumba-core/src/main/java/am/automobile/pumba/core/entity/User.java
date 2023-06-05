package am.automobile.pumba.core.entity;

import com.automobile.pumba.data.transfer.model.UserPermission;
import com.automobile.pumba.data.transfer.model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String phone;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDate createAt;
    private boolean isEnable;

    private boolean isBlocked;
    private LocalDate blockedAt;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserPermission> permissions;
}
