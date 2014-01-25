package br.com.dextraining.service;

import java.util.List;

import br.com.dextraining.annotation.Transaction;
import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.exception.AuthenticateException;

public interface UsuarioService {

	public abstract Usuario autenticarUsuario(String login, String senha) throws AuthenticateException;

	public abstract Usuario atualizarDataLogin(Usuario user);

	public abstract Funcionario buscarFuncionarioPorUsuario(String login);

	@Transaction
	public abstract Usuario salvar(Usuario u);

	public abstract Usuario buscarPorId(Long id);

	public List<Usuario> buscarTodos();

}