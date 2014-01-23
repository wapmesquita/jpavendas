package br.com.dextraining.service;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.exception.AuthenticateException;

public interface UsuarioService {

	public abstract Usuario autenticarUsuario(String login, String senha) throws AuthenticateException;

	public abstract Usuario atualizarDataLogin(Usuario user);

	public abstract Funcionario buscarFuncionarioPorUsuario(String login);

	public abstract void salvar(Usuario u);

	public abstract Usuario buscarPorId(Long id);

}