package org.pinguim.model.common;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(State.class)
public abstract class State_ {

	public static volatile SingularAttribute<State, ZonedDateTime> update_to;
	public static volatile SingularAttribute<State, Country> country;
	public static volatile SingularAttribute<State, String> name;
	public static volatile SingularAttribute<State, Long> id;
	public static volatile SingularAttribute<State, String> abbr;
	public static volatile SingularAttribute<State, Integer> version;

}

