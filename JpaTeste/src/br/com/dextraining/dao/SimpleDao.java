package br.com.dextraining.dao;

import java.util.List;
import java.util.Map;

import br.com.dextraining.domain.AbstractEntity;

public final class SimpleDao extends AbstractDao<AbstractEntity> {

	public SimpleDao() {
		super();
	}

	public SimpleDao(boolean gerenciaTransacao) {
		super(gerenciaTransacao);
	}

	@Override
	public AbstractEntity buscarPorId(Long id) {
		throw new RuntimeException("Unimplemented method!");
	}

	@Override
	public List<AbstractEntity> buscarTodos() {
		throw new RuntimeException("Unimplemented method!");
	}

	@Override
	public List<AbstractEntity> buscarPorFiltro(Map<String, Object> filtro) {
		throw new RuntimeException("Unimplemented method!");
	}

}
