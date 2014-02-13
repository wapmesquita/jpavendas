package br.com.dextraining.service.impl;

import java.util.List;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.service.FuncionarioService;

public class FuncionarioServiceImpl extends PessoaServiceImpl<Funcionario> implements FuncionarioService {

	public FuncionarioServiceImpl() {
		super(Funcionario.class);
	}

	@Override
	public Funcionario buscarPorUsuario(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> buscaPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
