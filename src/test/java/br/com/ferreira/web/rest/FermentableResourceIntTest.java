package br.com.ferreira.web.rest;

import br.com.ferreira.JbeerappApp;

import br.com.ferreira.domain.Fermentable;
import br.com.ferreira.repository.FermentableRepository;
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
 * Test class for the FermentableResource REST controller.
 *
 * @see FermentableResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JbeerappApp.class)
public class FermentableResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_AMOUNT = "BBBBBBBBBB";

    @Autowired
    private FermentableRepository fermentableRepository;

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

    private MockMvc restFermentableMockMvc;

    private Fermentable fermentable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FermentableResource fermentableResource = new FermentableResource(fermentableRepository);
        this.restFermentableMockMvc = MockMvcBuilders.standaloneSetup(fermentableResource)
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
    public static Fermentable createEntity(EntityManager em) {
        Fermentable fermentable = new Fermentable()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .amount(DEFAULT_AMOUNT)
            .origin(DEFAULT_ORIGIN)
            .supplier(DEFAULT_SUPPLIER)
            .displayAmount(DEFAULT_DISPLAY_AMOUNT);
        return fermentable;
    }

    @Before
    public void initTest() {
        fermentable = createEntity(em);
    }

    @Test
    @Transactional
    public void createFermentable() throws Exception {
        int databaseSizeBeforeCreate = fermentableRepository.findAll().size();

        // Create the Fermentable
        restFermentableMockMvc.perform(post("/api/fermentables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fermentable)))
            .andExpect(status().isCreated());

        // Validate the Fermentable in the database
        List<Fermentable> fermentableList = fermentableRepository.findAll();
        assertThat(fermentableList).hasSize(databaseSizeBeforeCreate + 1);
        Fermentable testFermentable = fermentableList.get(fermentableList.size() - 1);
        assertThat(testFermentable.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFermentable.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFermentable.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testFermentable.getOrigin()).isEqualTo(DEFAULT_ORIGIN);
        assertThat(testFermentable.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testFermentable.getDisplayAmount()).isEqualTo(DEFAULT_DISPLAY_AMOUNT);
    }

    @Test
    @Transactional
    public void createFermentableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fermentableRepository.findAll().size();

        // Create the Fermentable with an existing ID
        fermentable.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFermentableMockMvc.perform(post("/api/fermentables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fermentable)))
            .andExpect(status().isBadRequest());

        // Validate the Fermentable in the database
        List<Fermentable> fermentableList = fermentableRepository.findAll();
        assertThat(fermentableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFermentables() throws Exception {
        // Initialize the database
        fermentableRepository.saveAndFlush(fermentable);

        // Get all the fermentableList
        restFermentableMockMvc.perform(get("/api/fermentables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fermentable.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].origin").value(hasItem(DEFAULT_ORIGIN.toString())))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER.toString())))
            .andExpect(jsonPath("$.[*].displayAmount").value(hasItem(DEFAULT_DISPLAY_AMOUNT.toString())));
    }
    
    @Test
    @Transactional
    public void getFermentable() throws Exception {
        // Initialize the database
        fermentableRepository.saveAndFlush(fermentable);

        // Get the fermentable
        restFermentableMockMvc.perform(get("/api/fermentables/{id}", fermentable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fermentable.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.origin").value(DEFAULT_ORIGIN.toString()))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER.toString()))
            .andExpect(jsonPath("$.displayAmount").value(DEFAULT_DISPLAY_AMOUNT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFermentable() throws Exception {
        // Get the fermentable
        restFermentableMockMvc.perform(get("/api/fermentables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFermentable() throws Exception {
        // Initialize the database
        fermentableRepository.saveAndFlush(fermentable);

        int databaseSizeBeforeUpdate = fermentableRepository.findAll().size();

        // Update the fermentable
        Fermentable updatedFermentable = fermentableRepository.findById(fermentable.getId()).get();
        // Disconnect from session so that the updates on updatedFermentable are not directly saved in db
        em.detach(updatedFermentable);
        updatedFermentable
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .amount(UPDATED_AMOUNT)
            .origin(UPDATED_ORIGIN)
            .supplier(UPDATED_SUPPLIER)
            .displayAmount(UPDATED_DISPLAY_AMOUNT);

        restFermentableMockMvc.perform(put("/api/fermentables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFermentable)))
            .andExpect(status().isOk());

        // Validate the Fermentable in the database
        List<Fermentable> fermentableList = fermentableRepository.findAll();
        assertThat(fermentableList).hasSize(databaseSizeBeforeUpdate);
        Fermentable testFermentable = fermentableList.get(fermentableList.size() - 1);
        assertThat(testFermentable.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFermentable.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFermentable.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFermentable.getOrigin()).isEqualTo(UPDATED_ORIGIN);
        assertThat(testFermentable.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testFermentable.getDisplayAmount()).isEqualTo(UPDATED_DISPLAY_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingFermentable() throws Exception {
        int databaseSizeBeforeUpdate = fermentableRepository.findAll().size();

        // Create the Fermentable

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFermentableMockMvc.perform(put("/api/fermentables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fermentable)))
            .andExpect(status().isBadRequest());

        // Validate the Fermentable in the database
        List<Fermentable> fermentableList = fermentableRepository.findAll();
        assertThat(fermentableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFermentable() throws Exception {
        // Initialize the database
        fermentableRepository.saveAndFlush(fermentable);

        int databaseSizeBeforeDelete = fermentableRepository.findAll().size();

        // Get the fermentable
        restFermentableMockMvc.perform(delete("/api/fermentables/{id}", fermentable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fermentable> fermentableList = fermentableRepository.findAll();
        assertThat(fermentableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fermentable.class);
        Fermentable fermentable1 = new Fermentable();
        fermentable1.setId(1L);
        Fermentable fermentable2 = new Fermentable();
        fermentable2.setId(fermentable1.getId());
        assertThat(fermentable1).isEqualTo(fermentable2);
        fermentable2.setId(2L);
        assertThat(fermentable1).isNotEqualTo(fermentable2);
        fermentable1.setId(null);
        assertThat(fermentable1).isNotEqualTo(fermentable2);
    }
}
