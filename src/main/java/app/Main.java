package app;

import DAO.ConsultaDAO;
import DAO.MedicamentoDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.rodry.Entity.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practicoJPA");
        EntityManager em = emf.createEntityManager();
        PacienteDAO pacienteDao = new PacienteDAO(em, Paciente.class);
        MedicoDAO medicoDao = new MedicoDAO(em, Medico.class);
        ConsultaDAO consultaDao = new ConsultaDAO(em, Consulta.class);
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO(em, Medicamento.class);
        
        try {
            Paciente paciente = Paciente.builder()
                                        .nombre("Rodrigo")
                                        .apellido("Ramirez")
                                        .edad(34)
                                        .obraSocial("Boreal")
                                        .dni("44333554")
                                        .historiaClinica(new HistoriaClinica("Historia clinica del paciente Ramirez"))
                                        .build();
            Paciente paciente2 = Paciente.builder()
                                         .nombre("Rocio")
                                         .apellido("Arancibia")
                                         .edad(31)
                                         .obraSocial("Boreal")
                                         .dni("22333444")
                                         .historiaClinica(new HistoriaClinica("Historia clinica del paciente Arancibia"))
                                         .build();
            
            Paciente paciente3 = Paciente.builder()
                                         .nombre("Fernando")
                                         .apellido("Suarez")
                                         .dni("345554444")
                                         .edad(25)
                                         .obraSocial("Medife")
                                         .historiaClinica(new HistoriaClinica("Historia clinica el paciente Suarez"))
                                         .build();
            pacienteDao.guardar(paciente);
            pacienteDao.guardar(paciente2);
            pacienteDao.guardar(paciente3);
            
            Medico medico1 =  Medico.builder()
                                      .apelido("Fernandez")
                                      .nombre("Juan")
                                      .especialidad("Traumatologia")
                                      .edad(38)
                                      .matricula("12214")
                                      .build();
            
            Medico medico2 =  Medico.builder()
                                    .apelido("Rodriguez")
                                    .nombre("Victoria")
                                    .especialidad("Clinica")
                                    .edad(35)
                                    .matricula("13244")
                                    .build();
            
            medicoDao.guardar(medico1);
            medicoDao.guardar(medico2);
            
            Consulta consulta1 = Consulta.builder()
                                         .fecha(LocalDate.of(2025, 8, 20))
                                         .medico(medico1)
                                         .paciente(paciente)
                                         .diagnostico("Traumatismo tobillo derecho")
                                         .build();
            
            Consulta consulta2 = Consulta.builder()
                                         .fecha(LocalDate.of(2025, 8, 20))
                                         .medico(medico2)
                                         .paciente(paciente2)
                                         .diagnostico("Tos y fiebre")
                                         .build();
            
            Consulta consulta3 = Consulta.builder()
                                         .fecha(LocalDate.of(2025, 8, 20))
                                         .medico(medico1)
                                         .paciente(paciente3)
                                         .diagnostico("Fractura de muñeca derecha")
                                         .build();
            
            Consulta consulta4 = Consulta.builder()
                                         .fecha(LocalDate.of(2025, 8, 20))
                                         .medico(medico2)
                                         .paciente(paciente)
                                         .diagnostico("Control anual")
                                         .build();
            
            consultaDao.guardar(consulta1);
            consultaDao.guardar(consulta2);
            consultaDao.guardar(consulta3);
            consultaDao.guardar(consulta4);
            
            Medicamento medicamento1 = Medicamento.builder()
                                              .nombre("Tafirol")
                                              .droga("Paracetamol")
                                              .pesoEnGramos(4)
                                              .listaPaciente(List.of(paciente, paciente2))
                                              .build();
            
            Medicamento medicamento2 = Medicamento.builder()
                                                 .nombre("Actron")
                                                 .droga("Ibuprofeno")
                                                 .pesoEnGramos(4)
                                                 .listaPaciente(List.of(paciente))
                                                 .build();
            
            Medicamento medicamento3 = Medicamento.builder()
                                                 .nombre("Tostop")
                                                 .droga("Bromhexina")
                                                 .pesoEnGramos(4)
                                                 .listaPaciente(List.of(paciente3))
                                                 .build();
            
            medicamentoDAO.guardar(medicamento1);
            medicamentoDAO.guardar(medicamento2);
            medicamentoDAO.guardar(medicamento3);
            
            System.out.println("        ***Lista de pacientes mayores de 30 años***");
            pacienteDao.listarMayoresDe(30).forEach(System.out::println);
            
            System.out.println("        ***Consultas de medico en especifico***");
            consultaDao.listarConsultasPorMedico(medico1).forEach(System.out::println);
            
            System.out.println("        ***Medicamentos asociados a un paciente***");
            medicamentoDAO.listarMedicamentosPaciente(paciente).forEach(System.out::println);
            
            System.out.println("        ***Consultas con diagnostico y paciente***");
            consultaDao.listarDiagnosticoyPaciente().forEach(fila -> {
                        String apellido = (String) fila[0];
                        String nombre = (String) fila[1];
                        String diagnostico = (String) fila[2];
                
                System.out.println("Paciente " + apellido + ", " +  nombre + ", " + diagnostico);
                    });
                    
            
            System.out.println("        ***Promedio de edadd de los pacientes***");
            System.out.println(pacienteDao.calcularPromedioEdad());
            
            System.out.println("        ***Pacientes con obra social Boreal***");
            pacienteDao.listarPacientesPorObraSocial("Boreal").forEach(System.out::println);
            
            System.out.println("        ***Medicos y cantidad de consultas***");
            consultaDao.listarConsultasPorMedico(medico2).forEach(System.out::println);
            
            System.out.println("        ***Pacientes con historia clinica***");
            pacienteDao.listarPacienteDescripcionHC().forEach(fila -> {
                String apellido = (String) fila[0];
                String nombre = (String) fila[1];
                String descripcionHC = (String) fila[2];
                
                System.out.println("Paciente: " + nombre + " " + apellido + " - Historia Clinica: " + descripcionHC);
            });
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

