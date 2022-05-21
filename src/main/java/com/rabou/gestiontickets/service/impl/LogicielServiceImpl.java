package com.rabou.gestiontickets.service.impl;

import com.rabou.gestiontickets.domain.Logiciel;
import com.rabou.gestiontickets.repository.LogicielRepository;
import com.rabou.gestiontickets.service.LogicielService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Logiciel}.
 */
@Service
@Transactional
public class LogicielServiceImpl implements LogicielService {

    private final Logger log = LoggerFactory.getLogger(LogicielServiceImpl.class);

    private final LogicielRepository logicielRepository;

    public LogicielServiceImpl(LogicielRepository logicielRepository) {
        this.logicielRepository = logicielRepository;
    }

    @Override
    public Logiciel save(Logiciel logiciel) {
        log.debug("Request to save Logiciel : {}", logiciel);
        return logicielRepository.save(logiciel);
    }

    @Override
    public Logiciel update(Logiciel logiciel) {
        log.debug("Request to save Logiciel : {}", logiciel);
        return logicielRepository.save(logiciel);
    }

    @Override
    public Optional<Logiciel> partialUpdate(Logiciel logiciel) {
        log.debug("Request to partially update Logiciel : {}", logiciel);

        return logicielRepository
            .findById(logiciel.getId())
            .map(existingLogiciel -> {
                if (logiciel.getNom() != null) {
                    existingLogiciel.setNom(logiciel.getNom());
                }
                if (logiciel.getDescription() != null) {
                    existingLogiciel.setDescription(logiciel.getDescription());
                }

                return existingLogiciel;
            })
            .map(logicielRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Logiciel> findAll(Pageable pageable) {
        log.debug("Request to get all Logiciels");
        return logicielRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Logiciel> findOne(Long id) {
        log.debug("Request to get Logiciel : {}", id);
        return logicielRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Logiciel : {}", id);
        logicielRepository.deleteById(id);
    }
}
