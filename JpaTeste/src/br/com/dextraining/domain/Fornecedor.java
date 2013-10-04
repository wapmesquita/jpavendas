package br.com.dextraining.domain;

import javax.persistence.Entity;

@Entity
public class Fornecedor extends AbstractEntity {

	private String nome;
	private String telefone;
	private String nomeResponsavel;
	private String cidade;
	private UF estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getEstado() {
		return estado;
	}

	public void setEstado(UF estado) {
		this.estado = estado;
	}

}
