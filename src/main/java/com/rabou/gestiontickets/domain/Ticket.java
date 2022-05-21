package com.rabou.gestiontickets.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rabou.gestiontickets.domain.enumeration.TicketStatut;
import com.rabou.gestiontickets.domain.enumeration.TicketUrgence;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Ticket.
 */
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_ouverture")
    private LocalDate dateOuverture;

    @Column(name = "date_cloture")
    private LocalDate dateCloture;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private TicketStatut statut;

    @Lob
    @Column(name = "descripition")
    private String descripition;

    @Enumerated(EnumType.STRING)
    @Column(name = "urgence")
    private TicketUrgence urgence;

    @Column(name = "environnement")
    private String environnement;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tickets" }, allowSetters = true)
    private Logiciel logiciel;

    @ManyToOne
    private User developpeur;

    @ManyToOne
    private User client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ticket id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOuverture() {
        return this.dateOuverture;
    }

    public Ticket dateOuverture(LocalDate dateOuverture) {
        this.setDateOuverture(dateOuverture);
        return this;
    }

    public void setDateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public LocalDate getDateCloture() {
        return this.dateCloture;
    }

    public Ticket dateCloture(LocalDate dateCloture) {
        this.setDateCloture(dateCloture);
        return this;
    }

    public void setDateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
    }

    public TicketStatut getStatut() {
        return this.statut;
    }

    public Ticket statut(TicketStatut statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(TicketStatut statut) {
        this.statut = statut;
    }

    public String getDescripition() {
        return this.descripition;
    }

    public Ticket descripition(String descripition) {
        this.setDescripition(descripition);
        return this;
    }

    public void setDescripition(String descripition) {
        this.descripition = descripition;
    }

    public TicketUrgence getUrgence() {
        return this.urgence;
    }

    public Ticket urgence(TicketUrgence urgence) {
        this.setUrgence(urgence);
        return this;
    }

    public void setUrgence(TicketUrgence urgence) {
        this.urgence = urgence;
    }

    public String getEnvironnement() {
        return this.environnement;
    }

    public Ticket environnement(String environnement) {
        this.setEnvironnement(environnement);
        return this;
    }

    public void setEnvironnement(String environnement) {
        this.environnement = environnement;
    }

    public Logiciel getLogiciel() {
        return this.logiciel;
    }

    public void setLogiciel(Logiciel logiciel) {
        this.logiciel = logiciel;
    }

    public Ticket logiciel(Logiciel logiciel) {
        this.setLogiciel(logiciel);
        return this;
    }

    public User getDeveloppeur() {
        return this.developpeur;
    }

    public void setDeveloppeur(User user) {
        this.developpeur = user;
    }

    public Ticket developpeur(User user) {
        this.setDeveloppeur(user);
        return this;
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(User user) {
        this.client = user;
    }

    public Ticket client(User user) {
        this.setClient(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ticket{" +
            "id=" + getId() +
            ", dateOuverture='" + getDateOuverture() + "'" +
            ", dateCloture='" + getDateCloture() + "'" +
            ", statut='" + getStatut() + "'" +
            ", descripition='" + getDescripition() + "'" +
            ", urgence='" + getUrgence() + "'" +
            ", environnement='" + getEnvironnement() + "'" +
            "}";
    }
}
