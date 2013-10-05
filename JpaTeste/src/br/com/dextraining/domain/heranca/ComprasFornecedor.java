package br.com.dextraining.domain.heranca;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fornecedor")
public class ComprasFornecedor extends Compras {

	public String fornecedor;
	
}
