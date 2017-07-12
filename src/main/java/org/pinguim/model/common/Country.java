package org.pinguim.model.common;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Audited
@Cacheable(true)
@Table(name = "common_country")
public class Country implements Serializable {

    
    private static final long serialVersionUID = 1l;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    private String abbr;
    
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "country", cascade = {CascadeType.PERSIST})    
    private List<State> states  = new ArrayList<State>();
    
    @Version
    @NotNull
    private int version;
    
    /*
     * Trigger block
     */
    @NotAudited
    private ZonedDateTime update_to;
    
    @PrePersist
    public void prepersiste(){
        this.setUpdate_to(ZonedDateTime.now());
    }
    @PreUpdate
    public void preupdate(){
        this.setUpdate_to(ZonedDateTime.now());
    }
    /*
     * Trigger block
     */
    
    public Country() {
    }
    
    public Country(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public ZonedDateTime getUpdate_to() {
        return update_to;
    }

    public void setUpdate_to(ZonedDateTime update_to) {
        this.update_to = update_to;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Country)) {
            return false;
        }
        Country other = (Country) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (name != null && !name.trim().isEmpty()) {
            result += "name: " + name;
        }
        return result;
    }
    
    
}
