package br.com.dextraining.service;

import java.util.List;

import br.com.dextraining.annotation.Loggable;
import br.com.dextraining.annotation.Transaction;
import br.com.dextraining.domain.Funcionario;

public interface FuncionarioService {

	@Loggable("Salvar Funcionario")
	@Transaction
	public abstract void salvar(Funcionario value);

	public abstract Funcionario buscarPorUsuario(String login);

	public abstract List<Funcionario> buscaPorNome(String nome);

	public abstract Funcionario buscarPorId(Long id);

	public abstract Object buscarTodos();

	@Transaction
	public abstract void remover(Funcionario buscarPorId);

}