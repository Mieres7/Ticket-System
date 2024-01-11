package codesyncgroup.ticketsystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "status")
public class StatusEntity {

    @Id
    @Column(name = "id_status")
    private Long idStatus;

    private String status;

}
