package br.com.dextraining.service.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Produto;
import br.com.dextraining.exception.QuantidadeDeProdutosIndisponiveis;
import br.com.dextraining.service.ProdutoService;

public class ProdutoServiceImpl extends AbstractServiceImpl<Produto> implements ProdutoService {

	public ProdutoServiceImpl() {
		super(Produto.class);
	}

	public List<Produto> buscarProdutosComValorMaiorQue(double valor) {
		String jpql = "FROM " + getClazz().getSimpleName() + " t WHERE t.valor > :valor ORDER BY t.nome";
		TypedQuery<Produto> qry = getEm().createQuery(jpql, getClazz());
		qry.setParameter("valor", valor);
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public List<Produto> buscarProdutosForaEstoque() {
		String jpql = "FROM " + getClazz().getSimpleName() + " t WHERE t.qntd = 0 ORDER BY t.nome";
		TypedQuery<Produto> qry = getEm().createQuery(jpql, getClazz());
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public List<Produto> buscarProdutorOrdenadorPorValor() {
		String jpql = "FROM " + getClazz().getSimpleName() + " t ORDER BY t.valor";
		TypedQuery<Produto> qry = getEm().createQuery(jpql, getClazz());
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public void atualizaQuantidade(Produto produto, Integer value) throws QuantidadeDeProdutosIndisponiveis {
		produto.baixaEstoque(value);
		getEm().merge(produto);
	}
}
