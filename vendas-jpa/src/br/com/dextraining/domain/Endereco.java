package br.com.dextraining.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Endereco {

    @Column(nullable = false, length = 255)
    private String rua;

    @Column(nullable = false, length = 255)
    private String cidade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UF estado;

    @Column(length = 255)
    private String complemento;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UF getEstado() {
        return estado;
    }

    public void setEstado(UF estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
