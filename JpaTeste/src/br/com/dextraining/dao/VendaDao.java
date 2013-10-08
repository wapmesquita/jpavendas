package br.com.dextraining.dao;

import java.util.Date;

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
}