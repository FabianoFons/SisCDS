package com.example.fabinho.siscds;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import database.DaoConsulta;

public class Consulta extends AppCompatActivity {
    private TextView textBG;
    private TextView textBP;
    private TextView textCrisalida;
    private TextView textPupas;
    private TextView textTotalNos;
    private TextView textBrocados;
    private TextView textPV;
    //private ListView lvConsulta;
    private Button btVoltar;

    int brocaGrande;
    int brocaPequena;
    int crisalida;
    int pupas;
    int totalNo;
    int brocados;
    int podridaoVermelha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Consulta");

        Bundle b = getIntent().getExtras();
        brocaGrande = b.getInt("brocaGrande");

        textBG = (TextView) findViewById(R.id.textPR);

        textBG.setText(brocaGrande);

        botaoVoltar();
            }


    public void botaoVoltar(){
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btVoltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getBaseContext(), AnaliseEntomologica.class);
                        startActivity(intent);
                    }
                });
            }

        });
    }

}












