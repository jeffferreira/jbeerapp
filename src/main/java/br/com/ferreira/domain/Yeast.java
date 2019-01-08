package br.com.ferreira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Yeast.
 */
@Entity
@Table(name = "yeast")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Yeast implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "laboratory")
    private String laboratory;

    @Column(name = "product_id")
    private String productId;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Recipe yeast;

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

    public Yeast name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public Yeast type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public Yeast laboratory(String laboratory) {
        this.laboratory = laboratory;
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getProductId() {
        return productId;
    }

    public Yeast productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Recipe getYeast() {
        return yeast;
    }

    public Yeast yeast(Recipe recipe) {
        this.yeast = recipe;
        return this;
    }

    public void setYeast(Recipe recipe) {
        this.yeast = recipe;
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
        Yeast yeast = (Yeast) o;
        if (yeast.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), yeast.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Yeast{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", productId='" + getProductId() + "'" +
            "}";
    }
}
