package org.pinguim.model.audit;

import javax.enterprise.inject.spi.CDI;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.envers.RevisionListener;


public class AuditListener implements RevisionListener { 

    @Override
    public void newRevision(Object revisionEntity) {
        
        HttpServletRequest req = CDI.current().select(HttpServletRequest.class).get();      
        AuditEntity revEntity = (AuditEntity) revisionEntity;  
        revEntity.setUsuario("usuario teste ");
        revEntity.setIp(req.getRemoteAddr());
        
    }

}
