package vincenzomanfredi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vincenzomanfredi.entities.Location;

public class LocationDAO {

    private final EntityManager entityManager;

    public LocationDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Location newLocation) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(newLocation);
        transaction.commit();
        System.out.println("La location " + newLocation + " è stata salvata nel DB!");
    }

    public Location findById(long id) {
        Location fromDB = this.entityManager.find(Location.class, id);
        if (fromDB == null) {
            throw new RuntimeException("Location con id " + id + " non trovata!");
        }
        return fromDB;
    }

    public void findByIdAndDelete(long id) {
        Location fromDB = this.findById(id);
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.remove(fromDB);
        transaction.commit();
        System.out.println("La location " + fromDB + " è stata rimossa dal DB!");
    }
}
