package org.pinguim.model.common;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Phone.class)
public abstract class Phone_ {

	public static volatile SingularAttribute<Phone, ZonedDateTime> update_to;
	public static volatile SingularAttribute<Phone, String> number;
	public static volatile SingularAttribute<Phone, Boolean> isDefault;
	public static volatile SingularAttribute<Phone, String> ddd;
	public static volatile SingularAttribute<Phone, String> ddi;
	public static volatile SingularAttribute<Phone, UUID> id;

}

