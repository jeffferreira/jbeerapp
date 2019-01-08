package br.com.ferreira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Fermentable.
 */
@Entity
@Table(name = "fermentable")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Fermentable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "origin")
    private String origin;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "display_amount")
    private String displayAmount;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Recipe fermentable;

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

    public Fermentable name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public Fermentable type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Fermentable amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrigin() {
        return origin;
    }

    public Fermentable origin(String origin) {
        this.origin = origin;
        return this;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSupplier() {
        return supplier;
    }

    public Fermentable supplier(String supplier) {
        this.supplier = supplier;
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDisplayAmount() {
        return displayAmount;
    }

    public Fermentable displayAmount(String displayAmount) {
        this.displayAmount = displayAmount;
        return this;
    }

    public void setDisplayAmount(String displayAmount) {
        this.displayAmount = displayAmount;
    }

    public Recipe getFermentable() {
        return fermentable;
    }

    public Fermentable fermentable(Recipe recipe) {
        this.fermentable = recipe;
        return this;
    }

    public void setFermentable(Recipe recipe) {
        this.fermentable = recipe;
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
        Fermentable fermentable = (Fermentable) o;
        if (fermentable.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fermentable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fermentable{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", amount=" + getAmount() +
            ", origin='" + getOrigin() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", displayAmount='" + getDisplayAmount() + "'" +
            "}";
    }
}
