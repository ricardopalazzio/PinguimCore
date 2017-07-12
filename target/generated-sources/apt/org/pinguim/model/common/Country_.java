package org.pinguim.model.common;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Country.class)
public abstract class Country_ {

	public static volatile SingularAttribute<Country, ZonedDateTime> update_to;
	public static volatile SingularAttribute<Country, String> name;
	public static volatile SingularAttribute<Country, Long> id;
	public static volatile SingularAttribute<Country, String> abbr;
	public static volatile SingularAttribute<Country, Integer> version;
	public static volatile ListAttribute<Country, State> states;

}

