package br.com.dextraining.service.impl;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.exception.AuthenticateException;
import br.com.dextraining.service.UsuarioService;

public class UsuarioServiceImpl extends AbstractServiceImpl<Usuario> implements UsuarioService {

	public UsuarioServiceImpl() {
		super(Usuario.class);
	}

	@Override
	public Usuario autenticarUsuario(String login, String senha) throws AuthenticateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario atualizarDataLogin(Usuario user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario buscarFuncionarioPorUsuario(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Usuario getUsuarioLogado() {
		// TODO Auto-generated method stub
		return null;
	}

}
