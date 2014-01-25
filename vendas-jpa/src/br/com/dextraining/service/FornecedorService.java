package br.com.dextraining.service;

import java.util.List;
import java.util.Map;

import br.com.dextraining.annotation.Loggable;
import br.com.dextraining.annotation.Transaction;
import br.com.dextraining.domain.compras.Fornecedor;

public interface FornecedorService {

	public abstract List<Fornecedor> buscarOrdenadoPorEstado(String nome);

	@Loggable("Salvar Fornecedor")
	@Transaction
	public abstract Fornecedor salvar(Fornecedor f);

	public abstract List<Fornecedor> buscarPorFiltro(Map<String, Object> filtros);

	public abstract Fornecedor buscarPorId(Long id);

	@Transaction
	public abstract void remover(Fornecedor pessoaEncontrada);

	public abstract List<Fornecedor> buscarTodos();

}