package com.rabou.gestiontickets.service;

import com.rabou.gestiontickets.domain.Ticket;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Ticket}.
 */
public interface TicketService {
    /**
     * Save a ticket.
     *
     * @param ticket the entity to save.
     * @return the persisted entity.
     */
    Ticket save(Ticket ticket);

    /**
     * Updates a ticket.
     *
     * @param ticket the entity to update.
     * @return the persisted entity.
     */
    Ticket update(Ticket ticket);

    /**
     * Partially updates a ticket.
     *
     * @param ticket the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Ticket> partialUpdate(Ticket ticket);

    /**
     * Get all the tickets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Ticket> findAll(Pageable pageable);

    /**
     * Get all the tickets with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Ticket> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" ticket.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Ticket> findOne(Long id);

    /**
     * Delete the "id" ticket.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
