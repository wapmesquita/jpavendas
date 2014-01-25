package br.com.dextraining.service;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Endereco;
import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.exception.AuthenticateException;
import br.com.dextraining.service.FuncionarioService;
import br.com.dextraining.service.UsuarioService;

public class UsuarioServiceTest extends AbstractTest {

	@Test
	public void testAutenticacao() throws AuthenticateException {
		Endereco endereco = getEndereco("campinas", UF.SP, "Rua 1");
		Funcionario funcionario = getFuncionario("111.111.111-11", "Joao", 2000.00, endereco);

		Usuario u = new Usuario();
		u.setLogin("usuario");
		u.setSenha("senha");
		u.setFuncionario(funcionario);

		getService(UsuarioService.class).salvar(u);

		Usuario usuarioBuscado = getService(UsuarioService.class).buscarPorId(u.getId());
		System.out.println(usuarioBuscado.getLogin());

		Usuario autenticado = getService(UsuarioService.class).autenticarUsuario("usuario", "senha");

		autenticado = getService(UsuarioService.class).atualizarDataLogin(autenticado);

		Assert.assertNotNull(autenticado.getDataUltimoAcesso());

		Funcionario f = getService(FuncionarioService.class).buscarPorId(u.getFuncionario().getId());

		Assert.assertEquals(u.getFuncionario(), f);

	}
}
