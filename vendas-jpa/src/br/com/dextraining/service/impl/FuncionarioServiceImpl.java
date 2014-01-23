package br.com.dextraining.service.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.auditoria.AcaoAuditoria;
import br.com.dextraining.service.FuncionarioService;

public class FuncionarioServiceImpl extends PessoaServiceImpl<Funcionario> implements FuncionarioService {

	public FuncionarioServiceImpl() {
		super(Funcionario.class);
	}

    @Override
	public void salvar(Funcionario value) {
		super.salvar(value);
		AuditoriaServiceImpl.auditar(value.getId(), value, AcaoAuditoria.CADASTRO_FUNCIONARIO);
	}

	@Override
    public Funcionario buscarPorUsuario(String login) {
		String jpql = "FROM " + getClazz().getSimpleName() + " f WHERE f.usuario.login = :login";
		TypedQuery<Funcionario> qry = getEm().createQuery(jpql, getClazz());
		qry.setParameter("login", login);
		try {
			return qry.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

	}
	
    @Override
	public List<Funcionario> buscaPorNome(String nome) {
		TypedQuery<Funcionario> qry = getEm().createNamedQuery("Funcionario.buscarPorNome", Funcionario.class);
		qry.setParameter("nome", nome + "%");
		return qry.getResultList();
	}
}
