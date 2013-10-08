package br.com.dextraining.domain.compras;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ComprasFornecedor extends Compras {

    @OneToOne
    private Fornecedor fornecedor;

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
