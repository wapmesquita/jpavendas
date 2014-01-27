package br.com.dextraining.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Estoque extends AbstractEntity {

	@ManyToOne(optional=false)
	private Produto produto;
	@Column(nullable=false)
	private Integer quantidade;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Movimentacao movimentacao;

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public static Estoque entrada(Produto produto, Integer quantidade) {
		Estoque estoque = new Estoque();
		estoque.produto = produto;
		estoque.quantidade = quantidade;
		estoque.movimentacao = Movimentacao.ENTRADA;
		return estoque;
	}

	public static Estoque saida(Produto produto, Integer quantidade) {
		Estoque estoque = new Estoque();
		estoque.produto = produto;
		estoque.quantidade = quantidade;
		estoque.movimentacao = Movimentacao.SAIDA;
		return estoque;
	}

}
