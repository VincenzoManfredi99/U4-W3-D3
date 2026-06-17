package vincenzomanfredi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vincenzomanfredi.entities.Persona;

public class PersonaDAO {

    private final EntityManager entityManager;

    public PersonaDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Persona newPersona) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(newPersona);
        transaction.commit();
        System.out.println("La persona " + newPersona + " è stata salvata nel DB!");
    }

    public Persona findById(long id) {
        Persona fromDB = this.entityManager.find(Persona.class, id);
        if (fromDB == null) {
            throw new RuntimeException("Persona con id " + id + " non trovata!");
        }
        return fromDB;
    }

    public void findByIdAndDelete(long id) {
        Persona fromDB = this.findById(id);
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.remove(fromDB);
        transaction.commit();
        System.out.println("La persona " + fromDB + " è stata rimossa dal DB!");
    }
}
