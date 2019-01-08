package br.com.ferreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.ferreira.domain.Hop;
import br.com.ferreira.repository.HopRepository;
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
 * REST controller for managing Hop.
 */
@RestController
@RequestMapping("/api")
public class HopResource {

    private final Logger log = LoggerFactory.getLogger(HopResource.class);

    private static final String ENTITY_NAME = "hop";

    private final HopRepository hopRepository;

    public HopResource(HopRepository hopRepository) {
        this.hopRepository = hopRepository;
    }

    /**
     * POST  /hops : Create a new hop.
     *
     * @param hop the hop to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hop, or with status 400 (Bad Request) if the hop has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hops")
    @Timed
    public ResponseEntity<Hop> createHop(@RequestBody Hop hop) throws URISyntaxException {
        log.debug("REST request to save Hop : {}", hop);
        if (hop.getId() != null) {
            throw new BadRequestAlertException("A new hop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Hop result = hopRepository.save(hop);
        return ResponseEntity.created(new URI("/api/hops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hops : Updates an existing hop.
     *
     * @param hop the hop to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hop,
     * or with status 400 (Bad Request) if the hop is not valid,
     * or with status 500 (Internal Server Error) if the hop couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hops")
    @Timed
    public ResponseEntity<Hop> updateHop(@RequestBody Hop hop) throws URISyntaxException {
        log.debug("REST request to update Hop : {}", hop);
        if (hop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Hop result = hopRepository.save(hop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hop.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hops : get all the hops.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hops in body
     */
    @GetMapping("/hops")
    @Timed
    public List<Hop> getAllHops() {
        log.debug("REST request to get all Hops");
        return hopRepository.findAll();
    }

    /**
     * GET  /hops/:id : get the "id" hop.
     *
     * @param id the id of the hop to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hop, or with status 404 (Not Found)
     */
    @GetMapping("/hops/{id}")
    @Timed
    public ResponseEntity<Hop> getHop(@PathVariable Long id) {
        log.debug("REST request to get Hop : {}", id);
        Optional<Hop> hop = hopRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hop);
    }

    /**
     * DELETE  /hops/:id : delete the "id" hop.
     *
     * @param id the id of the hop to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hops/{id}")
    @Timed
    public ResponseEntity<Void> deleteHop(@PathVariable Long id) {
        log.debug("REST request to delete Hop : {}", id);

        hopRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
