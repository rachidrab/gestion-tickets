package com.rabou.gestiontickets.web.rest;

import com.rabou.gestiontickets.domain.Logiciel;
import com.rabou.gestiontickets.repository.LogicielRepository;
import com.rabou.gestiontickets.service.LogicielService;
import com.rabou.gestiontickets.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.rabou.gestiontickets.domain.Logiciel}.
 */
@RestController
@RequestMapping("/api")
public class LogicielResource {

    private final Logger log = LoggerFactory.getLogger(LogicielResource.class);

    private static final String ENTITY_NAME = "logiciel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LogicielService logicielService;

    private final LogicielRepository logicielRepository;

    public LogicielResource(LogicielService logicielService, LogicielRepository logicielRepository) {
        this.logicielService = logicielService;
        this.logicielRepository = logicielRepository;
    }

    /**
     * {@code POST  /logiciels} : Create a new logiciel.
     *
     * @param logiciel the logiciel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new logiciel, or with status {@code 400 (Bad Request)} if the logiciel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/logiciels")
    public ResponseEntity<Logiciel> createLogiciel(@Valid @RequestBody Logiciel logiciel) throws URISyntaxException {
        log.debug("REST request to save Logiciel : {}", logiciel);
        if (logiciel.getId() != null) {
            throw new BadRequestAlertException("A new logiciel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Logiciel result = logicielService.save(logiciel);
        return ResponseEntity
            .created(new URI("/api/logiciels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /logiciels/:id} : Updates an existing logiciel.
     *
     * @param id the id of the logiciel to save.
     * @param logiciel the logiciel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated logiciel,
     * or with status {@code 400 (Bad Request)} if the logiciel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the logiciel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/logiciels/{id}")
    public ResponseEntity<Logiciel> updateLogiciel(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Logiciel logiciel
    ) throws URISyntaxException {
        log.debug("REST request to update Logiciel : {}, {}", id, logiciel);
        if (logiciel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, logiciel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!logicielRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Logiciel result = logicielService.update(logiciel);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, logiciel.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /logiciels/:id} : Partial updates given fields of an existing logiciel, field will ignore if it is null
     *
     * @param id the id of the logiciel to save.
     * @param logiciel the logiciel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated logiciel,
     * or with status {@code 400 (Bad Request)} if the logiciel is not valid,
     * or with status {@code 404 (Not Found)} if the logiciel is not found,
     * or with status {@code 500 (Internal Server Error)} if the logiciel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/logiciels/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Logiciel> partialUpdateLogiciel(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Logiciel logiciel
    ) throws URISyntaxException {
        log.debug("REST request to partial update Logiciel partially : {}, {}", id, logiciel);
        if (logiciel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, logiciel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!logicielRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Logiciel> result = logicielService.partialUpdate(logiciel);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, logiciel.getId().toString())
        );
    }

    /**
     * {@code GET  /logiciels} : get all the logiciels.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of logiciels in body.
     */
    @GetMapping("/logiciels")
    public ResponseEntity<List<Logiciel>> getAllLogiciels(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Logiciels");
        Page<Logiciel> page = logicielService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /logiciels/:id} : get the "id" logiciel.
     *
     * @param id the id of the logiciel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the logiciel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/logiciels/{id}")
    public ResponseEntity<Logiciel> getLogiciel(@PathVariable Long id) {
        log.debug("REST request to get Logiciel : {}", id);
        Optional<Logiciel> logiciel = logicielService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logiciel);
    }

    /**
     * {@code DELETE  /logiciels/:id} : delete the "id" logiciel.
     *
     * @param id the id of the logiciel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/logiciels/{id}")
    public ResponseEntity<Void> deleteLogiciel(@PathVariable Long id) {
        log.debug("REST request to delete Logiciel : {}", id);
        logicielService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
