package br.com.dextraining.service;

import java.util.List;

import br.com.dextraining.annotation.Loggable;
import br.com.dextraining.annotation.Transaction;
import br.com.dextraining.domain.Produto;

public interface ProdutoService {

	public abstract List<Produto> buscarProdutosComValorMaiorQue(double valor);

	public abstract List<Produto> buscarProdutorOrdenadorPorValor();

	@Loggable("Salvar Produto")
	@Transaction
	public abstract Produto salvar(Produto p2);

	public abstract Produto buscarPorId(Long id);

	@Transaction
	public abstract void remover(Produto produto);

}