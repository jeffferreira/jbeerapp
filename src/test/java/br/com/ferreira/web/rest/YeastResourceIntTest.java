package br.com.ferreira.web.rest;

import br.com.ferreira.JbeerappApp;

import br.com.ferreira.domain.Yeast;
import br.com.ferreira.repository.YeastRepository;
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
import java.util.List;


import static br.com.ferreira.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the YeastResource REST controller.
 *
 * @see YeastResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JbeerappApp.class)
public class YeastResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    @Autowired
    private YeastRepository yeastRepository;

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

    private MockMvc restYeastMockMvc;

    private Yeast yeast;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final YeastResource yeastResource = new YeastResource(yeastRepository);
        this.restYeastMockMvc = MockMvcBuilders.standaloneSetup(yeastResource)
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
    public static Yeast createEntity(EntityManager em) {
        Yeast yeast = new Yeast()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .laboratory(DEFAULT_LABORATORY)
            .productId(DEFAULT_PRODUCT_ID);
        return yeast;
    }

    @Before
    public void initTest() {
        yeast = createEntity(em);
    }

    @Test
    @Transactional
    public void createYeast() throws Exception {
        int databaseSizeBeforeCreate = yeastRepository.findAll().size();

        // Create the Yeast
        restYeastMockMvc.perform(post("/api/yeasts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yeast)))
            .andExpect(status().isCreated());

        // Validate the Yeast in the database
        List<Yeast> yeastList = yeastRepository.findAll();
        assertThat(yeastList).hasSize(databaseSizeBeforeCreate + 1);
        Yeast testYeast = yeastList.get(yeastList.size() - 1);
        assertThat(testYeast.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testYeast.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testYeast.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testYeast.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
    }

    @Test
    @Transactional
    public void createYeastWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = yeastRepository.findAll().size();

        // Create the Yeast with an existing ID
        yeast.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restYeastMockMvc.perform(post("/api/yeasts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yeast)))
            .andExpect(status().isBadRequest());

        // Validate the Yeast in the database
        List<Yeast> yeastList = yeastRepository.findAll();
        assertThat(yeastList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllYeasts() throws Exception {
        // Initialize the database
        yeastRepository.saveAndFlush(yeast);

        // Get all the yeastList
        restYeastMockMvc.perform(get("/api/yeasts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(yeast.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY.toString())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getYeast() throws Exception {
        // Initialize the database
        yeastRepository.saveAndFlush(yeast);

        // Get the yeast
        restYeastMockMvc.perform(get("/api/yeasts/{id}", yeast.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(yeast.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY.toString()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingYeast() throws Exception {
        // Get the yeast
        restYeastMockMvc.perform(get("/api/yeasts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateYeast() throws Exception {
        // Initialize the database
        yeastRepository.saveAndFlush(yeast);

        int databaseSizeBeforeUpdate = yeastRepository.findAll().size();

        // Update the yeast
        Yeast updatedYeast = yeastRepository.findById(yeast.getId()).get();
        // Disconnect from session so that the updates on updatedYeast are not directly saved in db
        em.detach(updatedYeast);
        updatedYeast
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .laboratory(UPDATED_LABORATORY)
            .productId(UPDATED_PRODUCT_ID);

        restYeastMockMvc.perform(put("/api/yeasts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedYeast)))
            .andExpect(status().isOk());

        // Validate the Yeast in the database
        List<Yeast> yeastList = yeastRepository.findAll();
        assertThat(yeastList).hasSize(databaseSizeBeforeUpdate);
        Yeast testYeast = yeastList.get(yeastList.size() - 1);
        assertThat(testYeast.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testYeast.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testYeast.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testYeast.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingYeast() throws Exception {
        int databaseSizeBeforeUpdate = yeastRepository.findAll().size();

        // Create the Yeast

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restYeastMockMvc.perform(put("/api/yeasts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yeast)))
            .andExpect(status().isBadRequest());

        // Validate the Yeast in the database
        List<Yeast> yeastList = yeastRepository.findAll();
        assertThat(yeastList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteYeast() throws Exception {
        // Initialize the database
        yeastRepository.saveAndFlush(yeast);

        int databaseSizeBeforeDelete = yeastRepository.findAll().size();

        // Get the yeast
        restYeastMockMvc.perform(delete("/api/yeasts/{id}", yeast.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Yeast> yeastList = yeastRepository.findAll();
        assertThat(yeastList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Yeast.class);
        Yeast yeast1 = new Yeast();
        yeast1.setId(1L);
        Yeast yeast2 = new Yeast();
        yeast2.setId(yeast1.getId());
        assertThat(yeast1).isEqualTo(yeast2);
        yeast2.setId(2L);
        assertThat(yeast1).isNotEqualTo(yeast2);
        yeast1.setId(null);
        assertThat(yeast1).isNotEqualTo(yeast2);
    }
}
