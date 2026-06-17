package vincenzomanfredi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "eventi")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titolo;
    private LocalDate dataEvento;
    private String descrizione;

    @Column(name = "tipo_evento")
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    @Column(name = "numero_massimo_partecipanti")
    private int numMassimoPartecipanti;

    //Attributo per la relazione
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Evento() {
    }


    public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numMassimoPartecipanti, Location location) {
        this.titolo = titolo;
        this.dataEvento = dataEvento;
        this.descrizione = descrizione;
        this.tipoEvento = tipoEvento;
        this.numMassimoPartecipanti = numMassimoPartecipanti;
        this.location = location;
    }


    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumMassimoPartecipanti() {
        return numMassimoPartecipanti;
    }

    public void setNumMassimoPartecipanti(int numMassimoPartecipanti) {
        this.numMassimoPartecipanti = numMassimoPartecipanti;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", numMassimoPartecipanti=" + numMassimoPartecipanti +
                ", location " + location +
                '}';
    }
}