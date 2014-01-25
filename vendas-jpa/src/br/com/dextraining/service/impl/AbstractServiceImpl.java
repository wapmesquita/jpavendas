package br.com.dextraining.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.AbstractEntity;
import br.com.dextraining.jpa.EntityManagerFactoryWrapper;
import br.com.dextraining.service.AbstractService;

public class AbstractServiceImpl<T extends AbstractEntity> implements AbstractService<T> {

	private final Class<T> clazz;

	public AbstractServiceImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	protected EntityManager getEm() {
		return EntityManagerFactoryWrapper.getEntityManager();
	}

	protected Class<T> getClazz() {
		return clazz;
	}

	@Override
	public void salvar(T value) {
		if (value.getId() == null) {
			getEm().persist(value);
		} else {
			getEm().merge(value);
		}
	}

	@Override
	public void remover(T value) {
		getEm().remove(value);
	}

	@Override
	public T buscarPorId(Long id) {
		return getEm().find(clazz, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() {
		String jpql = "from " + clazz.getSimpleName();
		Query qry = getEm().createQuery(jpql);
		return qry.getResultList();
	}

	@Override
	public List<T> buscarPorFiltro(Map<String, Object> filtro) {
		StringBuilder sb = new StringBuilder("FROM ").append(
				clazz.getSimpleName()).append(" t");
		if (filtro != null && !filtro.isEmpty()) {
			sb.append(" WHERE ");
			Iterator<Entry<String, Object>> iterator = filtro.entrySet()
					.iterator();
			Entry<String, Object> entry;
			while (iterator.hasNext()) {
				entry = iterator.next();
				sb.append(entry.getKey()).append(" = :").append(entry.getKey());
				if (iterator.hasNext()) {
					sb.append(" and ");
				}
			}
		}

		TypedQuery<T> qry = getEm().createQuery(sb.toString(), clazz);
		if (filtro != null && !filtro.isEmpty()) {
			for (Entry<String, Object> entry : filtro.entrySet()) {
				qry.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return qry.getResultList();
	}

}
