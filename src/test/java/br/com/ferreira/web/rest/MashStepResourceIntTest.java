package br.com.ferreira.web.rest;

import br.com.ferreira.JbeerappApp;

import br.com.ferreira.domain.MashStep;
import br.com.ferreira.repository.MashStepRepository;
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
 * Test class for the MashStepResource REST controller.
 *
 * @see MashStepResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JbeerappApp.class)
public class MashStepResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_STEP_TIME = new BigDecimal(1);
    private static final BigDecimal UPDATED_STEP_TIME = new BigDecimal(2);

    private static final BigDecimal DEFAULT_STEP_TEMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_STEP_TEMP = new BigDecimal(2);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INFUSE_TEMP = "AAAAAAAAAA";
    private static final String UPDATED_INFUSE_TEMP = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_STEP_TEMP = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_STEP_TEMP = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_INFUSE_AMT = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_INFUSE_AMT = "BBBBBBBBBB";

    @Autowired
    private MashStepRepository mashStepRepository;

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

    private MockMvc restMashStepMockMvc;

    private MashStep mashStep;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MashStepResource mashStepResource = new MashStepResource(mashStepRepository);
        this.restMashStepMockMvc = MockMvcBuilders.standaloneSetup(mashStepResource)
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
    public static MashStep createEntity(EntityManager em) {
        MashStep mashStep = new MashStep()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .stepTime(DEFAULT_STEP_TIME)
            .stepTemp(DEFAULT_STEP_TEMP)
            .description(DEFAULT_DESCRIPTION)
            .infuseTemp(DEFAULT_INFUSE_TEMP)
            .displayStepTemp(DEFAULT_DISPLAY_STEP_TEMP)
            .displayInfuseAmt(DEFAULT_DISPLAY_INFUSE_AMT);
        return mashStep;
    }

    @Before
    public void initTest() {
        mashStep = createEntity(em);
    }

    @Test
    @Transactional
    public void createMashStep() throws Exception {
        int databaseSizeBeforeCreate = mashStepRepository.findAll().size();

        // Create the MashStep
        restMashStepMockMvc.perform(post("/api/mash-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mashStep)))
            .andExpect(status().isCreated());

        // Validate the MashStep in the database
        List<MashStep> mashStepList = mashStepRepository.findAll();
        assertThat(mashStepList).hasSize(databaseSizeBeforeCreate + 1);
        MashStep testMashStep = mashStepList.get(mashStepList.size() - 1);
        assertThat(testMashStep.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMashStep.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMashStep.getStepTime()).isEqualTo(DEFAULT_STEP_TIME);
        assertThat(testMashStep.getStepTemp()).isEqualTo(DEFAULT_STEP_TEMP);
        assertThat(testMashStep.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMashStep.getInfuseTemp()).isEqualTo(DEFAULT_INFUSE_TEMP);
        assertThat(testMashStep.getDisplayStepTemp()).isEqualTo(DEFAULT_DISPLAY_STEP_TEMP);
        assertThat(testMashStep.getDisplayInfuseAmt()).isEqualTo(DEFAULT_DISPLAY_INFUSE_AMT);
    }

    @Test
    @Transactional
    public void createMashStepWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mashStepRepository.findAll().size();

        // Create the MashStep with an existing ID
        mashStep.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMashStepMockMvc.perform(post("/api/mash-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mashStep)))
            .andExpect(status().isBadRequest());

        // Validate the MashStep in the database
        List<MashStep> mashStepList = mashStepRepository.findAll();
        assertThat(mashStepList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMashSteps() throws Exception {
        // Initialize the database
        mashStepRepository.saveAndFlush(mashStep);

        // Get all the mashStepList
        restMashStepMockMvc.perform(get("/api/mash-steps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mashStep.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].stepTime").value(hasItem(DEFAULT_STEP_TIME.intValue())))
            .andExpect(jsonPath("$.[*].stepTemp").value(hasItem(DEFAULT_STEP_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].infuseTemp").value(hasItem(DEFAULT_INFUSE_TEMP.toString())))
            .andExpect(jsonPath("$.[*].displayStepTemp").value(hasItem(DEFAULT_DISPLAY_STEP_TEMP.toString())))
            .andExpect(jsonPath("$.[*].displayInfuseAmt").value(hasItem(DEFAULT_DISPLAY_INFUSE_AMT.toString())));
    }
    
    @Test
    @Transactional
    public void getMashStep() throws Exception {
        // Initialize the database
        mashStepRepository.saveAndFlush(mashStep);

        // Get the mashStep
        restMashStepMockMvc.perform(get("/api/mash-steps/{id}", mashStep.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mashStep.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.stepTime").value(DEFAULT_STEP_TIME.intValue()))
            .andExpect(jsonPath("$.stepTemp").value(DEFAULT_STEP_TEMP.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.infuseTemp").value(DEFAULT_INFUSE_TEMP.toString()))
            .andExpect(jsonPath("$.displayStepTemp").value(DEFAULT_DISPLAY_STEP_TEMP.toString()))
            .andExpect(jsonPath("$.displayInfuseAmt").value(DEFAULT_DISPLAY_INFUSE_AMT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMashStep() throws Exception {
        // Get the mashStep
        restMashStepMockMvc.perform(get("/api/mash-steps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMashStep() throws Exception {
        // Initialize the database
        mashStepRepository.saveAndFlush(mashStep);

        int databaseSizeBeforeUpdate = mashStepRepository.findAll().size();

        // Update the mashStep
        MashStep updatedMashStep = mashStepRepository.findById(mashStep.getId()).get();
        // Disconnect from session so that the updates on updatedMashStep are not directly saved in db
        em.detach(updatedMashStep);
        updatedMashStep
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .stepTime(UPDATED_STEP_TIME)
            .stepTemp(UPDATED_STEP_TEMP)
            .description(UPDATED_DESCRIPTION)
            .infuseTemp(UPDATED_INFUSE_TEMP)
            .displayStepTemp(UPDATED_DISPLAY_STEP_TEMP)
            .displayInfuseAmt(UPDATED_DISPLAY_INFUSE_AMT);

        restMashStepMockMvc.perform(put("/api/mash-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMashStep)))
            .andExpect(status().isOk());

        // Validate the MashStep in the database
        List<MashStep> mashStepList = mashStepRepository.findAll();
        assertThat(mashStepList).hasSize(databaseSizeBeforeUpdate);
        MashStep testMashStep = mashStepList.get(mashStepList.size() - 1);
        assertThat(testMashStep.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMashStep.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMashStep.getStepTime()).isEqualTo(UPDATED_STEP_TIME);
        assertThat(testMashStep.getStepTemp()).isEqualTo(UPDATED_STEP_TEMP);
        assertThat(testMashStep.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMashStep.getInfuseTemp()).isEqualTo(UPDATED_INFUSE_TEMP);
        assertThat(testMashStep.getDisplayStepTemp()).isEqualTo(UPDATED_DISPLAY_STEP_TEMP);
        assertThat(testMashStep.getDisplayInfuseAmt()).isEqualTo(UPDATED_DISPLAY_INFUSE_AMT);
    }

    @Test
    @Transactional
    public void updateNonExistingMashStep() throws Exception {
        int databaseSizeBeforeUpdate = mashStepRepository.findAll().size();

        // Create the MashStep

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMashStepMockMvc.perform(put("/api/mash-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mashStep)))
            .andExpect(status().isBadRequest());

        // Validate the MashStep in the database
        List<MashStep> mashStepList = mashStepRepository.findAll();
        assertThat(mashStepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMashStep() throws Exception {
        // Initialize the database
        mashStepRepository.saveAndFlush(mashStep);

        int databaseSizeBeforeDelete = mashStepRepository.findAll().size();

        // Get the mashStep
        restMashStepMockMvc.perform(delete("/api/mash-steps/{id}", mashStep.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MashStep> mashStepList = mashStepRepository.findAll();
        assertThat(mashStepList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MashStep.class);
        MashStep mashStep1 = new MashStep();
        mashStep1.setId(1L);
        MashStep mashStep2 = new MashStep();
        mashStep2.setId(mashStep1.getId());
        assertThat(mashStep1).isEqualTo(mashStep2);
        mashStep2.setId(2L);
        assertThat(mashStep1).isNotEqualTo(mashStep2);
        mashStep1.setId(null);
        assertThat(mashStep1).isNotEqualTo(mashStep2);
    }
}
