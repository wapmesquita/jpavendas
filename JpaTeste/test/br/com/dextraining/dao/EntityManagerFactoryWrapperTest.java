package br.com.dextraining.dao;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Pessoa;

public class EntityManagerFactoryWrapperTest {

	@Test
	public void testaEntityManager() {
		EntityManager em = EntityManagerFactoryWrapper.getEntityManager();

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("walter");
		pessoa.setCpf("999.999.999-99");

//		em.getTransaction().begin();
		EntityManagerFactoryWrapper.init();
		em.persist(pessoa);
		EntityManagerFactoryWrapper.commit();
//		em.getTransaction().commit();

		Pessoa pessoaEncontrada = em.find(Pessoa.class, pessoa.id);

		Assert.assertEquals(pessoa, pessoaEncontrada);
	}
}
