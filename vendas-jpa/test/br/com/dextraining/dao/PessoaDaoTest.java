package br.com.dextraining.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.domain.compras.Fornecedor;
import br.com.dextraining.service.FornecedorService;
import br.com.dextraining.service.FuncionarioService;
import br.com.dextraining.service.ServiceFactory;
import br.com.dextraining.service.UsuarioService;

public class PessoaDaoTest extends AbstractTest {

	@Test
	public void test01Salvar() {
		Fornecedor f = new Fornecedor();
		f.setNome("Fornecedor 1");
		f.setCnpj("123451234523453");
		f.getEndereco().setCidade("Campinas");
		f.getEndereco().setEstado(UF.SP);
		f.getEndereco().setRua("Rua 1");
		f.setNomeResponsavel("Mario");

		FornecedorService fornecedorService = ServiceFactory.service(FornecedorService.class);
		fornecedorService.salvar(f);
		System.out.println(f.getId());
		Fornecedor pessoaEncontrada = fornecedorService.buscarPorId(f.getId());

		Assert.assertEquals(f, pessoaEncontrada);

		fornecedorService.remover(pessoaEncontrada);
		Assert.assertEquals(null, fornecedorService.buscarPorId(f.getId()));
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

		FornecedorService fornecedorService = ServiceFactory.service(FornecedorService.class);
		fornecedorService.salvar(f);

		List<Fornecedor> pessoas = fornecedorService.buscarTodos();
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

		UsuarioService userService = ServiceFactory.service(UsuarioService.class);
		userService.salvar(u);

		Funcionario p = new Funcionario();
		p.setNome("Jose");
		p.getEndereco().setCidade("Campinas");
		p.getEndereco().setEstado(UF.SP);
		p.getEndereco().setRua("Rua 1");
		p.setCpf("222.222.222-22");
		p.setSalario(1559.80);

		FuncionarioService funcService = ServiceFactory.service(FuncionarioService.class);
		funcService.salvar(p);
		Long id = p.getId();
		System.out.println(id);

		System.out.println("Buscando");

		Funcionario pessoaEncontrada = funcService.buscarPorId(id);
		System.out.println(pessoaEncontrada.getId());
		Assert.assertEquals(p, pessoaEncontrada);

		System.out.println("Todos");
		Assert.assertNotNull(funcService.buscarTodos());

		funcService.remover(funcService.buscarPorId(id));
		Assert.assertEquals(null, funcService.buscarPorId(id));

	}

	@Test
	public void buscarPorNome() {
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

		UsuarioService userService = ServiceFactory.service(UsuarioService.class);
		userService.salvar(u);

		Funcionario p = new Funcionario();
		p.setNome("Walter");
		p.getEndereco().setCidade("Campinas");
		p.getEndereco().setEstado(UF.SP);
		p.getEndereco().setRua("Rua 1");
		p.setCpf("999.999.999-99");
		p.setSalario(1559.80);

		FuncionarioService funcService = ServiceFactory.service(FuncionarioService.class);
		funcService.salvar(p);
		Long id = p.getId();
		System.out.println(id);

		System.out.println("Buscando");
		Assert.assertEquals(p, funcService.buscaPorNome("Walter").get(0));
		System.out.println("Segunda vez");
		Assert.assertEquals(p, funcService.buscaPorNome("Walter").get(0));

	}
}
