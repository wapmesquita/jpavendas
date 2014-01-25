package br.com.dextraining.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.compras.Fornecedor;
import br.com.dextraining.service.FornecedorService;

public class FornecedorServiceTest extends AbstractTest {

	@Test
	public void test01OrdenadoPorEstado() {
		Fornecedor f = getFornecedor("Fornecedor 1", "123451234523453", "JOAO", getEndereco("campinas", UF.SP, "Rua 2"));

		getService(FornecedorService.class).salvar(f);

		Fornecedor f2 = getFornecedor("Fornecedor 2", "123451234523453", "MARIO", getEndereco("Campinas", UF.ES, "Rua 2"));

		getService(FornecedorService.class).salvar(f2);

		List<Fornecedor> list = getService(FornecedorService.class).buscarOrdenadoPorEstado("For");
		Assert.assertTrue(list.size() == 2);
		Assert.assertEquals(f2, list.get(0));

		Map<String, Object> filtros = new HashMap<String, Object>();
		filtros.put("nomeResponsavel", "JOAO");
		filtros.put("cnpj", "123451234523453");

		List<Fornecedor> buscarPorFiltro = getService(FornecedorService.class).buscarPorFiltro(filtros);
		Assert.assertEquals(1, buscarPorFiltro.size());

		getService(FornecedorService.class).remover(f);

		list = getService(FornecedorService.class).buscarTodos();
		Assert.assertTrue(list.size() == 1);
	}

}
