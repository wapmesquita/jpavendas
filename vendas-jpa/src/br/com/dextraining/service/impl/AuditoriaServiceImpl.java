package br.com.dextraining.service.impl;

import java.util.Date;

import br.com.dextraining.domain.auditoria.AcaoAuditoria;
import br.com.dextraining.domain.auditoria.Auditoria;
import br.com.dextraining.jpa.EntityManagerFactoryWrapper;
import flexjson.JSONSerializer;

public class AuditoriaServiceImpl {

	public static void auditar(Long idObj, AcaoAuditoria acao) {
		auditar(idObj, null, acao);
	}

	public static void auditar(Long idObj, Object obj, AcaoAuditoria acao) {
		JSONSerializer serializer = getSerializer();
		Auditoria auditoria = new Auditoria(UsuarioServiceImpl.getUsuarioLogado().getId(), idObj, acao);
		if (obj != null) {
			auditoria.setObjectData(serializer.deepSerialize(obj));
		}
		auditoria.setData(new Date());
		EntityManagerFactoryWrapper.getEntityManager().persist(auditoria);
	}

	private static JSONSerializer getSerializer() {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer;
	}

}
