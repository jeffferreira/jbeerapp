package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("HOP")
public class HopDTO {

    @XStreamAlias("NAME")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("VERSION")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("ORIGIN")
    @XStreamAsAttribute
    private String origin;

    @XStreamAlias("ALPHA")
    @XStreamAsAttribute
    private String alpha;

    @XStreamAlias("AMOUNT")
    @XStreamAsAttribute
    private String amount;

    @XStreamAlias("USE")
    @XStreamAsAttribute
    private String use;

    @XStreamAlias("TIME")
    @XStreamAsAttribute
    private String time;

    @XStreamAlias("NOTES")
    @XStreamAsAttribute
    private String notes;

    @XStreamAlias("TYPE")
    @XStreamAsAttribute
    private String type;

    @XStreamAlias("FORM")
    @XStreamAsAttribute
    private String form;

    @XStreamAlias("BETA")
    @XStreamAsAttribute
    private String beta;

    @XStreamAlias("HSI")
    @XStreamAsAttribute
    private String hsi;

    @XStreamAlias("DISPLAY_AMOUNT")
    @XStreamAsAttribute
    private String displayAmount;

    @XStreamAlias("INVENTORY")
    @XStreamAsAttribute
    private String inventory;

    @XStreamAlias("DISPLAY_TIME")
    @XStreamAsAttribute
    private String displayTime;

}
