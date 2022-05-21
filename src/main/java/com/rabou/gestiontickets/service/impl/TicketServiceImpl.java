package com.rabou.gestiontickets.service.impl;

import com.rabou.gestiontickets.domain.Ticket;
import com.rabou.gestiontickets.repository.TicketRepository;
import com.rabou.gestiontickets.service.TicketService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ticket}.
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        log.debug("Request to save Ticket : {}", ticket);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        log.debug("Request to save Ticket : {}", ticket);
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> partialUpdate(Ticket ticket) {
        log.debug("Request to partially update Ticket : {}", ticket);

        return ticketRepository
            .findById(ticket.getId())
            .map(existingTicket -> {
                if (ticket.getDateOuverture() != null) {
                    existingTicket.setDateOuverture(ticket.getDateOuverture());
                }
                if (ticket.getDateCloture() != null) {
                    existingTicket.setDateCloture(ticket.getDateCloture());
                }
                if (ticket.getStatut() != null) {
                    existingTicket.setStatut(ticket.getStatut());
                }
                if (ticket.getDescripition() != null) {
                    existingTicket.setDescripition(ticket.getDescripition());
                }
                if (ticket.getUrgence() != null) {
                    existingTicket.setUrgence(ticket.getUrgence());
                }
                if (ticket.getEnvironnement() != null) {
                    existingTicket.setEnvironnement(ticket.getEnvironnement());
                }

                return existingTicket;
            })
            .map(ticketRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ticket> findAll(Pageable pageable) {
        log.debug("Request to get all Tickets");
        return ticketRepository.findAll(pageable);
    }

    public Page<Ticket> findAllWithEagerRelationships(Pageable pageable) {
        return ticketRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ticket> findOne(Long id) {
        log.debug("Request to get Ticket : {}", id);
        return ticketRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ticket : {}", id);
        ticketRepository.deleteById(id);
    }
}
