package br.com.dextraining.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.dextraining.domain.Categoria;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.service.ProdutoService;
import br.com.dextraining.service.ServiceFactory;

public class ProdutoDaoTest extends AbstractTest {

	@Test
	public void testBuscarPorValor() {
		ProdutoService service = ServiceFactory.service(ProdutoService.class);

		Produto p1 = new Produto();
		p1.setDescricao("Produto 1");
		p1.setNome("Produto Barato");
		p1.setQntd(120);
		p1.setValor(37.90);

		service.salvar(p1);

		Produto p2 = new Produto();
		p2.setDescricao("Produto 2");
		p2.setNome("Produto Caro");
		p2.setQntd(120);
		p2.setValor(137.90);

		service.salvar(p2);

		List<Produto> lista = service.buscarProdutosComValorMaiorQue(70.0);
		Assert.assertEquals(p2, lista.get(0));
	}

	@Test
	public void testProdutosForaDoEstoque() {
		ProdutoService service = ServiceFactory.service(ProdutoService.class);

		Produto p1 = new Produto();
		p1.setDescricao("Produto 3");
		p1.setNome("Produto Barato");
		p1.setQntd(0);
		p1.setValor(47.90);

		service.salvar(p1);

		List<Produto> lista = service.buscarProdutosForaEstoque();
		Assert.assertEquals(p1, lista.get(0));
	}

	@Ignore
	@Test
	public void buscarProdutorOrdenadorPorValor() {
		ProdutoService service = ServiceFactory.service(ProdutoService.class);

		Produto p1 = new Produto();
		p1.setDescricao("Produto 3");
		p1.setNome("Produto Barato");
		p1.setQntd(30);
		p1.setValor(10.90);

		service.salvar(p1);

		List<Produto> lista = service.buscarProdutorOrdenadorPorValor();
		Assert.assertEquals(lista.get(0).getValor(), new Double(10.90));
	}

	@Test
	public void testCategoria() {
		ProdutoService service = ServiceFactory.service(ProdutoService.class);

		Produto p1 = new Produto();
		p1.setDescricao("Produto 3");
		p1.setNome("Produto Barato");
		p1.setQntd(30);
		p1.setValor(10.90);

		Categoria categoria = new Categoria("Barato");
		p1.addCategoria(categoria);
		p1.addCategoria(new Categoria("Ultima Moda"));

		service.salvar(p1);

		//TODO Fazer teste para categoria
		/*
		AbstractServiceImpl<Categoria> categoriaDao = new AbstractServiceImpl<Categoria>(Categoria.class, true);
		Categoria cat = categoriaDao.buscarPorId(p1.getCategorias().get(0).getId());

		p1.removeCategoria(cat);
		service.salvar(p1);

		categoriaDao.remover(cat);
		*/
	}
}
