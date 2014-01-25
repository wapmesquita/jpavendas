package br.com.dextraining.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Endereco;
import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.service.FuncionarioService;
import br.com.dextraining.service.UsuarioService;

public class FuncionarioServiceTest extends AbstractTest {

	@Test
	public void testSalvar() {
		Usuario u = getUsuario("joao", "joao");
		Endereco endereco = getEndereco("campinas", UF.SP, "Rua 1");
		Funcionario funcionario = getFuncionario("111.111.111-11", "Joao", 2000.00, endereco);
		u.setFuncionario(funcionario);

		getService(UsuarioService.class).salvar(u);

		Funcionario buscarFuncionarioPorUsuario = getService(UsuarioService.class).buscarFuncionarioPorUsuario("joao");
		Funcionario buscarPorId = getService(FuncionarioService.class).buscarPorId(1L);
		Assert.assertEquals(buscarFuncionarioPorUsuario, buscarPorId);

		List<Funcionario> buscaPorNome = getService(FuncionarioService.class).buscaPorNome("Joao");
		Assert.assertEquals(buscarPorId, buscaPorNome.get(0));
		
		Funcionario buscarPorUsuario = getService(FuncionarioService.class).buscarPorUsuario("joao");
		Assert.assertEquals(buscarFuncionarioPorUsuario, buscarPorUsuario);
	}

}
