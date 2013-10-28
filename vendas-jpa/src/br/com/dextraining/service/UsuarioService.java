package br.com.dextraining.service;

import br.com.dextraining.dao.UsuarioDao;
import br.com.dextraining.domain.Usuario;

public class UsuarioService {

	public static Usuario getUsuarioLogado() {
		UsuarioDao dao = new UsuarioDao();
		return dao.buscarPorId(1L);
	}

}
