package br.com.dextraining.service.impl;

import java.util.Date;
import java.util.List;

import br.com.dextraining.domain.Produto;
import br.com.dextraining.domain.Venda;
import br.com.dextraining.domain.query.VendaAcumuladaData;
import br.com.dextraining.service.VendaService;

public class VendaServiceImpl extends AbstractServiceImpl<Venda> implements VendaService {

	public VendaServiceImpl() {
		super(Venda.class);
	}

	@Override
	public List<Venda> buscarVendasDoFuncionario(Long funcionarioId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venda> buscarVendasParaCliente(Long clienteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venda> buscarVendasDoProduto(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VendaAcumuladaData> buscarVendaAcumuladas(Date inicio, Date fim) {
		// TODO Auto-generated method stub
		return null;
	}

}