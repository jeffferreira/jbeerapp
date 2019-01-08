package br.com.ferreira.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A Recipe.
 */
@Entity
@Table(name = "recipe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "brewer")
    private String brewer;

    @Column(name = "batch_size", precision = 10, scale = 2)
    private BigDecimal batchSize;

    @Column(name = "boil_size", precision = 10, scale = 2)
    private BigDecimal boilSize;

    @Column(name = "boil_time", precision = 10, scale = 2)
    private BigDecimal boilTime;

    @Column(name = "eficiency", precision = 10, scale = 2)
    private BigDecimal eficiency;

    @Column(name = "jhi_date")
    private Instant date;

    @Column(name = "ibu")
    private String ibu;

    @Column(name = "est_abv")
    private String estAbv;

    @Column(name = "display_batch_size")
    private String displayBatchSize;

    @Column(name = "display_og")
    private String displayOg;

    @Column(name = "display_fg")
    private String displayFg;

    @OneToOne
    @JoinColumn(unique = true)
    private Mash mash;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "style_id", referencedColumnName = "id")
    private Style style;

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

    public Recipe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public Recipe type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrewer() {
        return brewer;
    }

    public Recipe brewer(String brewer) {
        this.brewer = brewer;
        return this;
    }

    public void setBrewer(String brewer) {
        this.brewer = brewer;
    }

    public BigDecimal getBatchSize() {
        return batchSize;
    }

    public Recipe batchSize(BigDecimal batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public void setBatchSize(BigDecimal batchSize) {
        this.batchSize = batchSize;
    }

    public BigDecimal getBoilSize() {
        return boilSize;
    }

    public Recipe boilSize(BigDecimal boilSize) {
        this.boilSize = boilSize;
        return this;
    }

    public void setBoilSize(BigDecimal boilSize) {
        this.boilSize = boilSize;
    }

    public BigDecimal getBoilTime() {
        return boilTime;
    }

    public Recipe boilTime(BigDecimal boilTime) {
        this.boilTime = boilTime;
        return this;
    }

    public void setBoilTime(BigDecimal boilTime) {
        this.boilTime = boilTime;
    }

    public BigDecimal getEficiency() {
        return eficiency;
    }

    public Recipe eficiency(BigDecimal eficiency) {
        this.eficiency = eficiency;
        return this;
    }

    public void setEficiency(BigDecimal eficiency) {
        this.eficiency = eficiency;
    }

    public Instant getDate() {
        return date;
    }

    public Recipe date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getIbu() {
        return ibu;
    }

    public Recipe ibu(String ibu) {
        this.ibu = ibu;
        return this;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getEstAbv() {
        return estAbv;
    }

    public Recipe estAbv(String estAbv) {
        this.estAbv = estAbv;
        return this;
    }

    public void setEstAbv(String estAbv) {
        this.estAbv = estAbv;
    }

    public String getDisplayBatchSize() {
        return displayBatchSize;
    }

    public Recipe displayBatchSize(String displayBatchSize) {
        this.displayBatchSize = displayBatchSize;
        return this;
    }

    public void setDisplayBatchSize(String displayBatchSize) {
        this.displayBatchSize = displayBatchSize;
    }

    public String getDisplayOg() {
        return displayOg;
    }

    public Recipe displayOg(String displayOg) {
        this.displayOg = displayOg;
        return this;
    }

    public void setDisplayOg(String displayOg) {
        this.displayOg = displayOg;
    }

    public String getDisplayFg() {
        return displayFg;
    }

    public Recipe displayFg(String displayFg) {
        this.displayFg = displayFg;
        return this;
    }

    public void setDisplayFg(String displayFg) {
        this.displayFg = displayFg;
    }

    public Mash getMash() {
        return mash;
    }

    public Recipe mash(Mash mash) {
        this.mash = mash;
        return this;
    }

    public void setMash(Mash mash) {
        this.mash = mash;
    }

    public Style getStyle() {
        return style;
    }

    public Recipe style(Style style) {
        this.style = style;
        return this;
    }

    public void setStyle(Style style) {
        this.style = style;
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
        Recipe recipe = (Recipe) o;
        if (recipe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recipe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Recipe{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", brewer='" + getBrewer() + "'" +
            ", batchSize=" + getBatchSize() +
            ", boilSize=" + getBoilSize() +
            ", boilTime=" + getBoilTime() +
            ", eficiency=" + getEficiency() +
            ", date='" + getDate() + "'" +
            ", ibu='" + getIbu() + "'" +
            ", estAbv='" + getEstAbv() + "'" +
            ", displayBatchSize='" + getDisplayBatchSize() + "'" +
            ", displayOg='" + getDisplayOg() + "'" +
            ", displayFg='" + getDisplayFg() + "'" +
            "}";
    }
}
