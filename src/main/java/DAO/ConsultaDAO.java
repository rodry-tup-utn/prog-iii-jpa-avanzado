package DAO;

import jakarta.persistence.EntityManager;
import org.rodry.Entity.Consulta;
import org.rodry.Entity.Medico;

import java.util.List;

public class ConsultaDAO extends GenericDAO {
    
    public ConsultaDAO(EntityManager em, Class claseEntidad) {
        super(em, claseEntidad);
    }
    
    //4 Listar consultas realizadas por un medico especifico
    public List<Consulta> listarConsultasPorMedico(Medico medico) {
        return em.createQuery("select c from Consulta c where c.medico = :medico", Consulta.class)
                 .setParameter("medico", medico)
                 .getResultList();
    }
    
    //6 Listar consultas con diagnostico y paciente
    public List<Object[]> listarDiagnosticoyPaciente() {
        return em.createQuery("select c.paciente.apellido, c.paciente.nombre, c.diagnostico" +
                              " from Consulta c", Object[].class)
                 .getResultList();
    }
    
    //9 Mostrar medicos con cantidad de consultas
    public List<Object[]> listarMedicoConCantConsultas() {
        return em.createQuery(
                         "select c.medico.nombre, count(c) " +
                         "from Consulta c " +
                         "group by c.medico", Object[].class)
                 .getResultList();
    }
    
}