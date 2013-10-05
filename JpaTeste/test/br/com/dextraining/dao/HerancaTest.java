package br.com.dextraining.dao;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.dextraining.domain.heranca.ComprasAvulsas;
import br.com.dextraining.domain.heranca.ComprasFornecedor;

public class HerancaTest {

	@Test
	public void testCompras() {
		ComprasFornecedor cf = new ComprasFornecedor();
		cf.id = 1L;
		cf.fornecedor = "F1";
		cf.produto = "Cerveja";
		cf.quantidade = "Suficiente";
		
		ComprasAvulsas ca = new ComprasAvulsas();
		ca.id = 2L;
		ca.estabelecimento = "lojinha da esquina";
		ca.produto = "Cerveja";
		ca.quantidade = "Suficiente";

		EntityManager em = EntityManagerFactoryWrapper.getEntityManager();
		em.getTransaction().begin();
		em.persist(cf);
		em.persist(ca);
		em.getTransaction().commit();

	}

}
