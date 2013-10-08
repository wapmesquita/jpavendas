package br.com.dextraining.domain.compras;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ComprasAvulsas extends Compras {

    @Column(nullable=false)
	public String estabelecimento;

}
