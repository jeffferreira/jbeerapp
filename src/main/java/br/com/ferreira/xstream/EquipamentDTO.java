package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("EQUIPAMENT")
public class EquipamentDTO {

    @XStreamAlias("NAME")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("VERSION")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("BOIL_SIZE")
    @XStreamAsAttribute
    private String boilSize;

    @XStreamAlias("BATCH_SIZE")
    @XStreamAsAttribute
    private String batchSize;

    @XStreamAlias("TUN_VOLUME")
    @XStreamAsAttribute
    private String tunVolume;

    @XStreamAlias("TUN_WEIGHT")
    @XStreamAsAttribute
    private String tunWeight;

    @XStreamAlias("TUN_SPECIFIC_HEAT")
    @XStreamAsAttribute
    private String tunSpecificHeat;

    @XStreamAlias("TOP_UP_WATER")
    @XStreamAsAttribute
    private String toUpWater;

    @XStreamAlias("TRUB_CHILLER_LOSS")
    @XStreamAsAttribute
    private String trubChillerLoss;

    @XStreamAlias("EVAP_RATE")
    @XStreamAsAttribute
    private String evapRate;

    @XStreamAlias("BOIL_TIME")
    @XStreamAsAttribute
    private String boilTime;

    @XStreamAlias("CALC_BOIL_VOLUME")
    @XStreamAsAttribute
    private String calcBoilVolume;

    @XStreamAlias("LAUTER_DEADSPACE")
    @XStreamAsAttribute
    private String lauterDeadSpace;

    @XStreamAlias("TOP_UP_KETTLE")
    @XStreamAsAttribute
    private String topUpKettle;

    @XStreamAlias("HOP_UTILIZATION")
    @XStreamAsAttribute
    private String hopUtilization;

    @XStreamAlias("COOLING_LOSS_PCT")
    @XStreamAsAttribute
    private String coolingLossPct;

    @XStreamAlias("NOTES")
    @XStreamAsAttribute
    private String notes;

    @XStreamAlias("DISPLAY_BOIL_SIZE")
    @XStreamAsAttribute
    private String displayBoilSize;

    @XStreamAlias("DISPLAY_BATCH_SIZE")
    @XStreamAsAttribute
    private String displayBatchSize;

    @XStreamAlias("DISPLAY_TUN_VOLUME")
    @XStreamAsAttribute
    private String displayTunVolume;

    @XStreamAlias("DISPLAY_TUN_WEIGHT")
    @XStreamAsAttribute
    private String displayTunWeight;

    @XStreamAlias("DISPLAY_TOP_UP_WATER")
    @XStreamAsAttribute
    private String displayTopUpWater;

    @XStreamAlias("DISPLAY_TRUB_CHILLER_LOSS")
    @XStreamAsAttribute
    private String displayTrubChillerLoss;

    @XStreamAlias("DISPLAY_LAUTER_DEADSPACE")
    @XStreamAsAttribute
    private String displayLauterDeadSpace;

    @XStreamAlias("DISPLAY_TOP_UP_KETTLE")
    @XStreamAsAttribute
    private String displayTopUpKettle;

}
