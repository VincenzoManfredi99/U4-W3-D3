package vincenzomanfredi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vincenzomanfredi.entities.Partecipazione;

public class PartecipazioniDAO {

    private final EntityManager entityManager;

    public PartecipazioniDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Partecipazione newPartecipazione) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(newPartecipazione);
        transaction.commit();
        System.out.println("La partecipazione " + newPartecipazione + " è stata salvata nel DB!");
    }

    public Partecipazione findById(long id) {
        Partecipazione fromDB = this.entityManager.find(Partecipazione.class, id);
        if (fromDB == null) {
            throw new RuntimeException("Partecipazione con id " + id + " non trovata!");
        }
        return fromDB;
    }

    public void findByIdAndDelete(long id) {
        Partecipazione fromDB = this.findById(id);
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.remove(fromDB);
        transaction.commit();
        System.out.println("La partecipazione " + fromDB + " è stata rimossa dal DB!");
    }
}
