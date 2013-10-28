package br.com.dextraining.domain.query;

import java.util.Date;

public class VendaAcumuladaData {

	private Date data;
	private Double valor;

	public VendaAcumuladaData(Date data, Double valor) {
		super();
		this.data = data;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public Double getValor() {
		return valor;
	}

}
