package org.pinguim.model.audit;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuditEntity.class)
public abstract class AuditEntity_ extends org.hibernate.envers.DefaultRevisionEntity_ {

	public static volatile SingularAttribute<AuditEntity, String> ip;
	public static volatile SingularAttribute<AuditEntity, String> usuario;

}

