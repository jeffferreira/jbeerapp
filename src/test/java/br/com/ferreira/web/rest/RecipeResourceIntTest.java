package br.com.ferreira.web.rest;

import br.com.ferreira.JbeerappApp;

import br.com.ferreira.domain.Recipe;
import br.com.ferreira.repository.RecipeRepository;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static br.com.ferreira.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RecipeResource REST controller.
 *
 * @see RecipeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JbeerappApp.class)
public class RecipeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BREWER = "AAAAAAAAAA";
    private static final String UPDATED_BREWER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BATCH_SIZE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BATCH_SIZE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BOIL_SIZE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BOIL_SIZE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BOIL_TIME = new BigDecimal(1);
    private static final BigDecimal UPDATED_BOIL_TIME = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EFICIENCY = new BigDecimal(1);
    private static final BigDecimal UPDATED_EFICIENCY = new BigDecimal(2);

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_IBU = "AAAAAAAAAA";
    private static final String UPDATED_IBU = "BBBBBBBBBB";

    private static final String DEFAULT_EST_ABV = "AAAAAAAAAA";
    private static final String UPDATED_EST_ABV = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_BATCH_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_BATCH_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_OG = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_OG = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_FG = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_FG = "BBBBBBBBBB";

    @Autowired
    private RecipeRepository recipeRepository;

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

    private MockMvc restRecipeMockMvc;

    private Recipe recipe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecipeResource recipeResource = new RecipeResource(recipeRepository);
        this.restRecipeMockMvc = MockMvcBuilders.standaloneSetup(recipeResource)
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
    public static Recipe createEntity(EntityManager em) {
        Recipe recipe = new Recipe()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .brewer(DEFAULT_BREWER)
            .batchSize(DEFAULT_BATCH_SIZE)
            .boilSize(DEFAULT_BOIL_SIZE)
            .boilTime(DEFAULT_BOIL_TIME)
            .eficiency(DEFAULT_EFICIENCY)
            .date(DEFAULT_DATE)
            .ibu(DEFAULT_IBU)
            .estAbv(DEFAULT_EST_ABV)
            .displayBatchSize(DEFAULT_DISPLAY_BATCH_SIZE)
            .displayOg(DEFAULT_DISPLAY_OG)
            .displayFg(DEFAULT_DISPLAY_FG);
        return recipe;
    }

    @Before
    public void initTest() {
        recipe = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecipe() throws Exception {
        int databaseSizeBeforeCreate = recipeRepository.findAll().size();

        // Create the Recipe
        restRecipeMockMvc.perform(post("/api/recipes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recipe)))
            .andExpect(status().isCreated());

        // Validate the Recipe in the database
        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(databaseSizeBeforeCreate + 1);
        Recipe testRecipe = recipeList.get(recipeList.size() - 1);
        assertThat(testRecipe.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRecipe.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testRecipe.getBrewer()).isEqualTo(DEFAULT_BREWER);
        assertThat(testRecipe.getBatchSize()).isEqualTo(DEFAULT_BATCH_SIZE);
        assertThat(testRecipe.getBoilSize()).isEqualTo(DEFAULT_BOIL_SIZE);
        assertThat(testRecipe.getBoilTime()).isEqualTo(DEFAULT_BOIL_TIME);
        assertThat(testRecipe.getEficiency()).isEqualTo(DEFAULT_EFICIENCY);
        assertThat(testRecipe.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testRecipe.getIbu()).isEqualTo(DEFAULT_IBU);
        assertThat(testRecipe.getEstAbv()).isEqualTo(DEFAULT_EST_ABV);
        assertThat(testRecipe.getDisplayBatchSize()).isEqualTo(DEFAULT_DISPLAY_BATCH_SIZE);
        assertThat(testRecipe.getDisplayOg()).isEqualTo(DEFAULT_DISPLAY_OG);
        assertThat(testRecipe.getDisplayFg()).isEqualTo(DEFAULT_DISPLAY_FG);
    }

    @Test
    @Transactional
    public void createRecipeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recipeRepository.findAll().size();

        // Create the Recipe with an existing ID
        recipe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecipeMockMvc.perform(post("/api/recipes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recipe)))
            .andExpect(status().isBadRequest());

        // Validate the Recipe in the database
        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRecipes() throws Exception {
        // Initialize the database
        recipeRepository.saveAndFlush(recipe);

        // Get all the recipeList
        restRecipeMockMvc.perform(get("/api/recipes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recipe.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].brewer").value(hasItem(DEFAULT_BREWER.toString())))
            .andExpect(jsonPath("$.[*].batchSize").value(hasItem(DEFAULT_BATCH_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].boilSize").value(hasItem(DEFAULT_BOIL_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].boilTime").value(hasItem(DEFAULT_BOIL_TIME.intValue())))
            .andExpect(jsonPath("$.[*].eficiency").value(hasItem(DEFAULT_EFICIENCY.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].ibu").value(hasItem(DEFAULT_IBU.toString())))
            .andExpect(jsonPath("$.[*].estAbv").value(hasItem(DEFAULT_EST_ABV.toString())))
            .andExpect(jsonPath("$.[*].displayBatchSize").value(hasItem(DEFAULT_DISPLAY_BATCH_SIZE.toString())))
            .andExpect(jsonPath("$.[*].displayOg").value(hasItem(DEFAULT_DISPLAY_OG.toString())))
            .andExpect(jsonPath("$.[*].displayFg").value(hasItem(DEFAULT_DISPLAY_FG.toString())));
    }
    
    @Test
    @Transactional
    public void getRecipe() throws Exception {
        // Initialize the database
        recipeRepository.saveAndFlush(recipe);

        // Get the recipe
        restRecipeMockMvc.perform(get("/api/recipes/{id}", recipe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recipe.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.brewer").value(DEFAULT_BREWER.toString()))
            .andExpect(jsonPath("$.batchSize").value(DEFAULT_BATCH_SIZE.intValue()))
            .andExpect(jsonPath("$.boilSize").value(DEFAULT_BOIL_SIZE.intValue()))
            .andExpect(jsonPath("$.boilTime").value(DEFAULT_BOIL_TIME.intValue()))
            .andExpect(jsonPath("$.eficiency").value(DEFAULT_EFICIENCY.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.ibu").value(DEFAULT_IBU.toString()))
            .andExpect(jsonPath("$.estAbv").value(DEFAULT_EST_ABV.toString()))
            .andExpect(jsonPath("$.displayBatchSize").value(DEFAULT_DISPLAY_BATCH_SIZE.toString()))
            .andExpect(jsonPath("$.displayOg").value(DEFAULT_DISPLAY_OG.toString()))
            .andExpect(jsonPath("$.displayFg").value(DEFAULT_DISPLAY_FG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRecipe() throws Exception {
        // Get the recipe
        restRecipeMockMvc.perform(get("/api/recipes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecipe() throws Exception {
        // Initialize the database
        recipeRepository.saveAndFlush(recipe);

        int databaseSizeBeforeUpdate = recipeRepository.findAll().size();

        // Update the recipe
        Recipe updatedRecipe = recipeRepository.findById(recipe.getId()).get();
        // Disconnect from session so that the updates on updatedRecipe are not directly saved in db
        em.detach(updatedRecipe);
        updatedRecipe
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .brewer(UPDATED_BREWER)
            .batchSize(UPDATED_BATCH_SIZE)
            .boilSize(UPDATED_BOIL_SIZE)
            .boilTime(UPDATED_BOIL_TIME)
            .eficiency(UPDATED_EFICIENCY)
            .date(UPDATED_DATE)
            .ibu(UPDATED_IBU)
            .estAbv(UPDATED_EST_ABV)
            .displayBatchSize(UPDATED_DISPLAY_BATCH_SIZE)
            .displayOg(UPDATED_DISPLAY_OG)
            .displayFg(UPDATED_DISPLAY_FG);

        restRecipeMockMvc.perform(put("/api/recipes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRecipe)))
            .andExpect(status().isOk());

        // Validate the Recipe in the database
        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(databaseSizeBeforeUpdate);
        Recipe testRecipe = recipeList.get(recipeList.size() - 1);
        assertThat(testRecipe.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRecipe.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testRecipe.getBrewer()).isEqualTo(UPDATED_BREWER);
        assertThat(testRecipe.getBatchSize()).isEqualTo(UPDATED_BATCH_SIZE);
        assertThat(testRecipe.getBoilSize()).isEqualTo(UPDATED_BOIL_SIZE);
        assertThat(testRecipe.getBoilTime()).isEqualTo(UPDATED_BOIL_TIME);
        assertThat(testRecipe.getEficiency()).isEqualTo(UPDATED_EFICIENCY);
        assertThat(testRecipe.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testRecipe.getIbu()).isEqualTo(UPDATED_IBU);
        assertThat(testRecipe.getEstAbv()).isEqualTo(UPDATED_EST_ABV);
        assertThat(testRecipe.getDisplayBatchSize()).isEqualTo(UPDATED_DISPLAY_BATCH_SIZE);
        assertThat(testRecipe.getDisplayOg()).isEqualTo(UPDATED_DISPLAY_OG);
        assertThat(testRecipe.getDisplayFg()).isEqualTo(UPDATED_DISPLAY_FG);
    }

    @Test
    @Transactional
    public void updateNonExistingRecipe() throws Exception {
        int databaseSizeBeforeUpdate = recipeRepository.findAll().size();

        // Create the Recipe

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecipeMockMvc.perform(put("/api/recipes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recipe)))
            .andExpect(status().isBadRequest());

        // Validate the Recipe in the database
        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecipe() throws Exception {
        // Initialize the database
        recipeRepository.saveAndFlush(recipe);

        int databaseSizeBeforeDelete = recipeRepository.findAll().size();

        // Get the recipe
        restRecipeMockMvc.perform(delete("/api/recipes/{id}", recipe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recipe.class);
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(recipe1.getId());
        assertThat(recipe1).isEqualTo(recipe2);
        recipe2.setId(2L);
        assertThat(recipe1).isNotEqualTo(recipe2);
        recipe1.setId(null);
        assertThat(recipe1).isNotEqualTo(recipe2);
    }
}
