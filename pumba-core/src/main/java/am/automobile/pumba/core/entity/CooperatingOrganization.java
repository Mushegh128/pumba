package am.automobile.pumba.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cooperating_organization")
public class CooperatingOrganization extends AbstractEntity {

    private String name;
    private String logo;
    private String description;
    private String website;
}
