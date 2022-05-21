package com.rabou.gestiontickets.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Logiciel.
 */
@Entity
@Table(name = "logiciel")
public class Logiciel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "logiciel")
    @JsonIgnoreProperties(value = { "logiciel", "developpeur", "client" }, allowSetters = true)
    private Set<Ticket> tickets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Logiciel id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Logiciel nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public Logiciel description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Ticket> getTickets() {
        return this.tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        if (this.tickets != null) {
            this.tickets.forEach(i -> i.setLogiciel(null));
        }
        if (tickets != null) {
            tickets.forEach(i -> i.setLogiciel(this));
        }
        this.tickets = tickets;
    }

    public Logiciel tickets(Set<Ticket> tickets) {
        this.setTickets(tickets);
        return this;
    }

    public Logiciel addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setLogiciel(this);
        return this;
    }

    public Logiciel removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
        ticket.setLogiciel(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Logiciel)) {
            return false;
        }
        return id != null && id.equals(((Logiciel) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Logiciel{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
