package org.pinguim.model.auth;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, ZonedDateTime> update_to;
	public static volatile SingularAttribute<User, String> passwd;
	public static volatile SingularAttribute<User, UUID> id;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, Integer> version;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, ZonedDateTime> lastLogon;

}

