package br.com.ferreira.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Sets;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * A Mash.
 */
@Entity
@Table(name = "mash")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ph", precision = 10, scale = 2)
    private BigDecimal ph;

    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "mash", orphanRemoval = true)
    private Set<MashStep> mashSteps = Sets.newHashSet();

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

    public Mash name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPh() {
        return ph;
    }

    public Mash ph(BigDecimal ph) {
        this.ph = ph;
        return this;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    public Set<MashStep> getMashSteps() {
        return mashSteps;
    }

    public void setMashSteps(Set<MashStep> mashSteps) {
        this.mashSteps = mashSteps;
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
        Mash mash = (Mash) o;
        if (mash.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mash.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Mash{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", ph=" + getPh() +
            "}";
    }
}
