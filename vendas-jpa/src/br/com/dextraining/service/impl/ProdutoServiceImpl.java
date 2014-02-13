package br.com.dextraining.service.impl;

import java.util.List;

import br.com.dextraining.domain.Produto;
import br.com.dextraining.service.ProdutoService;

public class ProdutoServiceImpl extends AbstractServiceImpl<Produto> implements ProdutoService {

	public ProdutoServiceImpl() {
		super(Produto.class);
	}

	@Override
	public List<Produto> buscarProdutosComValorMaiorQue(double valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> buscarProdutorOrdenadorPorValor() {
		// TODO Auto-generated method stub
		return null;
	}


}
