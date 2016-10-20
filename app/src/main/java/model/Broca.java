package model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fabinho.siscds.R;

/**
 * Created by Fabiano on 05/10/2016.
 */


public class Broca {
    private Integer id;
    private Integer idTalhao;
    private Integer brocaGrande;
    private Integer brocaPequena;
    private Integer crisalida;
    private Integer pupas;
    private Integer totalNos;
    private Integer brocados;
    private Integer podridaoVermelha;

    public Integer getBrocaGrande() {
        return brocaGrande;
    }

    public void setBrocaGrande(Integer brocaGrande) {
        this.brocaGrande = brocaGrande;
    }

    public Integer getBrocaPequena() {
        return brocaPequena;
    }

    public void setBrocaPequena(Integer brocaPequena) {
        this.brocaPequena = brocaPequena;
    }

    public Integer getCrisalida() {
        return crisalida;
    }

    public void setCrisalida(Integer crisalida) {
        this.crisalida = crisalida;
    }

    public Integer getPupas() {
        return pupas;
    }

    public void setPupas(Integer pupas) {
        this.pupas = pupas;
    }

    public Integer getTotalNos() {
        return totalNos;
    }

    public void setTotalNos(Integer totalNos) {
        this.totalNos = totalNos;
    }

    public Integer getBrocados() {
        return brocados;
    }

    public void setBrocados(Integer brocados) {
        this.brocados = brocados;
    }

    public Integer getPodridaoVermelha() {
        return podridaoVermelha;
    }

    public void setPodridaoVermelha(Integer podridaoVermelha) {
        this.podridaoVermelha = podridaoVermelha;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}