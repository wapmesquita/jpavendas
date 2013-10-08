package br.com.dextraining.domain;

import java.util.ArrayList;
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

import flexjson.JSONSerializer;

@Entity
public class Venda extends AbstractEntity {

    @ManyToOne(optional = true)
    private Cliente cliente;

    @ManyToOne(optional = false)
    private Funcionario funcionario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venda_id")
    private List<ItemVenda> itens;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(nullable = false, precision = 2, scale = 2)
    private Double valor = 0.0;

    public Venda(Cliente cliente, Funcionario funcionario) {
        super();
        this.cliente = cliente;
        this.funcionario = funcionario;
    }

    public Venda() {
        // Metodo gerado para jpa
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public List<ItemVenda> getItens() {
        if (itens == null) {
            itens = new ArrayList<ItemVenda>();
        }
        return itens;
    }

    public void addItem(ItemVenda item) {
        getItens().add(item);
        this.valor += item.getValorFinal();
    }

    public void removeItem(ItemVenda item) {
        getItens().remove(item);
        this.valor -= item.getValorFinal();
    }

    public Date getData() {
        return data;
    }

    public Double getValor() {
        return valor;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        JSONSerializer serializer = new JSONSerializer();
        serializer.exclude("*.class");
        return serializer.deepSerialize(this);
    }
}
