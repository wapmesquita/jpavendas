package br.com.dextraining.service;

import java.util.List;
import java.util.Map;

import br.com.dextraining.domain.compras.Fornecedor;

public interface FornecedorService {

	public abstract List<Fornecedor> buscarOrdenadoPorEstado(String nome);

	public abstract void salvar(Fornecedor f);

	public abstract List<Fornecedor> buscarPorFiltro(Map<String, Object> filtros);

	public abstract Fornecedor buscarPorId(Long id);

	public abstract void remover(Fornecedor pessoaEncontrada);

	public abstract List<Fornecedor> buscarTodos();

}