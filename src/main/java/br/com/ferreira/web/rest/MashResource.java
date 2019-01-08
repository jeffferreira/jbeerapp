package br.com.ferreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.ferreira.domain.Mash;
import br.com.ferreira.repository.MashRepository;
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
 * REST controller for managing Mash.
 */
@RestController
@RequestMapping("/api")
public class MashResource {

    private final Logger log = LoggerFactory.getLogger(MashResource.class);

    private static final String ENTITY_NAME = "mash";

    private final MashRepository mashRepository;

    public MashResource(MashRepository mashRepository) {
        this.mashRepository = mashRepository;
    }

    /**
     * POST  /mashes : Create a new mash.
     *
     * @param mash the mash to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mash, or with status 400 (Bad Request) if the mash has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mashes")
    @Timed
    public ResponseEntity<Mash> createMash(@RequestBody Mash mash) throws URISyntaxException {
        log.debug("REST request to save Mash : {}", mash);
        if (mash.getId() != null) {
            throw new BadRequestAlertException("A new mash cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Mash result = mashRepository.save(mash);
        return ResponseEntity.created(new URI("/api/mashes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mashes : Updates an existing mash.
     *
     * @param mash the mash to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mash,
     * or with status 400 (Bad Request) if the mash is not valid,
     * or with status 500 (Internal Server Error) if the mash couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mashes")
    @Timed
    public ResponseEntity<Mash> updateMash(@RequestBody Mash mash) throws URISyntaxException {
        log.debug("REST request to update Mash : {}", mash);
        if (mash.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Mash result = mashRepository.save(mash);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mash.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mashes : get all the mashes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mashes in body
     */
    @GetMapping("/mashes")
    @Timed
    public List<Mash> getAllMashes() {
        log.debug("REST request to get all Mashes");
        return mashRepository.findAll();
    }

    /**
     * GET  /mashes/:id : get the "id" mash.
     *
     * @param id the id of the mash to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mash, or with status 404 (Not Found)
     */
    @GetMapping("/mashes/{id}")
    @Timed
    public ResponseEntity<Mash> getMash(@PathVariable Long id) {
        log.debug("REST request to get Mash : {}", id);
        Optional<Mash> mash = mashRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mash);
    }

    /**
     * DELETE  /mashes/:id : delete the "id" mash.
     *
     * @param id the id of the mash to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mashes/{id}")
    @Timed
    public ResponseEntity<Void> deleteMash(@PathVariable Long id) {
        log.debug("REST request to delete Mash : {}", id);

        mashRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
