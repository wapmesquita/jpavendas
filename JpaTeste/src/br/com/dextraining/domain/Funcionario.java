package br.com.dextraining.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Funcionario extends PessoaFisica {

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

}
