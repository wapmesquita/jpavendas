package br.com.dextraining.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.domain.compras.Fornecedor;

public class PessoaDaoTest {

	@Test
	public void test01Salvar() {
		Fornecedor f = new Fornecedor();
		f.setNome("Fornecedor 1");
		f.setCnpj("123451234523453");
		f.getEndereco().setCidade("Campinas");
		f.getEndereco().setEstado(UF.SP);
		f.getEndereco().setRua("Rua 1");
		f.setNomeResponsavel("Mario");

		GenericDao<Fornecedor> dao = new GenericDao<Fornecedor>(Fornecedor.class, true);
		dao.salvar(f);
		System.out.println(f.getId());
		Fornecedor pessoaEncontrada = dao.buscarPorId(f.getId());

		Assert.assertEquals(f, pessoaEncontrada);

		dao.remover(pessoaEncontrada);
		Assert.assertEquals(null, dao.buscarPorId(f.getId()));
	}

	@Test
	public void test02BuscarTodos() {
		Fornecedor f = new Fornecedor();
		f.setNome("JOAO");
		f.setCnpj("123451234523453");
		f.getEndereco().setCidade("Campinas");
		f.getEndereco().setEstado(UF.SP);
		f.getEndereco().setRua("Rua 1");
		f.setNomeResponsavel("Maria");

		GenericDao<Fornecedor> dao = new GenericDao<Fornecedor>(Fornecedor.class, true);
		dao.salvar(f);

		List<Fornecedor> pessoas = dao.buscarTodos();
		Assert.assertTrue(pessoas.size() > 0);
		System.out.println("Pessoas");
		System.out.println(pessoas.size());
	}

	@Test
	public void test03SalvarPessoaFisica() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("JOAO");
        funcionario.getEndereco().setCidade("Campinas");
        funcionario.getEndereco().setEstado(UF.SP);
        funcionario.getEndereco().setRua("Rua 1");
        funcionario.setCpf("111.111.111-11");
        funcionario.setSalario(1559.80);

        Usuario u = new Usuario();
		u.setLogin("usuario");
		u.setSenha("senha");
		u.setFuncionario(funcionario);

		UsuarioDao userDao = new UsuarioDao(true);
		userDao.salvar(u);
		
		Funcionario p = new Funcionario();
		p.setNome("Jose");
		p.getEndereco().setCidade("Campinas");
		p.getEndereco().setEstado(UF.SP);
		p.getEndereco().setRua("Rua 1");
		p.setCpf("222.222.222-22");
		p.setSalario(1559.80);

		FuncionarioDao dao = new FuncionarioDao(true);
		dao.salvar(p);
		System.out.println(p.getId());

		System.out.println("Buscando");
		Funcionario pessoaEncontrada = dao.buscarPorId(p.getId());

		Assert.assertEquals(p, pessoaEncontrada);

		dao.remover(pessoaEncontrada);
		Assert.assertEquals(null, dao.buscarPorId(p.getId()));

	}
}
