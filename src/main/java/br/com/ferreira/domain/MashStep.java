package br.com.ferreira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A MashStep.
 */
@Entity
@Table(name = "mash_step")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MashStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "step_time", precision = 10, scale = 2)
    private BigDecimal stepTime;

    @Column(name = "step_temp", precision = 10, scale = 2)
    private BigDecimal stepTemp;

    @Column(name = "description")
    private String description;

    @Column(name = "infuse_temp")
    private String infuseTemp;

    @Column(name = "display_step_temp")
    private String displayStepTemp;

    @Column(name = "display_infuse_amt")
    private String displayInfuseAmt;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Mash mash;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MashStep name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public MashStep type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getStepTime() {
        return stepTime;
    }

    public MashStep stepTime(BigDecimal stepTime) {
        this.stepTime = stepTime;
        return this;
    }

    public void setStepTime(BigDecimal stepTime) {
        this.stepTime = stepTime;
    }

    public BigDecimal getStepTemp() {
        return stepTemp;
    }

    public MashStep stepTemp(BigDecimal stepTemp) {
        this.stepTemp = stepTemp;
        return this;
    }

    public void setStepTemp(BigDecimal stepTemp) {
        this.stepTemp = stepTemp;
    }

    public String getDescription() {
        return description;
    }

    public MashStep description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfuseTemp() {
        return infuseTemp;
    }

    public MashStep infuseTemp(String infuseTemp) {
        this.infuseTemp = infuseTemp;
        return this;
    }

    public void setInfuseTemp(String infuseTemp) {
        this.infuseTemp = infuseTemp;
    }

    public String getDisplayStepTemp() {
        return displayStepTemp;
    }

    public MashStep displayStepTemp(String displayStepTemp) {
        this.displayStepTemp = displayStepTemp;
        return this;
    }

    public void setDisplayStepTemp(String displayStepTemp) {
        this.displayStepTemp = displayStepTemp;
    }

    public String getDisplayInfuseAmt() {
        return displayInfuseAmt;
    }

    public MashStep displayInfuseAmt(String displayInfuseAmt) {
        this.displayInfuseAmt = displayInfuseAmt;
        return this;
    }

    public void setDisplayInfuseAmt(String displayInfuseAmt) {
        this.displayInfuseAmt = displayInfuseAmt;
    }

    public Mash getMash() {
        return mash;
    }

    public MashStep mash(Mash mash) {
        this.mash = mash;
        return this;
    }

    public void setMash(Mash mash) {
        this.mash = mash;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MashStep mashStep = (MashStep) o;
        if (mashStep.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mashStep.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MashStep{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", stepTime=" + getStepTime() +
            ", stepTemp=" + getStepTemp() +
            ", description='" + getDescription() + "'" +
            ", infuseTemp='" + getInfuseTemp() + "'" +
            ", displayStepTemp='" + getDisplayStepTemp() + "'" +
            ", displayInfuseAmt='" + getDisplayInfuseAmt() + "'" +
            "}";
    }
}
