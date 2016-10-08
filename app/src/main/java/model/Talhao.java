package model;

import java.sql.Date;

/**
 * Created by Fabiano on 27/09/2016.
 */

public class Talhao {
    private Integer id;
    private String identificacao;
    private String area;
    private Date data;
    private int idPropriedade;
    private int corte;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString (){
        return identificacao + " / " + area;
    }

    public void setIdPropriedade(int idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public int getIdPropriedade() {
        return idPropriedade;
    }

    public void setCorte(int corte) {
        this.corte = corte;
    }

    public int getCorte() {
        return corte;
    }
}
