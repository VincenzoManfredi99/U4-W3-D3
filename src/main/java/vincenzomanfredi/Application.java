package vincenzomanfredi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vincenzomanfredi.dao.EventoDAO;
import vincenzomanfredi.entities.Evento;
import vincenzomanfredi.entities.TipoEvento;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestioneeventipu");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EventoDAO eventoDAO = new EventoDAO(entityManager);


        Evento party = new Evento("Concerto Live", LocalDate.now(), "Musica rock sotto le stelle", TipoEvento.PUBBLICO, 500);

        eventoDAO.save(party);

        try {
            Evento found = eventoDAO.findById(1);
            System.out.println(found);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        try {
            eventoDAO.findByIdAndDelete(2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}