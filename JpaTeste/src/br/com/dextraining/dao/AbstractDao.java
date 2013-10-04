package br.com.dextraining.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.AbstractEntity;

public abstract class AbstractDao<T extends AbstractEntity> {

	protected EntityManager em = EntityManagerFactoryWrapper.getEntityManager();
	private final boolean gerenciaTransacao;

	public AbstractDao(boolean gerenciaTransacao) {
		this.gerenciaTransacao = gerenciaTransacao;
	}

	public AbstractDao() {
		this(false);
	}

	protected void init() {
		if (this.gerenciaTransacao)
			EntityManagerFactoryWrapper.init();
	}

	protected void commit() {
		if (this.gerenciaTransacao)
			EntityManagerFactoryWrapper.commit();
	}

	protected void rollback() {
		if (this.gerenciaTransacao)
			EntityManagerFactoryWrapper.rollback();
	}

	public void salvar(T value) {
		this.init();
		if (value.id == null) {
			em.persist(value);
		} else {
			em.merge(value);
		}
		this.commit();
	}

	public void remover(T value) {
		this.init();
		em.remove(value);
		this.commit();
	}

	public abstract T buscarPorId(Long id);

	protected T buscarPorId(Class<T> clazz, Long id) {
		return em.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	protected List<T> buscarTodos(Class<T> clazz) {
		String jpql = "from " + clazz.getSimpleName();
		Query qry = em.createQuery(jpql);
		return qry.getResultList();
	}

	public abstract List<T> buscarTodos();

	public abstract List<T> buscarPorFiltro(Map<String, Object> filtro);

	protected List<T> buscarPorFiltro(Class<T> clazz, Map<String, Object> filtro) {
		StringBuilder sb = new StringBuilder("FROM ").append(
				clazz.getSimpleName()).append(" t");
		if (filtro != null && !filtro.isEmpty()) {
			sb.append(" WHERE ");
			Iterator<Entry<String, Object>> iterator = filtro.entrySet().iterator();
			Entry<String, Object> entry;
			while (iterator.hasNext()) {
				entry = iterator.next();
				sb.append(entry.getKey()).append(" = :").append(entry.getKey());
				if (iterator.hasNext()) {
					sb.append(" and ");
				}
			}
		}

		TypedQuery<T> qry = em.createQuery(sb.toString(), clazz);
		if (filtro != null && !filtro.isEmpty()) {
			for (Entry<String, Object> entry : filtro.entrySet()) {
				qry.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return qry.getResultList();
	}
}
