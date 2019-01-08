package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("MASH_STEP")
public class MashStepDTO {

    @XStreamAlias("NAME")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("VERSION")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("TYPE")
    @XStreamAsAttribute
    private String type;

    @XStreamAlias("INFUSE_AMOUNT")
    @XStreamAsAttribute
    private String infuseAmount;

    @XStreamAlias("STEP_TIME")
    @XStreamAsAttribute
    private String stepTime;

    @XStreamAlias("STEP_TEMP")
    @XStreamAsAttribute
    private String stepTemp;

    @XStreamAlias("RAMP_TIME")
    @XStreamAsAttribute
    private String rampTime;

    @XStreamAlias("END_TEMP")
    @XStreamAsAttribute
    private String endTemp;

    @XStreamAlias("DESCRIPTION")
    @XStreamAsAttribute
    private String description;

    @XStreamAlias("WATER_GRAIN_RATIO")
    @XStreamAsAttribute
    private String waterGrainRatio;

    @XStreamAlias("DECOCTION_AMT")
    @XStreamAsAttribute
    private String decoctionAmt;

    @XStreamAlias("INFUSE_TEMP")
    @XStreamAsAttribute
    private String infuseTemp;

    @XStreamAlias("DISPLAY_STEP_TEMP")
    @XStreamAsAttribute
    private String displayStepTemp;

    @XStreamAlias("DISPLAY_INFUSE_AMT")
    @XStreamAsAttribute
    private String displayInfuseAmt;

}
