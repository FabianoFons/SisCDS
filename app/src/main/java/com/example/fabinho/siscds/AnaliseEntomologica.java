package com.example.fabinho.siscds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import database.DaoAmostrador;
import database.DaoCorte;
import database.DaoPropriedadeRural;
import database.DaoTalhao;
import database.DaoUsuarioLogado;
import model.Amostrador;
import model.Corte;
import model.PropriedadeRural;
import model.Talhao;
import model.Usuario;

public class AnaliseEntomologica extends AppCompatActivity {
    private TextView textAmostrador;
    private Spinner spPR;
    private Spinner spTL;
    private TextView textCorte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analise_entomologica);

        textAmostrador = (TextView) findViewById(R.id.textAmostrador);
        textCorte = (TextView) findViewById(R.id.textCorte);
        spPR = (Spinner) findViewById(R.id.spPR);
        spTL = (Spinner) findViewById((R.id.spTL)) ;


        buscaAmostrador();
        buscaPropriedadesRurais();
        buscaTalhao();
    }

    public void buscaAmostrador(){
        DaoUsuarioLogado daoUsuarioLogado = new DaoUsuarioLogado(getBaseContext());
        Usuario usuario = daoUsuarioLogado.getUsuarioLogado();

        DaoAmostrador daoAmostrador = new DaoAmostrador(getBaseContext());
        Amostrador amostrador = daoAmostrador.getAmostradorByID(usuario.getId());

        textAmostrador.setText("Amostrador: "+amostrador.getNome());
    }

    public void buscaPropriedadesRurais(){
        DaoPropriedadeRural daoPropriedadeRural = new DaoPropriedadeRural(getBaseContext());
        List<PropriedadeRural> listaPR = daoPropriedadeRural.getPropriedadesRurais();
        ArrayAdapter<PropriedadeRural> advPR =
                new ArrayAdapter<PropriedadeRural>(this,
                                                   android.R.layout.simple_list_item_1,
                                                   listaPR);
        spPR.setAdapter(advPR);
    }

    public void buscaTalhao(){
        DaoTalhao daoTalhao = new DaoTalhao(getBaseContext());
       List<Talhao> listaTL = daoTalhao.getTalhao();
        ArrayAdapter<Talhao> advTL =
                new ArrayAdapter <Talhao>(this,
                        android.R.layout.simple_list_item_1,
                        listaTL);
        spTL.setAdapter(advTL);
    }

    public void buscaCorte(){
        DaoCorte daoCorte = new DaoCorte(getBaseContext());
        Corte corte = daoCorte.getTipo();


        textCorte.setText(corte.getTipo());
    }
}
