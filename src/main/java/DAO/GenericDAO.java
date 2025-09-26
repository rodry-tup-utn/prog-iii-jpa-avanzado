package DAO;

import jakarta.persistence.EntityManager;
import org.hibernate.metamodel.mapping.EntityIdentifierMapping;
import org.rodry.Entity.Medico;

public class GenericDAO<T> {
    protected EntityManager em;
    private Class<T> claseEntidad;

    public GenericDAO(EntityManager em, Class<T> claseEntidad) {
        this.em = em;
        this.claseEntidad = claseEntidad;
    }

    public void guardar(T entidad) {
        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public T buscarPorId(Long id){
        return em.find(claseEntidad, id);
    }
}
