package br.com.dextraining.domain.auditoria;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Auditoria {

	@EmbeddedId
	private AuditoriaId id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private String objectData;
	
	public Auditoria(Long idUsuario, Long idObjeto, AcaoAuditoria acao) {
		super();
		this.id = new AuditoriaId(idUsuario, idObjeto, acao);
	}

	public AuditoriaId getId() {
		if (id == null) {
			id = new AuditoriaId();
		}
		return id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObjectData() {
		return objectData;
	}

	public void setObjectData(String objectData) {
		this.objectData = objectData;
	}

	
}
