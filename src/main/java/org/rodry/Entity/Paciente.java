package org.rodry.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paciente extends Base {
    private String nombre;
    private String apellido;
    private int edad;
    private String dni;
    private String obraSocial;
    private LocalDate fechaNacimiento;
    private Sexo sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "historiaClinica_id")
    private HistoriaClinica historiaClinica;
}
