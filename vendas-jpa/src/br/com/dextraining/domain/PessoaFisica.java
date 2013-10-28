package br.com.dextraining.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PessoaFisica extends Pessoa {

    @Column(length = 20, nullable = false)
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
