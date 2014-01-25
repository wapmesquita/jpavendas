package br.com.dextraining.service;

import java.util.List;

import br.com.dextraining.annotation.Loggable;
import br.com.dextraining.annotation.Transaction;
import br.com.dextraining.domain.Cliente;

public interface ClienteService {

	public abstract List<String> buscarNomes();

	public abstract List<Object[]> buscarInfos();

	@Loggable("Salvar cliente")
	@Transaction
	public Cliente salvar(Cliente c);

}