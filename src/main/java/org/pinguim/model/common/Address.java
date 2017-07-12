/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pinguim.model.common;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author tux
 */
@Entity
@Audited
@XmlRootElement
@Table(name = "common_address")
public class Address {
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private UUID id;
    
    @NotEmpty
    private String address;
    
    private String complement; 
    
    @NotEmpty
    private String number;
    
    @NotEmpty
    private String postalcode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private State state;
    
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ZonedDateTime getUpdate_to() {
        return update_to;
    }

    public void setUpdate_to(ZonedDateTime update_to) {
        this.update_to = update_to;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", address=" + address + ", number=" + number + ", postalcode=" + postalcode + ", complement=" + complement + ", state=" + state + ", update_to=" + update_to + '}';
    }
    
    
    
}
