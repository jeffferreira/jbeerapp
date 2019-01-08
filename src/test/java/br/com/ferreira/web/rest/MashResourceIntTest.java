package br.com.ferreira.web.rest;

import br.com.ferreira.JbeerappApp;

import br.com.ferreira.domain.Mash;
import br.com.ferreira.repository.MashRepository;
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
 * Test class for the MashResource REST controller.
 *
 * @see MashResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JbeerappApp.class)
public class MashResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PH = new BigDecimal(1);
    private static final BigDecimal UPDATED_PH = new BigDecimal(2);

    @Autowired
    private MashRepository mashRepository;

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

    private MockMvc restMashMockMvc;

    private Mash mash;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MashResource mashResource = new MashResource(mashRepository);
        this.restMashMockMvc = MockMvcBuilders.standaloneSetup(mashResource)
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
    public static Mash createEntity(EntityManager em) {
        Mash mash = new Mash()
            .name(DEFAULT_NAME)
            .ph(DEFAULT_PH);
        return mash;
    }

    @Before
    public void initTest() {
        mash = createEntity(em);
    }

    @Test
    @Transactional
    public void createMash() throws Exception {
        int databaseSizeBeforeCreate = mashRepository.findAll().size();

        // Create the Mash
        restMashMockMvc.perform(post("/api/mashes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mash)))
            .andExpect(status().isCreated());

        // Validate the Mash in the database
        List<Mash> mashList = mashRepository.findAll();
        assertThat(mashList).hasSize(databaseSizeBeforeCreate + 1);
        Mash testMash = mashList.get(mashList.size() - 1);
        assertThat(testMash.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMash.getPh()).isEqualTo(DEFAULT_PH);
    }

    @Test
    @Transactional
    public void createMashWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mashRepository.findAll().size();

        // Create the Mash with an existing ID
        mash.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMashMockMvc.perform(post("/api/mashes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mash)))
            .andExpect(status().isBadRequest());

        // Validate the Mash in the database
        List<Mash> mashList = mashRepository.findAll();
        assertThat(mashList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMashes() throws Exception {
        // Initialize the database
        mashRepository.saveAndFlush(mash);

        // Get all the mashList
        restMashMockMvc.perform(get("/api/mashes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mash.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].ph").value(hasItem(DEFAULT_PH.intValue())));
    }
    
    @Test
    @Transactional
    public void getMash() throws Exception {
        // Initialize the database
        mashRepository.saveAndFlush(mash);

        // Get the mash
        restMashMockMvc.perform(get("/api/mashes/{id}", mash.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mash.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.ph").value(DEFAULT_PH.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMash() throws Exception {
        // Get the mash
        restMashMockMvc.perform(get("/api/mashes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMash() throws Exception {
        // Initialize the database
        mashRepository.saveAndFlush(mash);

        int databaseSizeBeforeUpdate = mashRepository.findAll().size();

        // Update the mash
        Mash updatedMash = mashRepository.findById(mash.getId()).get();
        // Disconnect from session so that the updates on updatedMash are not directly saved in db
        em.detach(updatedMash);
        updatedMash
            .name(UPDATED_NAME)
            .ph(UPDATED_PH);

        restMashMockMvc.perform(put("/api/mashes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMash)))
            .andExpect(status().isOk());

        // Validate the Mash in the database
        List<Mash> mashList = mashRepository.findAll();
        assertThat(mashList).hasSize(databaseSizeBeforeUpdate);
        Mash testMash = mashList.get(mashList.size() - 1);
        assertThat(testMash.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMash.getPh()).isEqualTo(UPDATED_PH);
    }

    @Test
    @Transactional
    public void updateNonExistingMash() throws Exception {
        int databaseSizeBeforeUpdate = mashRepository.findAll().size();

        // Create the Mash

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMashMockMvc.perform(put("/api/mashes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mash)))
            .andExpect(status().isBadRequest());

        // Validate the Mash in the database
        List<Mash> mashList = mashRepository.findAll();
        assertThat(mashList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMash() throws Exception {
        // Initialize the database
        mashRepository.saveAndFlush(mash);

        int databaseSizeBeforeDelete = mashRepository.findAll().size();

        // Get the mash
        restMashMockMvc.perform(delete("/api/mashes/{id}", mash.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Mash> mashList = mashRepository.findAll();
        assertThat(mashList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mash.class);
        Mash mash1 = new Mash();
        mash1.setId(1L);
        Mash mash2 = new Mash();
        mash2.setId(mash1.getId());
        assertThat(mash1).isEqualTo(mash2);
        mash2.setId(2L);
        assertThat(mash1).isNotEqualTo(mash2);
        mash1.setId(null);
        assertThat(mash1).isNotEqualTo(mash2);
    }
}
