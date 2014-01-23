package br.com.dextraining.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class FornecedorDaoTest extends AbstractTest {

	@Test
	public void test01OrdenadoPorEstado() {
		Fornecedor f = new Fornecedor();
		f.setNome("Fornecedor 1");
		f.setCnpj("123451234523453");
		f.getEndereco().setCidade("Campinas");
		f.getEndereco().setEstado(UF.SP);
		f.getEndereco().setRua("Rua 1");
		f.setNomeResponsavel("JOAO");

		FornecedorService fornecedorService = ServiceFactory.service(FornecedorService.class);
		;
		fornecedorService.salvar(f);

		Fornecedor f2 = new Fornecedor();
		f2.setNome("Fornecedor 2");
		f2.setCnpj("123451234523453");
		f2.getEndereco().setCidade("Campinas");
		f2.getEndereco().setEstado(UF.ES);
		f2.getEndereco().setRua("Rua 1");
		f2.setNomeResponsavel("JOAO");

		fornecedorService.salvar(f2);

		List<Fornecedor> list = fornecedorService.buscarOrdenadoPorEstado("For");
		Assert.assertTrue(list.size() > 1);
		Assert.assertEquals(f2, list.get(0));

		Map<String, Object> filtros = new HashMap<String, Object>();
		filtros.put("nomeResponsavel", "JOAO");
		filtros.put("cnpj", "123451234523453");

		List<Fornecedor> buscarPorFiltro = fornecedorService.buscarPorFiltro(filtros);
		Assert.assertEquals(2, buscarPorFiltro.size());

	}

	@Test
	public void buscarPorUsuario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("JOAO");
		funcionario.getEndereco().setCidade("Campinas");
		funcionario.getEndereco().setEstado(UF.SP);
		funcionario.getEndereco().setRua("Rua 1");
		funcionario.setCpf("111.111.111-11");
		funcionario.setSalario(1559.80);

		Usuario u = new Usuario();
		String login = "usuarioFuncionario";
		u.setLogin(login);
		u.setSenha("senha");
		u.setFuncionario(funcionario);

		UsuarioService usuarioService = ServiceFactory.service(UsuarioService.class);
		usuarioService.salvar(u);

		FuncionarioService funcionarioService = ServiceFactory.service(FuncionarioService.class);
		Funcionario porUsuario = funcionarioService.buscarPorUsuario(login);
		Assert.assertEquals(u.getFuncionario(), porUsuario);

		Funcionario buscarFuncionarioPorUsuario = usuarioService.buscarFuncionarioPorUsuario(login);
		Assert.assertEquals(buscarFuncionarioPorUsuario, porUsuario);
	}

}
