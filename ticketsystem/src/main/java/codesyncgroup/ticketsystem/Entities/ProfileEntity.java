package codesyncgroup.ticketsystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class ProfileEntity {
    @Id
    @Column(name = "id_profile", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProfile;
    String firstname;
    String middlename;
    String lastname;
    String email;
    String descriptionProfile;
    String gender;
    Date birthday;
    String pictureProfile;
}
