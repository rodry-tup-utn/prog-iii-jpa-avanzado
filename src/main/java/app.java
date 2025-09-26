import DAO.PacienteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.rodry.Entity.HistoriaClinica;
import org.rodry.Entity.Paciente;

import java.util.List;

public class app {
    public static class Main {
        public static void main(String[] args) {
    
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("practicoJPA");
            EntityManager em = emf.createEntityManager();
            PacienteDAO pacienteDao = new PacienteDAO(em, Paciente.class);
    
            try {
                HistoriaClinica clinica = new HistoriaClinica("Historia clinica del paciente Ramirez");
                HistoriaClinica clinica1 = new HistoriaClinica("Historia clinica del paciente Arancibia");
                Paciente paciente = Paciente.builder()
                        .nombre("Rodrigo")
                        .apellido("Ramirez")
                        .edad(34)
                        .obraSocial("Boreal")
                        .dni("36499229")
                        .historiaClinica(clinica)
                        .build();
                Paciente paciente2 = Paciente.builder()
                        .nombre("Rocio")
                        .apellido("Arancibia")
                        .edad(31)
                        .obraSocial("Boreal")
                        .dni("39473146")
                        .historiaClinica(clinica1)
                        .build();
    
                pacienteDao.guardar(paciente);
                pacienteDao.guardar(paciente2);
    
                List<Paciente> listaPacientes = pacienteDao.listarMayoresDe(30);
                listaPacientes.forEach(System.out::println);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                em.close();
                emf.close();
            }
        }
    }
}
