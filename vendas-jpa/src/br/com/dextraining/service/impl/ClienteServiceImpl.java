package br.com.dextraining.service.impl;

import java.util.List;

import br.com.dextraining.domain.Cliente;
import br.com.dextraining.service.ClienteService;

public class ClienteServiceImpl extends PessoaServiceImpl<Cliente> implements ClienteService {

	public ClienteServiceImpl() {
		super(Cliente.class);
	}

	@Override
	public List<String> buscarNomes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> buscarInfos() {
		// TODO Auto-generated method stub
		return null;
	}

}
