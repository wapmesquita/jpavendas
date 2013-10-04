package br.com.dextraining.dao;

import br.com.dextraining.domain.Funcionario;

public class FuncionarioDao extends PessoaDao<Funcionario> {

	public FuncionarioDao(boolean gerenciaTransacao) {
		super(Funcionario.class, gerenciaTransacao);
	}

	public FuncionarioDao(Class<Funcionario> clazz) {
		super(Funcionario.class);
	}

}
