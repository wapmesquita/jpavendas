package br.com.dextraining.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Pessoa;

public abstract class PessoaDao<T extends Pessoa> extends GenericDao<T> {

	public PessoaDao(Class<T> clazz, boolean gerenciaTransacao) {
		super(clazz, gerenciaTransacao);
	}

	public PessoaDao(Class<T> clazz) {
		super(clazz);
	}

	public List<T> buscaPorNome(String nome) {
		String jpql = "FROM " + getClazz().getSimpleName()
				+ " t WHERE t.nome LIKE :nome";
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
