package com.rabou.gestiontickets.repository;

import com.rabou.gestiontickets.domain.Ticket;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Ticket entity.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("select ticket from Ticket ticket where ticket.developpeur.login = ?#{principal.username}")
    List<Ticket> findByDeveloppeurIsCurrentUser();

    @Query("select ticket from Ticket ticket where ticket.client.login = ?#{principal.username}")
    List<Ticket> findByClientIsCurrentUser();

    default Optional<Ticket> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Ticket> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Ticket> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct ticket from Ticket ticket left join fetch ticket.logiciel left join fetch ticket.developpeur left join fetch ticket.client",
        countQuery = "select count(distinct ticket) from Ticket ticket"
    )
    Page<Ticket> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct ticket from Ticket ticket left join fetch ticket.logiciel left join fetch ticket.developpeur left join fetch ticket.client"
    )
    List<Ticket> findAllWithToOneRelationships();

    @Query(
        "select ticket from Ticket ticket left join fetch ticket.logiciel left join fetch ticket.developpeur left join fetch ticket.client where ticket.id =:id"
    )
    Optional<Ticket> findOneWithToOneRelationships(@Param("id") Long id);
}
