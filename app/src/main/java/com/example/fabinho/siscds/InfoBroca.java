package com.example.fabinho.siscds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import database.DaoAmostrador;
import database.DaoPropriedadeRural;
import database.DaoTalhao;
import database.DaoUsuarioLogado;
import model.Amostrador;
import model.Broca;
import model.PropriedadeRural;
import model.Talhao;
import model.Usuario;

public class InfoBroca extends AppCompatActivity {

    private Button btSalvar;
    private TextView textPR;
    private TextView textTalhao;
    private TextView textCorte;
    private EditText edBG;
    private EditText edBP;
    private EditText edCrisalida;
    private EditText edPupa;
    private EditText edTotalNo;
    private EditText edBrocado;
    private EditText edPV;



    int codPropriedade;
    String nomePropriedade;
    int codTalhao;
    String talhao;
    Integer corte;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_broca);
        setTitle("Cadastro de Indormações da Broca");

        btSalvar = (Button) findViewById(R.id.btSalvar);
        textPR = (TextView) findViewById(R.id.textPR);
        textTalhao = (TextView) findViewById(R.id.textTalhao);
        textCorte = (TextView) findViewById(R.id.textCorte);
        edBG = (EditText) findViewById((R.id.edBG));
        edBP = (EditText) findViewById((R.id.edBP));
        edCrisalida = (EditText) findViewById((R.id.edCrisalida));
        edPupa = (EditText) findViewById((R.id.edPupa));
        edTotalNo = (EditText) findViewById((R.id.edTotalNo));
        edBrocado = (EditText) findViewById((R.id.edBrocado));
        edPV = (EditText) findViewById((R.id.edPV));


        textPR.setText("Propriedade Rural: "+nomePropriedade);
        textTalhao.setText("Talhao: "+talhao);
        textCorte.setText("Corte: "+corte);

        botaoSalvar();
    }

    public void botaoSalvar(){
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validando os campos
                if (edBG.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Informe quantidade de Broca Grande", Toast.LENGTH_LONG).show();
                } else {
                    if (edBP.getText().toString().equals("")) {
                        Toast.makeText(getBaseContext(), "Informe quantidade de Broca Pequena", Toast.LENGTH_LONG).show();
                } else {
                    if (edCrisalida.getText().toString().equals("")) {
                            Toast.makeText(getBaseContext(), "Informe quantidade de Crisalidas", Toast.LENGTH_LONG).show();
                } else {
                    if (edPupa.getText().toString().equals("")) {
                                Toast.makeText(getBaseContext(), "Informe quantidade de Pupas", Toast.LENGTH_LONG).show();
                } else {
                    if (edTotalNo.getText().toString().equals("")) {
                                    Toast.makeText(getBaseContext(), "Informe o Total de Nós", Toast.LENGTH_LONG).show();
                } else {
                    if (edBrocado.getText().toString().equals("")) {
                                        Toast.makeText(getBaseContext(), "Informe quantidade de Canas Brocadas", Toast.LENGTH_LONG).show();
                } else {
                    if (edPV.getText().toString().equals("")) {
                                            Toast.makeText(getBaseContext(), "Informe quantidade de Podridão Vermelha", Toast.LENGTH_LONG).show();
                }else{
                    finish();
                    }
                    }
                    }
                    }
                    }
                    }
                }
            }


    });
    }

    //Envia informações e pega o retorno dos dados
    @Override
    public void finish() {
        Bundle bundle = new Bundle();


        bundle.putString("brocaGrande", edBG.getText().toString());
        bundle.putString("brocaPequena",edBP.getText().toString());
        bundle.putString("crisalida",edCrisalida.getText().toString());
        bundle.putString("pupas",edPupa.getText().toString());
        bundle.putString("totalNos",edTotalNo.getText().toString());
        bundle.putString("brocados",edBrocado.getText().toString());
        bundle.putString("podridaoVermelha",edPV.getText().toString());

        Intent intent = new Intent();
        intent.putExtras(bundle);

        setResult(RESULT_OK,intent);

        super.finish();
    }
}