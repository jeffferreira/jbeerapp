package br.com.ferreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.ferreira.domain.Fermentable;
import br.com.ferreira.repository.FermentableRepository;
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
 * REST controller for managing Fermentable.
 */
@RestController
@RequestMapping("/api")
public class FermentableResource {

    private final Logger log = LoggerFactory.getLogger(FermentableResource.class);

    private static final String ENTITY_NAME = "fermentable";

    private final FermentableRepository fermentableRepository;

    public FermentableResource(FermentableRepository fermentableRepository) {
        this.fermentableRepository = fermentableRepository;
    }

    /**
     * POST  /fermentables : Create a new fermentable.
     *
     * @param fermentable the fermentable to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fermentable, or with status 400 (Bad Request) if the fermentable has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fermentables")
    @Timed
    public ResponseEntity<Fermentable> createFermentable(@RequestBody Fermentable fermentable) throws URISyntaxException {
        log.debug("REST request to save Fermentable : {}", fermentable);
        if (fermentable.getId() != null) {
            throw new BadRequestAlertException("A new fermentable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Fermentable result = fermentableRepository.save(fermentable);
        return ResponseEntity.created(new URI("/api/fermentables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fermentables : Updates an existing fermentable.
     *
     * @param fermentable the fermentable to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fermentable,
     * or with status 400 (Bad Request) if the fermentable is not valid,
     * or with status 500 (Internal Server Error) if the fermentable couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fermentables")
    @Timed
    public ResponseEntity<Fermentable> updateFermentable(@RequestBody Fermentable fermentable) throws URISyntaxException {
        log.debug("REST request to update Fermentable : {}", fermentable);
        if (fermentable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Fermentable result = fermentableRepository.save(fermentable);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fermentable.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fermentables : get all the fermentables.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fermentables in body
     */
    @GetMapping("/fermentables")
    @Timed
    public List<Fermentable> getAllFermentables() {
        log.debug("REST request to get all Fermentables");
        return fermentableRepository.findAll();
    }

    /**
     * GET  /fermentables/:id : get the "id" fermentable.
     *
     * @param id the id of the fermentable to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fermentable, or with status 404 (Not Found)
     */
    @GetMapping("/fermentables/{id}")
    @Timed
    public ResponseEntity<Fermentable> getFermentable(@PathVariable Long id) {
        log.debug("REST request to get Fermentable : {}", id);
        Optional<Fermentable> fermentable = fermentableRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fermentable);
    }

    /**
     * DELETE  /fermentables/:id : delete the "id" fermentable.
     *
     * @param id the id of the fermentable to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fermentables/{id}")
    @Timed
    public ResponseEntity<Void> deleteFermentable(@PathVariable Long id) {
        log.debug("REST request to delete Fermentable : {}", id);

        fermentableRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
