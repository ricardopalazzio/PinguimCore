package org.pinguim.model.audit;

import javax.persistence.Entity;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;


@Entity(name="audit_revinfo")
@RevisionEntity(AuditListener.class)
public class AuditEntity extends DefaultRevisionEntity {

    private static final long serialVersionUID = 1L;

    private String usuario;
 
    private String ip;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

 




}
