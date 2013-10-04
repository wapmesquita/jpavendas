package br.com.dextraining.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Pessoa;
import br.com.dextraining.domain.UF;

public class PessoaDaoTest {

	@Test
	public void test01Salvar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("JOAO");
		pessoa.setCpf("111.111.111-11");
		pessoa.setEstado(UF.SP);

		PessoaDao dao = new PessoaDao(true);
		dao.salvar(pessoa);
		System.out.println(pessoa.id);
		Pessoa pessoaEncontrada = dao.buscarPorId(pessoa.id);

		Assert.assertEquals(pessoa, pessoaEncontrada);
		
		dao.remover(pessoaEncontrada);
		Assert.assertEquals(null, dao.buscarPorId(pessoa.id));
	}
	
	@Test
	public void test02BuscarTodos() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("MARIA");
		pessoa.setCpf("222.222.222-22");
		pessoa.setEstado(UF.SP);

		PessoaDao dao = new PessoaDao(true);
		dao.salvar(pessoa);
		
		List<Pessoa> pessoas = dao.buscarTodos();
		Assert.assertTrue(pessoas.size() > 0);
		System.out.println("Pessoas");
		System.out.println(pessoas.size());
	}
}
