package br.com.dextraining.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.exception.AuthenticateException;

public class UsuarioDaoTest {

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

		UsuarioDao dao = new UsuarioDao(true);
		dao.salvar(u);

		Usuario usuarioBuscado = dao.buscarPorId(u.getId());
		System.out.println(usuarioBuscado.getLogin());

		Usuario autenticado = dao.autenticarUsuario("usuario", "senha");

		autenticado = dao.atualizarDataLogin(autenticado);

		Assert.assertNotNull(autenticado.getDataUltimoAcesso());

		FuncionarioDao funcDao = new FuncionarioDao(true);
		Funcionario f = funcDao.buscarPorId(u.getFuncionario().getId());

		Assert.assertEquals(u.getFuncionario(), f);

	}
}
