package br.com.dextraining.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Fornecedor;
import br.com.dextraining.domain.UF;

public class FornecedorDaoTest {

    @Test
    public void test01OrdenadoPorEstado() {
        Fornecedor f = new Fornecedor();
        f.setNome("Fornecedor 1");
        f.setCnpj("123451234523453");
        f.getEndereco().setCidade("Campinas");
        f.getEndereco().setEstado(UF.SP);
        f.getEndereco().setRua("Rua 1");
        f.setNomeResponsavel("JOAO");

        FornecedorDao dao = new FornecedorDao(true);
        dao.salvar(f);

        Fornecedor f2 = new Fornecedor();
        f2.setNome("Fornecedor 2");
        f2.setCnpj("123451234523453");
        f2.getEndereco().setCidade("Campinas");
        f2.getEndereco().setEstado(UF.ES);
        f2.getEndereco().setRua("Rua 1");
        f2.setNomeResponsavel("JOAO");

        dao.salvar(f2);

        List<Fornecedor> list = dao.buscarOrdenadoPorEstado("For");
        Assert.assertTrue(list.size() > 1);
        Assert.assertEquals(f2, list.get(0));

        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("nomeResponsavel", "JOAO");
        filtros.put("cnpj", "123451234523453");

        List<Fornecedor> buscarPorFiltro = dao.buscarPorFiltro(filtros);
        Assert.assertEquals(2, buscarPorFiltro.size());

    }

}
