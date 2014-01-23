package br.com.dextraining.dao;

import org.junit.Before;

public abstract class AbstractTest {
	
	@Before
	public void before() {
		EntityManagerFactoryWrapper.init();
	}

	public void after() {
		EntityManagerFactoryWrapper.commit();
		EntityManagerFactoryWrapper.renovar();
	}
}
