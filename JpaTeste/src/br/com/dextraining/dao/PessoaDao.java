package br.com.dextraining.dao;

import java.util.List;
import java.util.Map;

import br.com.dextraining.domain.Pessoa;

public class PessoaDao extends AbstractDao<Pessoa> {

	public PessoaDao() {
		super();
	}

	public PessoaDao(boolean gerenciaTransacao) {
		super(gerenciaTransacao);
	}

	@Override
	public Pessoa buscarPorId(Long id) {
		return buscarPorId(Pessoa.class, id);
	}

	@Override
	public List<Pessoa> buscarTodos() {
		return this.buscarTodos(Pessoa.class);
	}

	@Override
	public List<Pessoa> buscarPorFiltro(Map<String, Object> filtro) {
		return buscarPorFiltro(Pessoa.class, filtro);
	}

}
