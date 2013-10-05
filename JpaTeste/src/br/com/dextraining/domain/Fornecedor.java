package br.com.dextraining.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Fornecedor extends Pessoa {

	@Column(nullable = false)
	private String cnpj;

	@Column(nullable = false)
	private String nomeResponsavel;

	private String ramal;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "complemento", column = @Column(name = "ea_complemento", nullable=true)),
			@AttributeOverride(name = "rua", column = @Column(name = "ea_rua", nullable=true)),
			@AttributeOverride(name = "estado", column = @Column(name = "ea_uf", nullable=true)),
			@AttributeOverride(name = "cidade", column = @Column(name = "ea_city", nullable=true)) })
	private Endereco enderecoAlternativo;

	public Endereco getEnderecoAlternativo() {
		if (enderecoAlternativo == null) {
			enderecoAlternativo = new Endereco();
		}
		return enderecoAlternativo;
	}

	public void setEnderecoAlternativo(Endereco enderecoAlternativo) {
		this.enderecoAlternativo = enderecoAlternativo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

}
