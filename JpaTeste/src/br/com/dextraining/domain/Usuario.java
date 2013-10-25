package br.com.dextraining.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario extends AbstractEntity {

	@Column(nullable = false, length = 40)
	private String login;

	@Column(nullable = false, length = 20)
	private String senha;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimoAcesso;

	@OneToOne(optional = false, cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Funcionario funcionario;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		funcionario.setUsuario(this);
	}

}
