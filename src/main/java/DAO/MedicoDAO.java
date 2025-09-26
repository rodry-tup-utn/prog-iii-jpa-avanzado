package DAO;

import jakarta.persistence.EntityManager;

import java.util.List;

public class MedicoDAO extends GenericDAO {
    
    public MedicoDAO(EntityManager em, Class claseEntidad) {
        super(em, claseEntidad);
    }
}