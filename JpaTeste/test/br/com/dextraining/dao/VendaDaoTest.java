package br.com.dextraining.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Cliente;
import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.ItemVenda;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Venda;
import br.com.dextraining.domain.query.VendaAcumuladaData;

public class VendaDaoTest {

	@Test
	public void testNovaVenda() {
		Funcionario f = cadastrarFuncionario();
		Cliente c = cadastrarCliente();
		List<Produto> produtos = cadastrarProdutos();

		Venda venda = new Venda(c, f);

		VendaDao dao = new VendaDao(true);

		ItemVenda item = null;
		for (int i = 8; i >= 2; i--) {
			item = new ItemVenda(venda, produtos.get(i), i * 2, 0.13 * i * 1.34);
			venda.addItem(item);
		}

		dao.salvar(venda);

		ProdutoDao produtoDao = new ProdutoDao();
		Produto produtoVenda2 = produtoDao.buscarPorId(produtos.get(5).getId());
		int qntdProdutoVenda2 = produtoVenda2.getQntd();

		Venda venda2 = new Venda(null, f);

		venda2.addItem(new ItemVenda(venda2, produtoVenda2, 1, 0.0));

		dao.salvar(venda2);

		Assert.assertEquals(new Integer(qntdProdutoVenda2 - 1), produtoDao.buscarPorId(produtoVenda2.getId()).getQntd());

		Venda buscarPorId = dao.buscarPorId(venda.getId());
		System.out.println(buscarPorId.toString());

		EntityManagerFactoryWrapper.renovar();

		System.out.println("\n\n\n\nSimulando Lazy");
		buscarPorId = dao.buscarPorId(1L);
		Assert.assertNotNull(buscarPorId);
		System.out.println(buscarPorId.toString());
		System.out.println("Simulando Lazy\n\n\n\n");
		
		System.out.println("Buscar por Funcionario");
		List<Venda> vendasFuncionario = dao.buscarVendasDoFuncionario(f.getId());
		Assert.assertEquals(vendasFuncionario.get(0), buscarPorId);
		Assert.assertEquals(7, vendasFuncionario.get(0).getItens().size());

		System.out.println("Buscar por Cliente");
		List<Venda> vendasCliente = dao.buscarVendasParaCliente(c.getId());
		Assert.assertEquals(vendasCliente.get(0), buscarPorId);

		System.out.println("Buscar por Produto");
		List<Venda> vendasDoProduto = dao.buscarVendasDoProduto(produtos.get(3));
		Assert.assertEquals(vendasDoProduto.get(0), buscarPorId);

		System.out.println("Buscar por Acumuladas");
		List<VendaAcumuladaData> acumuladas = dao.buscarVendaAcumuladas(getOutraDate(-1), getOutraDate(1));
		Assert.assertEquals(1, acumuladas.size());
		Assert.assertEquals(new Double(1572.8948000000003), acumuladas.get(0).getValor());

		// new FuncionarioDao(true).remover(f);
		dao.remover(buscarPorId);
		// new ProdutoDao(true).remover(produtos.get(2));
	}

	private List<Produto> cadastrarProdutos() {
		ProdutoDao dao = new ProdutoDao(true);
		String[] nomeProduto = { "Cerveja", "Chocolate", "Alface", "Refrigerante", "Acucar", "Cafe", "Ovo", "Bala", "Suco", "Batata Frita", "Leite" };
		List<Produto> produtos = new ArrayList<Produto>();
		Produto p = null;
		for (int i = 1; i <= 10; i++) {
			p = new Produto();
			p.setNome(nomeProduto[i - 1]);
			p.setQntd(i * 10);
			p.setValor(i * 3.41);
			dao.salvar(p);
			produtos.add(p);
		}
		return produtos;
	}

	private Cliente cadastrarCliente() {
		Cliente c = new Cliente();
		c.setNome("Joao Silva");
		c.setCpf("111.111.111-11");
		c.getEndereco().setCidade("Campinas");
		c.getEndereco().setEstado(UF.SP);
		c.getEndereco().setRua("Rua 1");
		c.setNumeroCartao("2354 2134 3214 3214");

		GenericDao<Cliente> dao = new GenericDao<Cliente>(Cliente.class, true);
		dao.salvar(c);

		return c;
	}

	private Funcionario cadastrarFuncionario() {
		Funcionario f = new Funcionario();
		f.setNome("Joao Silva");
		f.setMatricula("123412341");
		f.setSalario(4502.65);
		f.setCpf("111.111.111-11");
		f.getEndereco().setCidade("Campinas");
		f.getEndereco().setEstado(UF.SP);
		f.getEndereco().setRua("Rua 1");

		GenericDao<Funcionario> dao = new GenericDao<Funcionario>(Funcionario.class, true);
		dao.salvar(f);
		return f;
	}

	private Date getOutraDate(int key) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.roll(Calendar.MONTH, key);
		return cal.getTime();
	}
}
