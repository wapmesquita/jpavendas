package br.com.dextraining.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.Produto;
import br.com.dextraining.domain.QuantidadeDeProdutosIndisponiveis;

public class ProdutoDao extends GenericDao<Produto> {

	public ProdutoDao(boolean gerenciaTransacao) {
		super(Produto.class, gerenciaTransacao);
	}

	public ProdutoDao() {
		super(Produto.class);
	}

	public List<Produto> buscarProdutosComValorMaiorQue(double valor) {
		String jpql = "FROM " + getClazz().getSimpleName()
				+ " t WHERE t.valor > :valor ORDER BY t.nome";
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
		String jpql = "FROM " + getClazz().getSimpleName()
				+ " t WHERE t.qntd = 0 ORDER BY t.nome";
		TypedQuery<Produto> qry = getEm().createQuery(jpql, getClazz());
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public List<Produto> buscarProdutorOrdenadorPorValor() {
		String jpql = "FROM " + getClazz().getSimpleName()
				+ " t ORDER BY t.valor";
		TypedQuery<Produto> qry = getEm().createQuery(jpql, getClazz());
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Deprecated
	public void atualizaQuantidade(Long id, Integer value) {
	    //String jpql = "update " + Produto.class.getSimpleName() + " p set p.qntd = p.qntd - :qntd where p.id = :id";
	    Query qry = getEm().createNativeQuery("update Produto p set p.qntd = p.qntd - :qntd where p.id = :id");
	    qry.setParameter("qntd", value);
	    qry.setParameter("id", id);
	    int result = qry.executeUpdate();
	    if (result != 1) {
	        throw new RuntimeException("erro ao atualizar produto");
	    }
	}

	public void atualizaQuantidade(Produto produto, Integer value) throws QuantidadeDeProdutosIndisponiveis {
		produto.baixaEstoque(value);		
		getEm().merge(produto);
	}
}
