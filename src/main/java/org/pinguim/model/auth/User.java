package org.pinguim.model.auth;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.enterprise.inject.Model;
import javax.persistence.Cacheable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotEmpty;

@Model
@Entity
@XmlRootElement
@Audited
@Cacheable(true)
@Table(name = "auth_user"
    ,  uniqueConstraints = @UniqueConstraint( name = "user_mail_uk" , columnNames = {"email"} ))

public class User implements Serializable {

    private static final long serialVersionUID = 1l;
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotEmpty
    private String email;
    
    @NotEmpty
    
    private String login;
    
    @NotEmpty
    private String passwd;
    
    @Transient
    private UUID session;
    
    @Version
    @NotNull
    private int version;

    @NotAudited
    private ZonedDateTime lastLogon;
    
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
    
    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", login=" + login + ", passwd=" + passwd + ", version=" + version + ", update_to=" + update_to + '}';
    }

    
 
    
    
}
