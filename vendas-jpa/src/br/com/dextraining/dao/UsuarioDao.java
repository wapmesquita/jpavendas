package br.com.dextraining.dao;

import java.util.Date;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.exception.AuthenticateException;

public class UsuarioDao extends GenericDao<Usuario> {

	public UsuarioDao(boolean gerenciaTransacao) {
		super(Usuario.class, gerenciaTransacao);
	}

	public UsuarioDao() {
		super(Usuario.class);
	}

	public Usuario autenticarUsuario(String login, String senha) throws AuthenticateException {
		String jpql = "FROM " + getClazz().getSimpleName()
				+ " u WHERE u.login = :login AND u.senha = :senha";
		TypedQuery<Usuario> qry = getEm().createQuery(jpql, Usuario.class);
		qry.setParameter("login", login);
		qry.setParameter("senha", senha);
		try {
			return qry.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new AuthenticateException();
		}
	}

	public Usuario atualizarDataLogin(Usuario user) {
		user.setDataUltimoAcesso(new Date());
		return getEm().merge(user);
	}

	public Funcionario buscarFuncionarioPorUsuario(String login) {
		String jpql = "SELECT u.funcionario FROM " + getClazz().getSimpleName()
				+ " u WHERE u.login = :login";
		TypedQuery<Funcionario> qry = getEm().createQuery(jpql, Funcionario.class);
		qry.setParameter("login", login);
		try {
			return qry.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

	}
}
