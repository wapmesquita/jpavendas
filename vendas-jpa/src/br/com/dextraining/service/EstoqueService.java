package br.com.dextraining.service;

import br.com.dextraining.annotation.Loggable;
import br.com.dextraining.annotation.Transaction;
import br.com.dextraining.domain.Estoque;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.exception.QuantidadeDeProdutosIndisponiveis;

public interface EstoqueService {

	@Loggable("registrando saida")
	void registrarSaida(Produto produto, Integer quantidade) throws QuantidadeDeProdutosIndisponiveis;

	@Loggable("registrando entrada")
	@Transaction
	void registrarEntrada(Produto produto, Integer quantidade);

	Long buscarQuantidadeNoEstoque(Produto produto);

	@Transaction
	Estoque salvar(Estoque entrada);

}
