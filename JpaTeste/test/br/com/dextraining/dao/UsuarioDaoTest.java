package br.com.dextraining.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Usuario;

public class UsuarioDaoTest {

	@Test
	public void testAutenticacao() {
		Usuario u = new Usuario();
		u.setLogin("usuario");
		u.setSenha("senha");
		UsuarioDao dao = new UsuarioDao(true);
		dao.salvar(u);
		
		Usuario autenticado = dao.autenticarUsuario("usuario", "senha");
		Assert.assertEquals(u, autenticado);
		
		autenticado = dao.atualizarDataLogin(autenticado);
		
		Assert.assertNotNull(autenticado.getDataUltimoAcesso());
	}
}
