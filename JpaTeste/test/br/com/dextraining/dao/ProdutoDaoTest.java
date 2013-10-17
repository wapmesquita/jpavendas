package br.com.dextraining.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Categoria;
import br.com.dextraining.domain.Produto;

public class ProdutoDaoTest {

	@Test
	public void testBuscarPorValor() {
		ProdutoDao dao = new ProdutoDao(true);

		Produto p1 = new Produto();
		p1.setDescricao("Produto 1");
		p1.setNome("Produto Barato");
		p1.setQntd(120);
		p1.setValor(37.90);

		dao.salvar(p1);

		Produto p2 = new Produto();
		p2.setDescricao("Produto 2");
		p2.setNome("Produto Caro");
		p2.setQntd(120);
		p2.setValor(137.90);

		dao.salvar(p2);

		List<Produto> lista = dao.buscarProdutosComValorMaiorQue(70.0);
		Assert.assertEquals(p2, lista.get(0));
	}

	@Test
	public void testProdutosForaDoEstoque() {
		ProdutoDao dao = new ProdutoDao(true);

		Produto p1 = new Produto();
		p1.setDescricao("Produto 3");
		p1.setNome("Produto Barato");
		p1.setQntd(0);
		p1.setValor(47.90);

		dao.salvar(p1);

		List<Produto> lista = dao.buscarProdutosForaEstoque();
		Assert.assertEquals(p1, lista.get(0));
	}

	@Test
	public void buscarProdutorOrdenadorPorValor() {
		ProdutoDao dao = new ProdutoDao(true);

		Produto p1 = new Produto();
		p1.setDescricao("Produto 3");
		p1.setNome("Produto Barato");
		p1.setQntd(30);
		p1.setValor(10.90);

		dao.salvar(p1);

		List<Produto> lista = dao.buscarProdutorOrdenadorPorValor();
		Assert.assertTrue(lista.get(0).getValor().equals(new Double(10.90)));
	}

	@Test
	public void testCategoria() {
		ProdutoDao dao = new ProdutoDao(true);

		Produto p1 = new Produto();
		p1.setDescricao("Produto 3");
		p1.setNome("Produto Barato");
		p1.setQntd(30);
		p1.setValor(10.90);

		Categoria categoria = new Categoria("Barato");
		p1.addCategoria(categoria);
		p1.addCategoria(new Categoria("Ultima Moda"));

		dao.salvar(p1);

		GenericDao<Categoria> categoriaDao = new GenericDao<Categoria>(Categoria.class, true);
		Categoria cat = categoriaDao.buscarPorId(p1.getCategoria().get(0).getId());

		p1.removeCategoria(cat);
		dao.salvar(p1);

		categoriaDao.remover(cat);
	}
}
