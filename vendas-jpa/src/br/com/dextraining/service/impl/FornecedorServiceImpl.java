package br.com.dextraining.service.impl;

import java.util.List;

import br.com.dextraining.domain.compras.Fornecedor;
import br.com.dextraining.service.FornecedorService;

public class FornecedorServiceImpl extends PessoaServiceImpl<Fornecedor> implements FornecedorService {

	public FornecedorServiceImpl() {
		super(Fornecedor.class);
	}

	@Override
	public List<Fornecedor> buscarOrdenadoPorEstado(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
