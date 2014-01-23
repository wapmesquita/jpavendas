package br.com.dextraining.service.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Cliente;
import br.com.dextraining.service.ClienteService;

public class ClienteServiceImpl extends PessoaServiceImpl<Cliente> implements ClienteService {

	public ClienteServiceImpl() {
		super(Cliente.class);
	}

	@Override
	public List<String> buscarNomes() {
		String jpql = "SELECT c.nome FROM " + Cliente.class.getSimpleName() + " c";
		TypedQuery<String> qry = getEm().createQuery(jpql, String.class);
		return qry.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> buscarInfos() {
		String jpql = "SELECT c.nome, c.endereco FROM " + Cliente.class.getSimpleName() + " c";
		Query qry = getEm().createQuery(jpql);
		return qry.getResultList();

	}

}
