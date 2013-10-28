package br.com.dextraining.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Cliente;

public class ClienteDao extends PessoaDao<Cliente> {

	public ClienteDao(boolean gerenciaTransacao) {
		super(Cliente.class, gerenciaTransacao);
	}

	public ClienteDao() {
		super(Cliente.class);
	}

	public List<String> buscarNomes() {
		String jpql = "SELECT c.nome FROM " + Cliente.class.getSimpleName()
				+ " c";
		TypedQuery<String> qry = getEm().createQuery(jpql, String.class);
		return qry.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> buscarInfos() {
		String jpql = "SELECT c.nome, c.endereco FROM " + Cliente.class.getSimpleName()
				+ " c";
		Query qry = getEm().createQuery(jpql);
		return qry.getResultList();
		
	
	}

}
