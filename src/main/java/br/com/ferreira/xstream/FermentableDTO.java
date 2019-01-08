package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("FERMENTABLE")
public class FermentableDTO {

    @XStreamAlias("NAME")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("VERSION")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("TYPE")
    @XStreamAsAttribute
    private String type;

    @XStreamAlias("AMOUNT")
    @XStreamAsAttribute
    private String amount;

    @XStreamAlias("YIELD")
    @XStreamAsAttribute
    private String yield;

    @XStreamAlias("COLOR")
    @XStreamAsAttribute
    private String color;

    @XStreamAlias("ADD_AFTER_BOIL")
    @XStreamAsAttribute
    private String addAfterBoil;

    @XStreamAlias("ORIGIN")
    @XStreamAsAttribute
    private String origin;

    @XStreamAlias("SUPPLIER")
    @XStreamAsAttribute
    private String supplier;

    @XStreamAlias("NOTES")
    @XStreamAsAttribute
    private String notes;

    @XStreamAlias("COARSE_FINE_DIFF")
    @XStreamAsAttribute
    private String coarseFineDiff;

    @XStreamAlias("MOISTURE")
    @XStreamAsAttribute
    private String moisture;

    @XStreamAlias("DIASTATIC_POWER")
    @XStreamAsAttribute
    private String diastaticPower;

    @XStreamAlias("PROTEIN")
    @XStreamAsAttribute
    private String protein;

    @XStreamAlias("MAX_IN_BATCH")
    @XStreamAsAttribute
    private String maxInBatch;

    @XStreamAlias("RECOMMEND_MASH")
    @XStreamAsAttribute
    private String recommendMash;

    @XStreamAlias("IBU_GAL_PER_LB")
    @XStreamAsAttribute
    private String ibuGalPerLb;

    @XStreamAlias("DISPLAY_AMOUNT")
    @XStreamAsAttribute
    private String displayAmount;

    @XStreamAlias("INVENTORY")
    @XStreamAsAttribute
    private String inventory;

    @XStreamAlias("POTENTIAL")
    @XStreamAsAttribute
    private String potential;

    @XStreamAlias("DISPLAY_COLOR")
    @XStreamAsAttribute
    private String displayColor;

    @XStreamAlias("EXTRACT_SUBSTITUTE")
    @XStreamAsAttribute
    private String extractSubstitute;

}
