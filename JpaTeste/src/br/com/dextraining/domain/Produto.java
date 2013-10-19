package br.com.dextraining.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Produto extends AbstractEntity {

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(length = 255)
	private String descricao;

	@Column(nullable = false, precision = 2, scale = 2)
	private Double valor;

	@Column(nullable = false)
	private Integer qntd;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Categoria> categoria;

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

	public List<Categoria> getCategorias() {
		if (categoria == null) {
			categoria = new ArrayList<Categoria>();
		}
		return categoria;
	}

	public void addCategoria(Categoria categoria) {
		getCategorias().add(categoria);
	}

	public void removeCategoria(Categoria categoria) {
		getCategorias().remove(categoria);
	}
}
