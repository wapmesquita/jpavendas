package br.com.dextraining.domain.heranca;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("avulsa")
public class ComprasAvulsas extends Compras {

	public String estabelecimento;

}
