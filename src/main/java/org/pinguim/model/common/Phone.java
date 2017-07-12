/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pinguim.model.common;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "common_phone")
public class Phone {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private UUID id;
    
    @NotEmpty
    private String ddi;
    
    @NotEmpty
    private String ddd;
    
    @NotEmpty
    private String number;
    
    @NotNull
    private Boolean isDefault = false;
    
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

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public ZonedDateTime getUpdate_to() {
        return update_to;
    }

    public void setUpdate_to(ZonedDateTime update_to) {
        this.update_to = update_to;
    }

    @Override
    public String toString() {
        return "Phone{" + "id=" + id + ", ddi=" + ddi + ", ddd=" + ddd + ", number=" + number + ", isDefault=" + isDefault + ", update_to=" + update_to + '}';
    }
    
    
    
}
