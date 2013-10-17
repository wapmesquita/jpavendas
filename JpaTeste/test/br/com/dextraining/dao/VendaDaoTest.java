package br.com.dextraining.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Cliente;
import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.ItemVenda;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Venda;


public class VendaDaoTest {

    @Test
    public void testNovaVenda() {
        Funcionario f = cadastrarFuncionario();
        Cliente c = cadastrarCliente();
        List<Produto> produtos = cadastrarProdutos();

        Venda venda = new Venda(c, f);

        VendaDao dao = new VendaDao(true);

        ItemVenda item = null;
        for (int i=8; i>=2; i--) {
            item = new ItemVenda(produtos.get(i), i*2, 0.13*i*1.34);
            venda.addItem(item);
        }

        dao.salvar(venda);

        Venda id = dao.buscarPorId(venda.getId());
        System.out.println(id.toString());

        List<Venda> vendasFuncionario = dao.buscarVendasDoFuncionario(f.getId());
        Assert.assertEquals(vendasFuncionario.get(0), venda);

        List<Venda> vendasCliente = dao.buscarVendasParaCliente(c.getId());
        Assert.assertEquals(vendasCliente.get(0), venda);

        //new FuncionarioDao(true).remover(f);
        dao.remover(venda);
        //new ProdutoDao(true).remover(produtos.get(2));
    }

    private List<Produto> cadastrarProdutos() {
        ProdutoDao dao = new ProdutoDao(true);
        String[] nomeProduto = {"Cerveja", "Chocolate", "Alface", "Refrigerante", "Acucar", "Cafe", "Ovo", "Bala", "Suco", "Batata Frita", "Leite"};
        List<Produto> produtos = new ArrayList<Produto>();
        Produto p = null;
        for (int i = 1; i <= 10; i++) {
            p = new Produto();
            p.setNome(nomeProduto[i-1]);
            p.setQntd(i *10);
            p.setValor(i*3.41);
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

}
