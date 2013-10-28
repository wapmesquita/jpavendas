package br.com.dextraining.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Categoria extends AbstractEntity {

	@Column(nullable=false, length=80)
	private String nome;
	@Column(length=500)
	private String descricao;

	public Categoria() {
	}

	public Categoria(String nome) {
	    super();
	    this.nome = nome;
    }

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

}
