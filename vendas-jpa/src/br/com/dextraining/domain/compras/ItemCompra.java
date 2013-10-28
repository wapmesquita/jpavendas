package br.com.dextraining.domain.compras;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.dextraining.domain.AbstractEntity;
import br.com.dextraining.domain.Produto;

@Entity
public class ItemCompra extends AbstractEntity {

    @ManyToOne(cascade=CascadeType.ALL)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double valorUnitario;

    @Column(nullable = false)
    private Double valorTotal;

    public ItemCompra() {
        // Metodo gerado para JPA
    }

    public ItemCompra(Produto produto, Integer quantidade, Double valorUnitario) {
        super();
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = this.valorUnitario * this.quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

}
