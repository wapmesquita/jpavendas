package br.com.dextraining.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "TABELA_PESSOA")
@DiscriminatorColumn(name = "tipoPessoa")
public abstract class Pessoa extends AbstractEntity {

    @Column(name = "NOME_T", nullable = false)
    private String nome;

    @Column(length = 20)
    private String telefone;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "estado", column = @Column(name = "UF")) })
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            this.endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
