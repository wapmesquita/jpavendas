package br.com.dextraining.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TABELA_PESSOA")
public class Pessoa extends AbstractEntity {

	@Column(name = "NOME_T", nullable = false)
	private String nome;
	@Column(unique = true, nullable = false)
	private String cpf;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	@Column(length = 80)
	private String rua;
	@Enumerated(EnumType.STRING)
	private UF estado;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	private String cidade;
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public UF getEstado() {
		return estado;
	}

	public void setEstado(UF estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
