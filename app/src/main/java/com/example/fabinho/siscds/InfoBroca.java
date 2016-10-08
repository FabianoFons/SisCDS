package com.example.fabinho.siscds;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import database.DaoAmostrador;
import database.DaoPropriedadeRural;
import database.DaoUsuarioLogado;
import model.Amostrador;
import model.PropriedadeRural;
import model.Usuario;

public class InfoBroca extends AppCompatActivity {

    private Button btBroca;
    private TextView textPR;
    private TextView textTalhao;
    private TextView textCorte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_broca);
        setTitle("Cadastro de Indormações da Broca");


    }

    public void buscaCabecalho(){
        DaoUsuarioLogado daoUsuarioLogado = new DaoUsuarioLogado(getBaseContext());
        Usuario usuario = daoUsuarioLogado.getUsuarioLogado();

        DaoAmostrador daoAmostrador = new DaoAmostrador(getBaseContext());
        Amostrador amostrador = daoAmostrador.getAmostradorByID(usuario.getId());

        textPR.setText( amostrador.getNome());
    }

}


