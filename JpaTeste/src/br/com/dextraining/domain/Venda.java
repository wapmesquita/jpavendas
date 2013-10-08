package br.com.dextraining.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venda extends AbstractEntity {

    @ManyToOne(optional=true, cascade=CascadeType.REMOVE)
    private Cliente cliente;

    @ManyToOne(optional=false, cascade=CascadeType.REMOVE)
    private Funcionario funcionario;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="venda_id")
    private List<ItemVenda> itens;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(nullable = false, precision = 2)
    private Double valor;

}
