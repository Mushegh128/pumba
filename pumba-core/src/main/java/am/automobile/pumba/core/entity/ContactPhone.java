package am.automobile.pumba.core.entity;

import jakarta.persistence.Column;
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
@Table(name = "contact_phone")
public class ContactPhone extends AbstractEntity {

    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String name;
    private Boolean whatsapp;
}
