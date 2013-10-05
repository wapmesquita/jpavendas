package br.com.dextraining.domain.heranca;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoCompra")
public abstract class Compras {

	@Id
	public Long id;

	public String produto;
	public String quantidade;

}
