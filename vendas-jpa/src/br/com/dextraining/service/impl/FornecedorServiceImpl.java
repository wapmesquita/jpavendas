package br.com.dextraining.service.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.compras.Fornecedor;
import br.com.dextraining.service.FornecedorService;

public class FornecedorServiceImpl extends PessoaServiceImpl<Fornecedor> implements FornecedorService {

	public FornecedorServiceImpl() {
		super(Fornecedor.class);
	}

	@Override
	public List<Fornecedor> buscarOrdenadoPorEstado(String nome) {
		String jpql = "FROM " + getClazz().getSimpleName() + " t WHERE t.nome LIKE :nome ORDER BY t.endereco.estado";
		TypedQuery<Fornecedor> qry = getEm().createQuery(jpql, getClazz());
		qry.setParameter("nome", nome + "%");
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
