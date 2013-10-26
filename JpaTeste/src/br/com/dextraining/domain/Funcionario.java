package br.com.dextraining.domain;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
	@NamedQuery(name = "Funcionario.buscarPorNome",
				query = "SELECT f FROM Funcionario f WHERE f.nome like :nome",
				hints = { 
					@QueryHint(name = "org.hibernate.cacheable", 
							value = "true") }) })
public class Funcionario extends PessoaFisica {

	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Usuario usuario;

	@Column
	private String matricula;

	@Column(precision = 2)
	private Double Salario;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Double getSalario() {
		return Salario;
	}

	public void setSalario(Double salario) {
		Salario = salario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
