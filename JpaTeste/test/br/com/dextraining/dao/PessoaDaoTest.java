package br.com.dextraining.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Fornecedor;
import br.com.dextraining.domain.Pessoa;
import br.com.dextraining.domain.PessoaFisica;
import br.com.dextraining.domain.UF;

public class PessoaDaoTest {

	@Test
	public void test01Salvar() {
	    Fornecedor f = new Fornecedor();
		f.setNome("Fornecedor 1");
		f.setCnpj("123451234523453");
		f.getEndereco().setCidade("Campinas");
		f.getEndereco().setEstado(UF.SP);
		f.getEndereco().setRua("Rua 1");
		f.setNomeResponsavel("JOAO");

		GenericDao<Fornecedor> dao = new GenericDao<Fornecedor>(Fornecedor.class, true);
		dao.salvar(f);
		System.out.println(f.id);
		Fornecedor pessoaEncontrada = dao.buscarPorId(f.id);

		Assert.assertEquals(f, pessoaEncontrada);

		dao.remover(pessoaEncontrada);
		Assert.assertEquals(null, dao.buscarPorId(f.id));
	}

	@Test
	public void test02BuscarTodos() {
        Fornecedor f = new Fornecedor();
        f.setNome("JOAO");
        f.setCnpj("123451234523453");
        f.getEndereco().setCidade("Campinas");
        f.getEndereco().setEstado(UF.SP);
        f.getEndereco().setRua("Rua 1");
        f.setNomeResponsavel("JOAO");

		PessoaDao dao = new PessoaDao(true);
		dao.salvar(f);

		List<Pessoa> pessoas = dao.buscarTodos();
		Assert.assertTrue(pessoas.size() > 0);
		System.out.println("Pessoas");
		System.out.println(pessoas.size());
	}

	@Test
	public void test03SalvarPessoaFisica() {
	    PessoaFisica p = new PessoaFisica();
        p.setNome("JOAO");
        p.getEndereco().setCidade("Campinas");
        p.getEndereco().setEstado(UF.SP);
        p.getEndereco().setRua("Rua 1");
        p.setCpf("111.111.111-11");

        GenericDao<PessoaFisica> dao = new GenericDao<PessoaFisica>(PessoaFisica.class, true);
        dao.salvar(p);
        System.out.println(p.id);
        PessoaFisica pessoaEncontrada = dao.buscarPorId(p.id);

        Assert.assertEquals(p, pessoaEncontrada);

        dao.remover(pessoaEncontrada);
        Assert.assertEquals(null, dao.buscarPorId(p.id));

	}

}
