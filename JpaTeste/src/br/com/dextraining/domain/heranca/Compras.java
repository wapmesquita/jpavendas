package br.com.dextraining.domain.heranca;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Compras {

	@Id
	public Long id;

	public String produto;
	public String quantidade;

}
