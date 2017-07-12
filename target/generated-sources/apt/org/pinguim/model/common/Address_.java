package org.pinguim.model.common;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, ZonedDateTime> update_to;
	public static volatile SingularAttribute<Address, String> number;
	public static volatile SingularAttribute<Address, String> address;
	public static volatile SingularAttribute<Address, String> postalcode;
	public static volatile SingularAttribute<Address, UUID> id;
	public static volatile SingularAttribute<Address, State> state;
	public static volatile SingularAttribute<Address, String> complement;

}

