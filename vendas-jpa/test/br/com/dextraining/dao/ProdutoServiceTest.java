package br.com.dextraining.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Categoria;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.service.ProdutoService;

public class ProdutoServiceTest extends AbstractTest {

	@Test
	public void testBuscarPorValor() {
		Produto p1 = getProduto("Produto 1", "Produto Barato", 120, 37.90);
		getService(ProdutoService.class).salvar(p1);

		Produto p2 = getProduto("Produto 2", "Produto Caro", 120, 137.90);
		getService(ProdutoService.class).salvar(p2);

		List<Produto> lista = getService(ProdutoService.class).buscarProdutosComValorMaiorQue(70.0);
		Assert.assertEquals(p2, lista.get(0));
	}

	@Test
	public void testProdutosForaDoEstoque() {
		Produto p1 = getProduto("Produto 1", "Produto Barato", 0, 37.90);

		getService(ProdutoService.class).salvar(p1);

		List<Produto> lista = getService(ProdutoService.class).buscarProdutosForaEstoque();
		Assert.assertEquals(p1, lista.get(0));
	}

	@Test
	public void buscarProdutosOrdenadorPorValor() {
		getService(ProdutoService.class).salvar(getProduto("Produto 1", "Produto A", 120, 37.90));
		getService(ProdutoService.class).salvar(getProduto("Produto 2", "Produto B", 100, 17.90));
		getService(ProdutoService.class).salvar(getProduto("Produto 3", "Produto C", 80, 7.90));
		getService(ProdutoService.class).salvar(getProduto("Produto 4", "Produto D", 30, 67.90));

		List<Produto> lista = getService(ProdutoService.class).buscarProdutorOrdenadorPorValor();
		Assert.assertEquals(lista.get(0).getValor(), new Double(7.90));
		Assert.assertEquals(lista.get(1).getValor(), new Double(17.90));
		Assert.assertEquals(lista.get(2).getValor(), new Double(37.90));
		Assert.assertEquals(lista.get(3).getValor(), new Double(67.90));
	}

	@Test
	public void testCategoria() {
		Produto p1 = getProduto("Produto 1", "Produto A", 120, 37.90);

		Categoria categoria = new Categoria("Barato");
		p1.addCategoria(categoria);
		p1.addCategoria(new Categoria("Ultima Moda"));

		getService(ProdutoService.class).salvar(p1);

		p1.removeCategoria(categoria);
		getService(ProdutoService.class).salvar(p1);

	}
}
