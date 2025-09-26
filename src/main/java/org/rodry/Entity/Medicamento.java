package org.rodry.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicamento extends Base {
    private String nombre;
    private String droga;
    private int pesoEnGramos;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "medicamento_paciente",
    joinColumns = @JoinColumn(name = "medicamento_id"),
    inverseJoinColumns = @JoinColumn(name = "paciente_id"))
    private List<Paciente> listaPaciente = new ArrayList<>();
}
