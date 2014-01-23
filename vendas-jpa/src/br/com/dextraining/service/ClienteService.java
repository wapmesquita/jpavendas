package br.com.dextraining.service;

import java.util.List;

import br.com.dextraining.domain.Cliente;

public interface ClienteService {

	public abstract List<String> buscarNomes();

	public abstract List<Object[]> buscarInfos();

	public abstract void salvar(Cliente c);

}