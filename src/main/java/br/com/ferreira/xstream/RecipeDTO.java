package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("RECIPE")
public class RecipeDTO {

    @XStreamAlias("NAME")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("VERSION")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("TYPE")
    @XStreamAsAttribute
    private String type;

    @XStreamAlias("BREWER")
    @XStreamAsAttribute
    private String brewer;

    @XStreamAlias("ASST_BREWER")
    @XStreamAsAttribute
    private String asstBrewer;

    @XStreamAlias("BATCH_SIZE")
    @XStreamAsAttribute
    private String batchSize;

    @XStreamAlias("BOIL_SIZE")
    @XStreamAsAttribute
    private String boilSize;

    @XStreamAlias("BOIL_TIME")
    @XStreamAsAttribute
    private String boilTime;

    @XStreamAlias("EFFICIENCY")
    @XStreamAsAttribute
    private String efficiency;

    @XStreamAlias("HOPS")
    @XStreamImplicit
    private List<HopDTO> hops = new ArrayList<>();

    @XStreamAlias("FERMENTABLES")
    @XStreamImplicit
    private List<FermentableDTO> fermentables = new ArrayList<>();

    @XStreamAlias("YEASTS")
    @XStreamImplicit
    private List<YeastDTO> yeasts = new ArrayList<>();

    @XStreamAlias("STYLE")
    @XStreamAsAttribute
    private StyleDTO style;

    @XStreamAlias("EQUIPMENT")
    @XStreamAsAttribute
    private EquipamentDTO equipment;

    @XStreamAlias("MASH")
    @XStreamAsAttribute
    private MashDTO mash;

    @XStreamAlias("NOTES")
    @XStreamAsAttribute
    private String notes;

    @XStreamAlias("TASTE_NOTES")
    @XStreamAsAttribute
    private String tasteNotes;

    @XStreamAlias("TASTE_RATING")
    @XStreamAsAttribute
    private String tasteRating;

    @XStreamAlias("OG")
    @XStreamAsAttribute
    private String og;

    @XStreamAlias("FG")
    @XStreamAsAttribute
    private String fg;

    @XStreamAlias("CARBONATION")
    @XStreamAsAttribute
    private String carbonation;

    @XStreamAlias("FERMENTATION_STAGES")
    @XStreamAsAttribute
    private String fermentationStages;

    @XStreamAlias("PRIMARY_AGE")
    @XStreamAsAttribute
    private String primaryAge;

    @XStreamAlias("PRIMARY_TEMP")
    @XStreamAsAttribute
    private String primaryTemp;

    @XStreamAlias("SECONDARY_AGE")
    @XStreamAsAttribute
    private String secondaryAge;

    @XStreamAlias("SECONDARY_TEMP")
    @XStreamAsAttribute
    private String secondaryTemp;

    @XStreamAlias("TERTIARY_AGE")
    @XStreamAsAttribute
    private String tertiaryAge;

    @XStreamAlias("AGE")
    @XStreamAsAttribute
    private String age;

    @XStreamAlias("AGE_TEMP")
    @XStreamAsAttribute
    private String ageTemp;

    @XStreamAlias("CARBONATION_USED")
    @XStreamAsAttribute
    private String carbonationUsed;

    @XStreamAlias("FORCED_CARBONATION")
    @XStreamAsAttribute
    private String forcedCarbonation;

    @XStreamAlias("PRIMING_SUGAR_NAME")
    @XStreamAsAttribute
    private String primingSugarName;

    @XStreamAlias("PRIMING_SUGAR_EQUIV")
    @XStreamAsAttribute
    private String primingSugarEquiv;

    @XStreamAlias("KEG_PRIMING_FACTOR")
    @XStreamAsAttribute
    private String kegPrimingFactor;

    @XStreamAlias("CARBONATION_TEMP")
    @XStreamAsAttribute
    private String carbonationTemp;

    @XStreamAlias("DISPLAY_CARB_TEMP")
    @XStreamAsAttribute
    private String displayCarbTemp;

    @XStreamAlias("DATE")
    @XStreamAsAttribute
    private String date;

    @XStreamAlias("EST_OG")
    @XStreamAsAttribute
    private String estOg;

    @XStreamAlias("EST_FG")
    @XStreamAsAttribute
    private String estFg;

    @XStreamAlias("EST_COLOR")
    @XStreamAsAttribute
    private String estColor;

    @XStreamAlias("IBU")
    @XStreamAsAttribute
    private String ibu;

    @XStreamAlias("IBU_METHOD")
    @XStreamAsAttribute
    private String ibuMethod;

    @XStreamAlias("EST_ABV")
    @XStreamAsAttribute
    private String estAbv;

    @XStreamAlias("ABV")
    @XStreamAsAttribute
    private String abv;

    @XStreamAlias("ACTUAL_EFFICIENCY")
    @XStreamAsAttribute
    private String actualEfficiency;

    @XStreamAlias("CALORIES")
    @XStreamAsAttribute
    private String calories;

    @XStreamAlias("DISPLAY_BATCH_SIZE")
    @XStreamAsAttribute
    private String displayBatchSize;

    @XStreamAlias("DISPLAY_BOIL_SIZE")
    @XStreamAsAttribute
    private String displayBoilSize;

    @XStreamAlias("DISPLAY_OG")
    @XStreamAsAttribute
    private String displayOg;

    @XStreamAlias("DISPLAY_FG")
    @XStreamAsAttribute
    private String displayFg;

    @XStreamAlias("DISPLAY_PRIMARY_TEMP")
    @XStreamAsAttribute
    private String displayPrimaryTemp;

    @XStreamAlias("DISPLAY_SECONDARY_TEMP")
    @XStreamAsAttribute
    private String displaySecondaryTemp;

    @XStreamAlias("DISPLAY_TERTIARY_TEMP")
    @XStreamAsAttribute
    private String displayTertiaryTemp;

    @XStreamAlias("DISPLAY_AGE_TEMP")
    @XStreamAsAttribute
    private String displayAgeTemp;

}
