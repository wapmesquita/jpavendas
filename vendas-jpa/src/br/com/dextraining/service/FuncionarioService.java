package br.com.dextraining.service;

import java.util.List;

import br.com.dextraining.domain.Funcionario;

public interface FuncionarioService {

	public abstract void salvar(Funcionario value);

	public abstract Funcionario buscarPorUsuario(String login);

	public abstract List<Funcionario> buscaPorNome(String nome);

	public abstract Funcionario buscarPorId(Long id);

	public abstract Object buscarTodos();

	public abstract void remover(Funcionario buscarPorId);

}