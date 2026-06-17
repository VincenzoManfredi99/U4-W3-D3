package vincenzomanfredi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vincenzomanfredi.entities.Evento;
// import vincenzomanfredi.exceptions.NotFoundException; // Decommenta se hai creato questa eccezione

public class EventoDAO {

    private final EntityManager entityManager;

    public EventoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Evento newEvento) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(newEvento);
        transaction.commit();
        System.out.println("L'evento " + newEvento + " è stato salvato nel DB!");
    }

    public Evento findById(long id) {
        // Cerchiamo l'evento tramite id. Se non trova niente torna null
        Evento fromDB = this.entityManager.find(Evento.class, id);

        if (fromDB == null) {
            // Se preferisci non usare l'eccezione custom, metti un semplice System.out.println
            throw new RuntimeException("Evento con id " + id + " non trovato!");
            // oppure: throw new NotFoundException(id);
        }

        return fromDB;
    }

    public void findByIdAndDelete(long id) {
        // 1. Cerchiamo l'evento tramite id riciclando il metodo findById
        Evento fromDB = this.findById(id);

        // 2. Creiamo una transazione
        EntityTransaction transaction = this.entityManager.getTransaction();

        // 3. Facciamo partire la transazione
        transaction.begin();

        // 4. Informiamo l'EntityManager che l'evento è da cancellare tramite .remove()
        this.entityManager.remove(fromDB);

        // 5. Il commit sincronizza il PersistenceContext con il DB eliminando la riga
        transaction.commit();

        // 6. Log di avvenuta cancellazione
        System.out.println("L'evento " + fromDB + " è stato rimosso dal DB!");
    }
}