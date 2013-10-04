package br.com.dextraining.dao;

import br.com.dextraining.domain.Cliente;

public class ClienteDao extends PessoaDao<Cliente> {

	public ClienteDao(boolean gerenciaTransacao) {
		super(Cliente.class, gerenciaTransacao);
	}

	public ClienteDao() {
		super(Cliente.class);
	}

}
