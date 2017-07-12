package org.pinguim.model.common;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
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

//@Entity
//@XmlRootElement
//@Audited
//@Cacheable(true)
//@Table(name = "common_sample")
public class Sample implements Serializable {

    
    private static final long serialVersionUID = 1l;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


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
    
    public Sample() {
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
        if (!(obj instanceof Sample)) {
            return false;
        }
        Sample other = (Sample) obj;
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
        return "Sample{" + "id=" + id + ", version=" + version + ", update_to=" + update_to + '}';
    }

 
    
    
}
