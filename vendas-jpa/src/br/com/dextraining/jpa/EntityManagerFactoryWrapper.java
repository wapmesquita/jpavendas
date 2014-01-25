package br.com.dextraining.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManagerFactoryWrapper {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static EntityManager getEntityManager() {
		if (em == null) {
			em = getFactory().createEntityManager();
		}
		return em;
	}

	private static EntityManagerFactory getFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("teste");
			// DataBaseManager.runManager("jdbc:hsqldb:mem:.");
		}
		return emf;
	}

	public static void restart() {
		commit();
		init();
	}

	public static void init() {
		EntityTransaction tx = getEntityManager().getTransaction();
		if (tx == null || !tx.isActive()) {
			tx.begin();
		}
	}

	public static void commit() {
		EntityTransaction transaction = getEntityManager().getTransaction();
		if (transaction != null && transaction.isActive() && !transaction.getRollbackOnly()) {
			transaction.commit();
		} else {
			rollback();
		}
	}

	public static void rollback() {
		EntityTransaction transaction = getEntityManager().getTransaction();
		if (transaction.isActive() || transaction.getRollbackOnly()) {
			transaction.rollback();
		}
	}

	public static void renovar() {
		em.close();
		em = null;
	}

}
