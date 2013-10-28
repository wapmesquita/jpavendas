package br.com.dextraining.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cliente extends PessoaFisica {

    @Column(length=20)
	private String numeroCartao;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

}
