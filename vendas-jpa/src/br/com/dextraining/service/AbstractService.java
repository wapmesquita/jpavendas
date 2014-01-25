package br.com.dextraining.service;

import java.util.List;
import java.util.Map;

import br.com.dextraining.domain.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {

	public abstract void salvar(T value);

	public abstract void remover(T value);

	public abstract T buscarPorId(Long id);

	public abstract List<T> buscarTodos();

	public abstract List<T> buscarPorFiltro(Map<String, Object> filtro);

}