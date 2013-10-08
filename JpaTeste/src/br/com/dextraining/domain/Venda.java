package br.com.dextraining.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venda extends AbstractEntity {

    @ManyToOne(optional=true)
    private Cliente cliente;

    @ManyToOne(optional=false)
    private Funcionario funcionario;

    @OneToMany
    private List<ItemVenda> itens;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(nullable = false, precision = 2)
    private Double valor;

}
