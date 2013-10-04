package br.com.dextraining.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Produto extends AbstractEntity {

	private String nome;
	private String descricao;
	@Column(precision=2)
	private Double valor;
	private Integer qntd;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQntd() {
		return qntd;
	}

	public void setQntd(Integer qntd) {
		this.qntd = qntd;
	}

}
