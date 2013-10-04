package br.com.dextraining.dao;

import br.com.dextraining.domain.Pessoa;

public class PessoaDao extends GenericDao<Pessoa> {

	public PessoaDao(boolean gerenciaTransacao) {
		super(Pessoa.class, gerenciaTransacao);
	}

	public PessoaDao() {
		super(Pessoa.class);
	}

}
