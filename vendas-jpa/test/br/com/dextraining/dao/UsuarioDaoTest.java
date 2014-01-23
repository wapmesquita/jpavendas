package br.com.dextraining.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.exception.AuthenticateException;
import br.com.dextraining.service.FuncionarioService;
import br.com.dextraining.service.ServiceFactory;
import br.com.dextraining.service.UsuarioService;

public class UsuarioDaoTest extends AbstractTest {

	@Test
	public void testAutenticacao() throws AuthenticateException {
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
		FuncionarioService funcService = ServiceFactory.service(FuncionarioService.class);
		userService.salvar(u);

		Usuario usuarioBuscado = userService.buscarPorId(u.getId());
		System.out.println(usuarioBuscado.getLogin());

		Usuario autenticado = userService.autenticarUsuario("usuario", "senha");

		autenticado = userService.atualizarDataLogin(autenticado);

		Assert.assertNotNull(autenticado.getDataUltimoAcesso());

		Funcionario f = funcService.buscarPorId(u.getFuncionario().getId());

		Assert.assertEquals(u.getFuncionario(), f);

	}
}
