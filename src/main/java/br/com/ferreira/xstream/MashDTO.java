package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("MASH")
public class MashDTO {

    @XStreamAlias("NAME")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("VERSION")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("GRAIN_TEMP")
    @XStreamAsAttribute
    private String grainTemp;

    @XStreamAlias("TUN_TEMP")
    @XStreamAsAttribute
    private String tunTemp;

    @XStreamAlias("SPARGE_TEMP")
    @XStreamAsAttribute
    private String spargeTemp;

    @XStreamAlias("PH")
    @XStreamAsAttribute
    private String ph;

    @XStreamAlias("TUN_WEIGHT")
    @XStreamAsAttribute
    private String tunWeight;

    @XStreamAlias("TUN_SPECIFIC_HEAT")
    @XStreamAsAttribute
    private String tunSpecificHeat;

    @XStreamAlias("EQUIP_ADJUST")
    @XStreamAsAttribute
    private String equipAdjust;

    @XStreamAlias("NOTES")
    @XStreamAsAttribute
    private String notes;

    @XStreamAlias("DISPLAY_GRAIN_TEMP")
    @XStreamAsAttribute
    private String displayGrainTemp;

    @XStreamAlias("DISPLAY_TUN_TEMP")
    @XStreamAsAttribute
    private String displayTunTemp;

    @XStreamAlias("DISPLAY_SPARGE_TEMP")
    @XStreamAsAttribute
    private String displaySpargeTemp;

    @XStreamAlias("DISPLAY_TUN_WEIGHT")
    @XStreamAsAttribute
    private String displayTunWeight;

    @XStreamAlias("MASH_STEPS")
    @XStreamImplicit(itemFieldName="MASH_STEP")
    private List<MashStepDTO> mashSteps;
}
