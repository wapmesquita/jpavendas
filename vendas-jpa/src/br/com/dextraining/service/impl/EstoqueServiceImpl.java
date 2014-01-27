package br.com.dextraining.service.impl;

import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Estoque;
import br.com.dextraining.domain.Movimentacao;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.exception.QuantidadeDeProdutosIndisponiveis;
import br.com.dextraining.jpa.EntityManagerFactoryWrapper;
import br.com.dextraining.service.EstoqueService;

public class EstoqueServiceImpl extends AbstractServiceImpl<Estoque> implements EstoqueService {

	public EstoqueServiceImpl() {
		super(Estoque.class);
	}

	@Override
	public void registrarEntrada(Produto produto, Integer quantidade) {
		Estoque entrada = Estoque.entrada(produto, quantidade);
		EntityManagerFactoryWrapper.getEntityManager().persist(entrada);
	}

	@Override
	public void registrarSaida(Produto produto, Integer quantidade) throws QuantidadeDeProdutosIndisponiveis {
		Long estoque = buscarQuantidadeNoEstoque(produto);
		if (quantidade > estoque) {
			throw new QuantidadeDeProdutosIndisponiveis();
		}

		Estoque saida = Estoque.saida(produto, quantidade);
		EntityManagerFactoryWrapper.getEntityManager().persist(saida);
	}

	@Override
	public Long buscarQuantidadeNoEstoque(Produto produto) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT SUM(e.quantidade) as qntd ");
		sb.append(" FROM ").append(Estoque.class.getSimpleName()).append(" e ");
		sb.append(" WHERE e.movimentacao = :movi");
		sb.append(" AND e.produto.id = :id");

		TypedQuery<Long> qry = EntityManagerFactoryWrapper.getEntityManager().createQuery(sb.toString(), Long.class);
		qry.setParameter("movi", Movimentacao.ENTRADA);
		qry.setParameter("id", produto.getId());
		Long entrada = qry.getSingleResult();

		qry = EntityManagerFactoryWrapper.getEntityManager().createQuery(sb.toString(), Long.class);
		qry.setParameter("movi", Movimentacao.SAIDA);
		qry.setParameter("id", produto.getId());
		Long saida = qry.getSingleResult();

		return (entrada == null ? 0 : entrada) - (saida == null ? 0 : saida);

	}

}
