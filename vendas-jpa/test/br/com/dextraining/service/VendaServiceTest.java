package br.com.dextraining.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Cliente;
import br.com.dextraining.domain.Estoque;
import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.ItemVenda;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.domain.Venda;
import br.com.dextraining.domain.query.VendaAcumuladaData;
import br.com.dextraining.jpa.EntityManagerFactoryWrapper;

public class VendaServiceTest extends AbstractTest {

	@Test
	public void testNovaVenda() {
		Cliente c = getCliente("Joao Silva", "111.111.111-11", "Campinas", UF.SP, "Rua 1", "2354 2134 3214 3214");
		getService(ClienteService.class).salvar(c);

		Funcionario f = getFuncionario("111.111.111-11", "Joao", 2000.00, getEndereco("campinas", UF.SP, "Rua 1"));
		Usuario u = getUsuario("joao", "joao");
		u.setFuncionario(f);
		getService(UsuarioService.class).salvar(u);

		List<Produto> produtos = cadastrarProdutos();

		Venda venda = new Venda(c, f);

		ItemVenda item = null;
		for (int i = 8; i >= 2; i--) {
			item = new ItemVenda(venda, produtos.get(i), i * 2, 0.13 * i * 1.34);
			venda.addItem(item);
		}

		getService(VendaService.class).salvar(venda);

		Produto produtoVenda2 = getService(ProdutoService.class).buscarPorId(produtos.get(5).getId());

		Venda venda2 = new Venda(null, f);

		venda2.addItem(new ItemVenda(venda2, produtoVenda2, 1, 0.0));

		getService(VendaService.class).salvar(venda2);

		Venda buscarPorId = getService(VendaService.class).buscarPorId(venda.getId());
		System.out.println(buscarPorId.toString());

		EntityManagerFactoryWrapper.renovar();

		System.out.println("\n\nnSimulando Lazy");
		buscarPorId = getService(VendaService.class).buscarPorId(1L);
		System.out.println(buscarPorId);
		Assert.assertNotNull(buscarPorId);
		System.out.println(buscarPorId.toString());

		System.out.println("\n\nBuscar por Funcionario");
		List<Venda> vendasFuncionario = getService(VendaService.class).buscarVendasDoFuncionario(f.getId());
		Assert.assertEquals(vendasFuncionario.get(0), buscarPorId);
		Assert.assertEquals(7, vendasFuncionario.get(0).getItens().size());

		System.out.println("\n\nBuscar por Cliente");
		List<Venda> vendasCliente = getService(VendaService.class).buscarVendasParaCliente(c.getId());
		System.out.println(vendasCliente.get(0));
		Assert.assertEquals(vendasCliente.get(0), buscarPorId);

		System.out.println("\n\nBuscar por Produto");
		List<Venda> vendasDoProduto = getService(VendaService.class).buscarVendasDoProduto(produtos.get(3));
		Assert.assertEquals(vendasDoProduto.get(0), buscarPorId);
		System.out.println(vendasDoProduto.get(0));

		System.out.println("\n\nBuscar por Acumuladas");
		List<VendaAcumuladaData> acumuladas = getService(VendaService.class).buscarVendaAcumuladas(getOutraDate(-1), getOutraDate(1));
		Assert.assertEquals(1, acumuladas.size());
		Assert.assertEquals(new Double(1572.8948000000003), acumuladas.get(0).getValor());

		getService(VendaService.class).remover(buscarPorId);
	}

	private List<Produto> cadastrarProdutos() {
		String[] nomeProduto = { "Cerveja", "Chocolate", "Alface", "Refrigerante", "Acucar", "Cafe", "Ovo", "Bala", "Suco", "Batata Frita", "Leite" };
		List<Produto> produtos = new ArrayList<Produto>();
		Produto p = null;
		for (int i = 1; i <= 10; i++) {
			p = new Produto();
			p.setNome(nomeProduto[i - 1]);
			p.setValor(i * 3.41);
			getService(ProdutoService.class).salvar(p);
			getService(EstoqueService.class).salvar(Estoque.entrada(p, 40));
			produtos.add(p);
		}
		return produtos;
	}

	private Date getOutraDate(int key) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.roll(Calendar.YEAR, key);
		return cal.getTime();
	}
}
