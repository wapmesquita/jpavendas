package br.com.dextraining.service.impl;

import br.com.dextraining.domain.Pessoa;

public abstract class PessoaServiceImpl<T extends Pessoa> extends AbstractServiceImpl<T> {

	public PessoaServiceImpl(Class<T> clazz) {
		super(clazz);
	}


}
