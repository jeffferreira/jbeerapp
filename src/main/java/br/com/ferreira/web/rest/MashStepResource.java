package br.com.ferreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.ferreira.domain.MashStep;
import br.com.ferreira.repository.MashStepRepository;
import br.com.ferreira.web.rest.errors.BadRequestAlertException;
import br.com.ferreira.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MashStep.
 */
@RestController
@RequestMapping("/api")
public class MashStepResource {

    private final Logger log = LoggerFactory.getLogger(MashStepResource.class);

    private static final String ENTITY_NAME = "mashStep";

    private final MashStepRepository mashStepRepository;

    public MashStepResource(MashStepRepository mashStepRepository) {
        this.mashStepRepository = mashStepRepository;
    }

    /**
     * POST  /mash-steps : Create a new mashStep.
     *
     * @param mashStep the mashStep to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mashStep, or with status 400 (Bad Request) if the mashStep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mash-steps")
    @Timed
    public ResponseEntity<MashStep> createMashStep(@RequestBody MashStep mashStep) throws URISyntaxException {
        log.debug("REST request to save MashStep : {}", mashStep);
        if (mashStep.getId() != null) {
            throw new BadRequestAlertException("A new mashStep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MashStep result = mashStepRepository.save(mashStep);
        return ResponseEntity.created(new URI("/api/mash-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mash-steps : Updates an existing mashStep.
     *
     * @param mashStep the mashStep to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mashStep,
     * or with status 400 (Bad Request) if the mashStep is not valid,
     * or with status 500 (Internal Server Error) if the mashStep couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mash-steps")
    @Timed
    public ResponseEntity<MashStep> updateMashStep(@RequestBody MashStep mashStep) throws URISyntaxException {
        log.debug("REST request to update MashStep : {}", mashStep);
        if (mashStep.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MashStep result = mashStepRepository.save(mashStep);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mashStep.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mash-steps : get all the mashSteps.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mashSteps in body
     */
    @GetMapping("/mash-steps")
    @Timed
    public List<MashStep> getAllMashSteps() {
        log.debug("REST request to get all MashSteps");
        return mashStepRepository.findAll();
    }

    /**
     * GET  /mash-steps/:id : get the "id" mashStep.
     *
     * @param id the id of the mashStep to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mashStep, or with status 404 (Not Found)
     */
    @GetMapping("/mash-steps/{id}")
    @Timed
    public ResponseEntity<MashStep> getMashStep(@PathVariable Long id) {
        log.debug("REST request to get MashStep : {}", id);
        Optional<MashStep> mashStep = mashStepRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mashStep);
    }

    /**
     * DELETE  /mash-steps/:id : delete the "id" mashStep.
     *
     * @param id the id of the mashStep to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mash-steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteMashStep(@PathVariable Long id) {
        log.debug("REST request to delete MashStep : {}", id);

        mashStepRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
