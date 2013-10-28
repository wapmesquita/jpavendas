package br.com.dextraining.domain.auditoria;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AuditoriaId implements Serializable {

	private static final long serialVersionUID = -8485583778702524816L;

	private Long idUsuario;
	private Long idObjeto;
	private AcaoAuditoria acao;

	public AuditoriaId() {
	}

	public AuditoriaId(Long idUsuario, Long idObjeto, AcaoAuditoria acao) {
		super();
		this.idUsuario = idUsuario;
		this.idObjeto = idObjeto;
		this.acao = acao;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(Long idObjeto) {
		this.idObjeto = idObjeto;
	}

	public AcaoAuditoria getAcao() {
		return acao;
	}

	public void setAcao(AcaoAuditoria acao) {
		this.acao = acao;
	}

}
