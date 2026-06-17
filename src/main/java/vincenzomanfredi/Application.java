package vincenzomanfredi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vincenzomanfredi.dao.EventoDAO;
import vincenzomanfredi.dao.LocationDAO;
import vincenzomanfredi.dao.PartecipazioniDAO;
import vincenzomanfredi.dao.PersonaDAO;
import vincenzomanfredi.entities.*;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestioneeventipu");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EventoDAO eventoDAO = new EventoDAO(entityManager);
        LocationDAO locationDAO = new LocationDAO(entityManager);
        PersonaDAO personaDAO = new PersonaDAO(entityManager);
        PartecipazioniDAO partecipazioneDAO = new PartecipazioniDAO(entityManager);


        try {

            Location stadio = new Location("Alianz", "Torino");
            locationDAO.save(stadio);

            Persona utente = new Persona("Vincenzo", "Manfredi", "vincenzo@email.com", LocalDate.of(1998, 5, 20), Sesso.M);
            personaDAO.save(utente);

            Evento concerto = new Evento("Rock in Roma", LocalDate.now().plusDays(10), "Grande concerto rock", TipoEvento.PUBBLICO, 40000, stadio);
            eventoDAO.save(concerto);

            Partecipazione iscrizione = new Partecipazione(utente, concerto, StatoPartecipazione.CONFERMATA);
            partecipazioneDAO.save(iscrizione);

            System.out.println("--- SALVATAGGI COMPLETATI CON SUCCESSO ---");
        } catch (Exception e) {
            System.err.println("Si è verificato un errore durante i test:");
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}