package model;

/**
 * Created by Fabiano on 27/09/2016.
 */

public class PropriedadeRural {
    private int id;
    private String nome;
    private String identificacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public int getId() {
        return id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    @Override
    public String toString() {
        return identificacao+" / "+nome;
    }
}
