package org.rodry.Entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class HistoriaClinica extends Base {
    private String descripcion;
}
