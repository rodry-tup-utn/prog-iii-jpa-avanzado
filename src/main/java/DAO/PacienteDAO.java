package DAO;

import jakarta.persistence.EntityManager;
import org.rodry.Entity.Paciente;

import java.util.List;

public class PacienteDAO extends GenericDAO<Paciente> {
    
    public PacienteDAO(EntityManager em, Class<Paciente> claseEntidad) {
        super(em, claseEntidad);
    }
    
    //3 listar pacientes mayores a x edad
    public List<Paciente> listarMayoresDe(int edad) {
        return em.createQuery("select p from Paciente p where p.edad > :edad", Paciente.class)
                 .setParameter("edad", edad)
                 .getResultList();
    }
    
    //7 Calcular promedio de edad de los pacientes
    public double calcularPromedioEdad() {
        Number avg = em.createQuery("select avg(p.edad) from Paciente p", Number.class).
                       getSingleResult();
        
        return (avg == null) ? 0.0 : avg.doubleValue();
    }
    
    //8 Listar pacientes con obra social especifica
    public List<Paciente> listarPacientesPorObraSocial(String obraSocial) {
        return em.createQuery("select p from Paciente p where p.obraSocial = :obraSocial", Paciente.class)
                 .setParameter("obraSocial", obraSocial)
                 .getResultList();
    }
    
    //10 Listar paciente con la descripcion de su historia clinica
    public List<Object[]> listarPacienteDescripcionHC() {
        return em.createQuery(
                         "select p.apellido, p.nombre, p.historiaClinica.descripcion from Paciente p", Object[].class)
                 .getResultList();
    }
}
