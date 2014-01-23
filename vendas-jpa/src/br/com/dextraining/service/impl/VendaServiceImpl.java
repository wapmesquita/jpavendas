package br.com.dextraining.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.dextraining.domain.ItemVenda;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.domain.Venda;
import br.com.dextraining.domain.query.VendaAcumuladaData;
import br.com.dextraining.exception.QuantidadeDeProdutosIndisponiveis;
import br.com.dextraining.service.ProdutoService;
import br.com.dextraining.service.ServiceFactory;
import br.com.dextraining.service.VendaService;

public class VendaServiceImpl extends AbstractServiceImpl<Venda> implements VendaService {

	public VendaServiceImpl() {
		super(Venda.class);
	}

	@Override
	public void salvar(Venda venda) {
		if (venda.getId() != null) {
			throw new RuntimeException("Venda não pode ser alterada");
		}

		ProdutoService produtoService = ServiceFactory.service(ProdutoService.class);

		for (ItemVenda item : venda.getItens()) {
			try {
				produtoService.atualizaQuantidade(item.getProduto(), item.getQntd());
			} catch (QuantidadeDeProdutosIndisponiveis e) {
				throw new RuntimeException(e);
			}
		}

		venda.setData(new Date());
		getEm().persist(venda);

	}

	public List<Venda> buscarVendasDoFuncionario(Long funcionarioId) {
		String jpql = "FROM " + getClazz().getSimpleName() + " v JOIN FETCH v.itens i WHERE v.funcionario.id = :id";
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

	public List<Venda> buscarVendasDoProduto(Produto produto) {
		StringBuilder jpql = new StringBuilder("SELECT venda FROM ");
		jpql.append(getClazz().getSimpleName()).append(" venda INNER JOIN venda.itens item ");
		jpql.append(" WHERE item.produto = :produto");

		TypedQuery<Venda> qry = getEm().createQuery(jpql.toString(), getClazz());
		qry.setParameter("produto", produto);
		try {
			return qry.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public List<VendaAcumuladaData> buscarVendaAcumuladas(Date inicio, Date fim) {
		StringBuilder sb = new StringBuilder("SELECT new ");
		sb.append(VendaAcumuladaData.class.getName());
		sb.append("(v.data, SUM(v.valor)) FROM ");
		sb.append(Venda.class.getSimpleName());
		sb.append(" v WHERE v.data BETWEEN :inicio AND :fim");
		sb.append(" GROUP BY v.data ");
		sb.append(" HAVING SUM(v.valor) > 10.0");
		System.out.println(sb.toString());

		TypedQuery<VendaAcumuladaData> qry = getEm().createQuery(sb.toString(), VendaAcumuladaData.class);
		qry.setParameter("inicio", inicio);
		qry.setParameter("fim", fim);
		return qry.getResultList();
	}
}