package br.com.dextraining.service.impl;

import br.com.dextraining.domain.Estoque;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.exception.QuantidadeDeProdutosIndisponiveis;
import br.com.dextraining.service.EstoqueService;

public class EstoqueServiceImpl extends AbstractServiceImpl<Estoque> implements EstoqueService {

	public EstoqueServiceImpl() {
		super(Estoque.class);
	}

	@Override
	public void registrarSaida(Produto produto, Integer quantidade) throws QuantidadeDeProdutosIndisponiveis {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarEntrada(Produto produto, Integer quantidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long buscarQuantidadeNoEstoque(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

}
