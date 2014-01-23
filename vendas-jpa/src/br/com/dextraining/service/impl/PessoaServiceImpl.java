package br.com.dextraining.service.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Pessoa;

public abstract class PessoaServiceImpl<T extends Pessoa> extends AbstractServiceImpl<T> {

	public PessoaServiceImpl(Class<T> clazz) {
		super(clazz);
	}

	public List<T> buscaPorNome(String nome) {
		String jpql = "FROM " + getClazz().getSimpleName() + " t WHERE t.nome LIKE :nome";
		TypedQuery<T> qry = getEm().createQuery(jpql, getClazz());
		qry.setParameter("nome", nome + "%");
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
