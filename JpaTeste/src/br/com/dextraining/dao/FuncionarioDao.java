package br.com.dextraining.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Funcionario;

public class FuncionarioDao extends PessoaDao<Funcionario> {

	public FuncionarioDao(boolean gerenciaTransacao) {
		super(Funcionario.class, gerenciaTransacao);
	}

	public FuncionarioDao(Class<Funcionario> clazz) {
		super(Funcionario.class);
	}

	public Funcionario buscarPorUsuario(String login) {
		String jpql = "FROM " + getClazz().getSimpleName()
				+ " f WHERE f.usuario.login = :login";
		TypedQuery<Funcionario> qry = getEm().createQuery(jpql, getClazz());
		qry.setParameter("login", login);
		try {
			return qry.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

	}
}
