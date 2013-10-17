package br.com.dextraining.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.ItemVenda;
import br.com.dextraining.domain.Venda;

public class VendaDao extends GenericDao<Venda> {

	public VendaDao(boolean gerenciaTransacao) {
		super(Venda.class, gerenciaTransacao);
	}

	public VendaDao() {
		super(Venda.class);
	}

	@Override
	public void salvar(Venda venda) {
		if (venda.getId() != null) {
			throw new RuntimeException("Venda não pode ser alterada");
		}

		init();
		ProdutoDao produtoDao = new ProdutoDao(false);

		for (ItemVenda item : venda.getItens()) {
			produtoDao.atualizaQuantidade(item.getProduto().getId(), item.getQntd());
		}

		venda.setData(new Date());
		getEm().persist(venda);

		commit();
	}

	public List<Venda> buscarVendasDoFuncionario(Long funcionarioId) {
		String jpql = "FROM " + getClazz().getSimpleName() + " v WHERE v.funcionario.id = :id";
		TypedQuery<Venda> qry = getEm().createQuery(jpql, getClazz());
		qry.setParameter("id", funcionarioId);
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public List<Venda> buscarVendasParaCliente(Long clienteId) {
		String jpql = "FROM " + getClazz().getSimpleName() + " v WHERE v.cliente.id = :id";
		TypedQuery<Venda> qry = getEm().createQuery(jpql, getClazz());
		qry.setParameter("id", clienteId);
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}