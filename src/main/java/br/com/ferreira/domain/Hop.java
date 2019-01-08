package br.com.ferreira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Hop.
 */
@Entity
@Table(name = "hop")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Hop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "origin")
    private String origin;

    @Column(name = "alpha", precision = 10, scale = 2)
    private BigDecimal alpha;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "jhi_use")
    private String use;

    @Column(name = "jhi_time", precision = 10, scale = 2)
    private BigDecimal time;

    @Column(name = "display_time")
    private String displayTime;

    @Column(name = "display_amount")
    private String displayAmount;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Recipe recipe;

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

    public Hop name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public Hop origin(String origin) {
        this.origin = origin;
        return this;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BigDecimal getAlpha() {
        return alpha;
    }

    public Hop alpha(BigDecimal alpha) {
        this.alpha = alpha;
        return this;
    }

    public void setAlpha(BigDecimal alpha) {
        this.alpha = alpha;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Hop amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUse() {
        return use;
    }

    public Hop use(String use) {
        this.use = use;
        return this;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public BigDecimal getTime() {
        return time;
    }

    public Hop time(BigDecimal time) {
        this.time = time;
        return this;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public String getDisplayTime() {
        return displayTime;
    }

    public Hop displayTime(String displayTime) {
        this.displayTime = displayTime;
        return this;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

    public String getDisplayAmount() {
        return displayAmount;
    }

    public Hop displayAmount(String displayAmount) {
        this.displayAmount = displayAmount;
        return this;
    }

    public void setDisplayAmount(String displayAmount) {
        this.displayAmount = displayAmount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Hop recipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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
        Hop hop = (Hop) o;
        if (hop.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hop.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hop{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", origin='" + getOrigin() + "'" +
            ", alpha=" + getAlpha() +
            ", amount=" + getAmount() +
            ", use='" + getUse() + "'" +
            ", time=" + getTime() +
            ", displayTime='" + getDisplayTime() + "'" +
            ", displayAmount='" + getDisplayAmount() + "'" +
            "}";
    }
}
