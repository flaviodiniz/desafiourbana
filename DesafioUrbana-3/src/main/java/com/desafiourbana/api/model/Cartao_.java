package com.desafiourbana.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cartao.class)
public abstract class Cartao_ {

	public static volatile SingularAttribute<Cartao, Integer> numeroCartao;
	public static volatile SingularAttribute<Cartao, TipoCartao> tipoCartao;
	public static volatile SingularAttribute<Cartao, String> nome;
	public static volatile SingularAttribute<Cartao, Usuario> usuario;
	public static volatile SingularAttribute<Cartao, Long> id;
	public static volatile SingularAttribute<Cartao, Boolean> status;

}

