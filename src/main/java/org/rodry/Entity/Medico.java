package org.rodry.Entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medico  extends Base{
    private String nombre;
    private String apelido;
    private int edad;
    private String especialidad;
    private String matricula;

}
