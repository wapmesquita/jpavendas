package br.com.dextraining.service;

import java.util.List;

import br.com.dextraining.domain.Produto;
import br.com.dextraining.exception.QuantidadeDeProdutosIndisponiveis;

public interface ProdutoService {

	public abstract List<Produto> buscarProdutosComValorMaiorQue(double valor);

	public abstract List<Produto> buscarProdutosForaEstoque();

	public abstract List<Produto> buscarProdutorOrdenadorPorValor();

	public abstract void atualizaQuantidade(Produto produto, Integer value) throws QuantidadeDeProdutosIndisponiveis;

	public abstract void salvar(Produto p2);

}