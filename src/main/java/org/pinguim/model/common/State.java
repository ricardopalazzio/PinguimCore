package org.pinguim.model.common;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Audited
@XmlRootElement
@Cacheable(true)
@Table(name = "common_state")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String abbr;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Country country;
    
    @NotEmpty
    private String name;

    @Version
    private int version;

    /*
     * Trigger block
     */
    @NotAudited
    private ZonedDateTime update_to;

    @PrePersist
    public void prepersiste() {
        this.setUpdate_to(ZonedDateTime.now());
    }

    @PreUpdate
    public void preupdate() {
        this.setUpdate_to(ZonedDateTime.now());
    }

    /*
     * Trigger block
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ZonedDateTime getUpdate_to() {
        return update_to;
    }

    public void setUpdate_to(ZonedDateTime update_to) {
        this.update_to = update_to;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "State{" + "id=" + id + ", abbr=" + abbr + ", country=" + country + ", name=" + name + ", version=" + version + ", update_to=" + update_to + '}';
    }

    
    
}
