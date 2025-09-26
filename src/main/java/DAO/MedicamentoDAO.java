package DAO;

import jakarta.persistence.EntityManager;
import org.rodry.Entity.Medicamento;
import org.rodry.Entity.Paciente;

import java.util.List;

public class MedicamentoDAO extends GenericDAO<Medicamento> {
    
    public MedicamentoDAO(EntityManager em, Class<Medicamento> claseEntidad) {
        super(em, claseEntidad);
    }
    
    //5 Mostrar todos los medicamentos asociados a un paciente
    public List<Medicamento> listarMedicamentosPaciente(Paciente paciente) {
        return em.createQuery("select m from Medicamento m" +
                        " join m.listaPaciente p " +
                        "where p = :paciente", Medicamento.class)
                .setParameter("paciente", paciente)
                .getResultList();
    }
}
