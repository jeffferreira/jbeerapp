package br.com.ferreira.web.rest;

import br.com.ferreira.JbeerappApp;

import br.com.ferreira.domain.Hop;
import br.com.ferreira.repository.HopRepository;
import br.com.ferreira.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


import static br.com.ferreira.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HopResource REST controller.
 *
 * @see HopResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JbeerappApp.class)
public class HopResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ALPHA = new BigDecimal(1);
    private static final BigDecimal UPDATED_ALPHA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_USE = "AAAAAAAAAA";
    private static final String UPDATED_USE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TIME = new BigDecimal(1);
    private static final BigDecimal UPDATED_TIME = new BigDecimal(2);

    private static final String DEFAULT_DISPLAY_TIME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_AMOUNT = "BBBBBBBBBB";

    @Autowired
    private HopRepository hopRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restHopMockMvc;

    private Hop hop;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HopResource hopResource = new HopResource(hopRepository);
        this.restHopMockMvc = MockMvcBuilders.standaloneSetup(hopResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hop createEntity(EntityManager em) {
        Hop hop = new Hop()
            .name(DEFAULT_NAME)
            .origin(DEFAULT_ORIGIN)
            .alpha(DEFAULT_ALPHA)
            .amount(DEFAULT_AMOUNT)
            .use(DEFAULT_USE)
            .time(DEFAULT_TIME)
            .displayTime(DEFAULT_DISPLAY_TIME)
            .displayAmount(DEFAULT_DISPLAY_AMOUNT);
        return hop;
    }

    @Before
    public void initTest() {
        hop = createEntity(em);
    }

    @Test
    @Transactional
    public void createHop() throws Exception {
        int databaseSizeBeforeCreate = hopRepository.findAll().size();

        // Create the Hop
        restHopMockMvc.perform(post("/api/hops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hop)))
            .andExpect(status().isCreated());

        // Validate the Hop in the database
        List<Hop> hopList = hopRepository.findAll();
        assertThat(hopList).hasSize(databaseSizeBeforeCreate + 1);
        Hop testHop = hopList.get(hopList.size() - 1);
        assertThat(testHop.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHop.getOrigin()).isEqualTo(DEFAULT_ORIGIN);
        assertThat(testHop.getAlpha()).isEqualTo(DEFAULT_ALPHA);
        assertThat(testHop.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testHop.getUse()).isEqualTo(DEFAULT_USE);
        assertThat(testHop.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testHop.getDisplayTime()).isEqualTo(DEFAULT_DISPLAY_TIME);
        assertThat(testHop.getDisplayAmount()).isEqualTo(DEFAULT_DISPLAY_AMOUNT);
    }

    @Test
    @Transactional
    public void createHopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hopRepository.findAll().size();

        // Create the Hop with an existing ID
        hop.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHopMockMvc.perform(post("/api/hops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hop)))
            .andExpect(status().isBadRequest());

        // Validate the Hop in the database
        List<Hop> hopList = hopRepository.findAll();
        assertThat(hopList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHops() throws Exception {
        // Initialize the database
        hopRepository.saveAndFlush(hop);

        // Get all the hopList
        restHopMockMvc.perform(get("/api/hops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hop.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].origin").value(hasItem(DEFAULT_ORIGIN.toString())))
            .andExpect(jsonPath("$.[*].alpha").value(hasItem(DEFAULT_ALPHA.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].use").value(hasItem(DEFAULT_USE.toString())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].displayTime").value(hasItem(DEFAULT_DISPLAY_TIME.toString())))
            .andExpect(jsonPath("$.[*].displayAmount").value(hasItem(DEFAULT_DISPLAY_AMOUNT.toString())));
    }
    
    @Test
    @Transactional
    public void getHop() throws Exception {
        // Initialize the database
        hopRepository.saveAndFlush(hop);

        // Get the hop
        restHopMockMvc.perform(get("/api/hops/{id}", hop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hop.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.origin").value(DEFAULT_ORIGIN.toString()))
            .andExpect(jsonPath("$.alpha").value(DEFAULT_ALPHA.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.use").value(DEFAULT_USE.toString()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.intValue()))
            .andExpect(jsonPath("$.displayTime").value(DEFAULT_DISPLAY_TIME.toString()))
            .andExpect(jsonPath("$.displayAmount").value(DEFAULT_DISPLAY_AMOUNT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHop() throws Exception {
        // Get the hop
        restHopMockMvc.perform(get("/api/hops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHop() throws Exception {
        // Initialize the database
        hopRepository.saveAndFlush(hop);

        int databaseSizeBeforeUpdate = hopRepository.findAll().size();

        // Update the hop
        Hop updatedHop = hopRepository.findById(hop.getId()).get();
        // Disconnect from session so that the updates on updatedHop are not directly saved in db
        em.detach(updatedHop);
        updatedHop
            .name(UPDATED_NAME)
            .origin(UPDATED_ORIGIN)
            .alpha(UPDATED_ALPHA)
            .amount(UPDATED_AMOUNT)
            .use(UPDATED_USE)
            .time(UPDATED_TIME)
            .displayTime(UPDATED_DISPLAY_TIME)
            .displayAmount(UPDATED_DISPLAY_AMOUNT);

        restHopMockMvc.perform(put("/api/hops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHop)))
            .andExpect(status().isOk());

        // Validate the Hop in the database
        List<Hop> hopList = hopRepository.findAll();
        assertThat(hopList).hasSize(databaseSizeBeforeUpdate);
        Hop testHop = hopList.get(hopList.size() - 1);
        assertThat(testHop.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHop.getOrigin()).isEqualTo(UPDATED_ORIGIN);
        assertThat(testHop.getAlpha()).isEqualTo(UPDATED_ALPHA);
        assertThat(testHop.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testHop.getUse()).isEqualTo(UPDATED_USE);
        assertThat(testHop.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testHop.getDisplayTime()).isEqualTo(UPDATED_DISPLAY_TIME);
        assertThat(testHop.getDisplayAmount()).isEqualTo(UPDATED_DISPLAY_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingHop() throws Exception {
        int databaseSizeBeforeUpdate = hopRepository.findAll().size();

        // Create the Hop

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHopMockMvc.perform(put("/api/hops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hop)))
            .andExpect(status().isBadRequest());

        // Validate the Hop in the database
        List<Hop> hopList = hopRepository.findAll();
        assertThat(hopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHop() throws Exception {
        // Initialize the database
        hopRepository.saveAndFlush(hop);

        int databaseSizeBeforeDelete = hopRepository.findAll().size();

        // Get the hop
        restHopMockMvc.perform(delete("/api/hops/{id}", hop.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Hop> hopList = hopRepository.findAll();
        assertThat(hopList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hop.class);
        Hop hop1 = new Hop();
        hop1.setId(1L);
        Hop hop2 = new Hop();
        hop2.setId(hop1.getId());
        assertThat(hop1).isEqualTo(hop2);
        hop2.setId(2L);
        assertThat(hop1).isNotEqualTo(hop2);
        hop1.setId(null);
        assertThat(hop1).isNotEqualTo(hop2);
    }
}
