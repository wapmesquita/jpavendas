package br.com.dextraining.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

import br.com.dextraining.domain.Cliente;
import br.com.dextraining.domain.Endereco;
import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.Produto;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.domain.compras.Fornecedor;
import br.com.dextraining.jpa.EntityManagerFactoryWrapper;
import br.com.dextraining.service.ServiceFactory;

public abstract class AbstractTest {

	private Map<Class<?>, Object> services = new HashMap<Class<?>, Object>();

	@Before
	public void before() {
		EntityManagerFactoryWrapper.start();
	}

	@After
	public void after() {
		EntityManagerFactoryWrapper.shutdown();
	}

	protected <T> T getService(Class<T> clazz) {
		@SuppressWarnings("unchecked")
		T value = (T) services.get(clazz);
		if (value == null) {
			value = ServiceFactory.service(clazz);
			services.put(clazz, value);
		}
		return value;
	}

	protected Funcionario getFuncionario(String cpf, String nome, Double salario, Endereco endereco) {
		Funcionario f = new Funcionario();
		f.setCpf(cpf);
		f.setNome(nome);
		f.setSalario(salario);
		f.setEndereco(endereco);
		return f;
	}

	protected Usuario getUsuario(String login, String senha) {
		Usuario u = new Usuario();
		u.setLogin(login);
		u.setSenha(senha);
		return u;
	}

	protected Endereco getEndereco(String cidade, UF uf, String rua) {
		Endereco e = new Endereco();
		e.setCidade(cidade);
		e.setEstado(uf);
		e.setRua(rua);
		return e;
	}

	protected Fornecedor getFornecedor(String nome, String cnpj, String nomeResponsavel, Endereco endereco) {
		Fornecedor f = new Fornecedor();
		f.setNome(nome);
		f.setCnpj(cnpj);
		f.setEndereco(endereco);
		f.setNomeResponsavel(nomeResponsavel);
		return f;
	}

	protected Produto getProduto(String descricao, String nome, int qntd, double valor) {
		Produto p1 = new Produto();
		p1.setDescricao(descricao);
		p1.setNome(nome);
		p1.setQntd(qntd);
		p1.setValor(valor);
		return p1;
	}

	protected Cliente getCliente(String nome, String cpf, String cidade, UF sp, String rua, String numeroCartao) {
		Cliente c = new Cliente();
		c.setNome(nome);
		c.setCpf(cpf);
		c.getEndereco().setCidade(cidade);
		c.getEndereco().setEstado(sp);
		c.getEndereco().setRua(rua);
		c.setNumeroCartao(numeroCartao);
		return c;
	}

}
