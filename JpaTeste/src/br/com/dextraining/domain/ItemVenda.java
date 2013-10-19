package br.com.dextraining.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda extends AbstractEntity {

	@ManyToOne(optional = false)
	private Produto produto;

	@Column(nullable = false)
	private Integer qntd = 0;

	@Column(nullable = false, precision = 2, scale = 2)
	private Double valorDesconto = 0.0;

	@Column(nullable = false, precision = 2, scale = 2)
	private Double valorFinal;

	public ItemVenda() {
		// Metodo gerado para JPA
	}

	public ItemVenda(Produto produto, Integer qntd) {
		this(produto, qntd, 0.0);
	}

	public ItemVenda(Produto produto, Integer qntd, Double valorDesconto) {
		super();
		this.produto = produto;
		this.qntd = qntd;
		this.valorDesconto = valorDesconto;
		this.valorFinal = (produto.getValor() - valorDesconto) * qntd;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQntd() {
		return qntd;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

}
