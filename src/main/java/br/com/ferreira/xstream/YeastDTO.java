package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("YEAST")
public class YeastDTO {

    @XStreamAlias("NAME")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("VERSION")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("TYPE")
    @XStreamAsAttribute
    private String type;

    @XStreamAlias("FORM")
    @XStreamAsAttribute
    private String form;

    @XStreamAlias("AMOUNT")
    @XStreamAsAttribute
    private String amount;

    @XStreamAlias("AMOUNT_IS_WEIGHT")
    @XStreamAsAttribute
    private String amountIsWeight;

    @XStreamAlias("LABORATORY")
    @XStreamAsAttribute
    private String laboratory;

    @XStreamAlias("PRODUCT_ID")
    @XStreamAsAttribute
    private String productId;

    @XStreamAlias("MIN_TEMPERATURE")
    @XStreamAsAttribute
    private String minTemperature;

    @XStreamAlias("MAX_TEMPERATURE")
    @XStreamAsAttribute
    private String maxTemperature;

    @XStreamAlias("FLOCCULATION")
    @XStreamAsAttribute
    private String flocculation;

    @XStreamAlias("ATTENUATION")
    @XStreamAsAttribute
    private String attenuation;

    @XStreamAlias("NOTES")
    @XStreamAsAttribute
    private String notes;

    @XStreamAlias("BEST_FOR")
    @XStreamAsAttribute
    private String bestFor;

    @XStreamAlias("MAX_REUSE")
    @XStreamAsAttribute
    private String maxReuse;

    @XStreamAlias("TIMES_CULTURED")
    @XStreamAsAttribute
    private String timesCultured;

    @XStreamAlias("ADD_TO_SECONDARY")
    @XStreamAsAttribute
    private String addToSecundary;

    @XStreamAlias("DISPLAY_AMOUNT")
    @XStreamAsAttribute
    private String displayAmount;

    @XStreamAlias("DISP_MIN_TEMP")
    @XStreamAsAttribute
    private String dispMinTemp;

    @XStreamAlias("DISP_MAX_TEMP")
    @XStreamAsAttribute
    private String dispMaxTemp;

    @XStreamAlias("INVENTORY")
    @XStreamAsAttribute
    private String inventory;

    @XStreamAlias("CULTURE_DATE")
    @XStreamAsAttribute
    private String cultureDate;

}
