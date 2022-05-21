package com.rabou.gestiontickets.service;

import com.rabou.gestiontickets.domain.Logiciel;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Logiciel}.
 */
public interface LogicielService {
    /**
     * Save a logiciel.
     *
     * @param logiciel the entity to save.
     * @return the persisted entity.
     */
    Logiciel save(Logiciel logiciel);

    /**
     * Updates a logiciel.
     *
     * @param logiciel the entity to update.
     * @return the persisted entity.
     */
    Logiciel update(Logiciel logiciel);

    /**
     * Partially updates a logiciel.
     *
     * @param logiciel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Logiciel> partialUpdate(Logiciel logiciel);

    /**
     * Get all the logiciels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Logiciel> findAll(Pageable pageable);

    /**
     * Get the "id" logiciel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Logiciel> findOne(Long id);

    /**
     * Delete the "id" logiciel.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
