package com.example.fabinho.siscds;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import database.DaoAmostrador;
import database.DaoCorte;
import database.DaoPropriedadeRural;
import database.DaoTalhao;
import database.DaoUsuarioLogado;
import model.Amostrador;
import model.Broca;
import model.Corte;
import model.PropriedadeRural;
import model.Talhao;
import model.Usuario;

public class AnaliseEntomologica extends AppCompatActivity {
    private TextView textAmostrador;
    private Spinner spPR;
    private Spinner spTL;
    private TextView textCorte;
    private Button btBroca;
    private Button btConsultar;
    private ArrayAdapter<PropriedadeRural> advPR;
    private ArrayAdapter<Talhao> advTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analise_entomologica);

        textAmostrador = (TextView) findViewById(R.id.textAmostrador);
        textCorte = (TextView) findViewById(R.id.textCorte);
        spPR = (Spinner) findViewById(R.id.spPR);
        spTL = (Spinner) findViewById((R.id.spTL));
        btBroca = (Button) findViewById(R.id.btBroca);
        btConsultar = (Button) findViewById(R.id.btConsultar);

        buscaAmostrador();
        buscaPropriedadesRurais();
        buscaTalhao();
        botaoBroca();
        botaoConsultar();
    }


    public void buscaAmostrador() {
        DaoUsuarioLogado daoUsuarioLogado = new DaoUsuarioLogado(getBaseContext());
        Usuario usuario = daoUsuarioLogado.getUsuarioLogado();

        DaoAmostrador daoAmostrador = new DaoAmostrador(getBaseContext());
        Amostrador amostrador = daoAmostrador.getAmostradorByID(usuario.getId());

        textAmostrador.setText("Amostrador: " + amostrador.getNome());
    }

    public void buscaPropriedadesRurais() {
        DaoPropriedadeRural daoPropriedadeRural = new DaoPropriedadeRural(getBaseContext());
        List<PropriedadeRural> listaPR = daoPropriedadeRural.getPropriedadesRurais();
        advPR =
                new ArrayAdapter<PropriedadeRural>(this,
                        android.R.layout.simple_list_item_1,
                        listaPR);
        spPR.setAdapter(advPR);
    }

    public void buscaTalhao() {

        spPR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final Context context = getBaseContext();
                PropriedadeRural prtmp = advPR.getItem(position);
                DaoTalhao daoTalhao = new DaoTalhao(getBaseContext());
                List<Talhao> listaTL = daoTalhao.getTalhao(prtmp.getId());
                advTL = new ArrayAdapter<Talhao>(context,
                        android.R.layout.simple_list_item_1,
                        listaTL);
                spTL.setAdapter(advTL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spTL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Talhao talhao = advTL.getItem(position);
                textCorte.setText(talhao.getCorte() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void buscaCorte() {
        DaoCorte daoCorte = new DaoCorte(getBaseContext());
        Corte corte = daoCorte.getTipo();

        textCorte.setText(corte.getTipo());
    }

    public void botaoBroca() {
        btBroca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spTL.toString().trim().equals("") || spPR.toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Campo Talhao Vazio", Toast.LENGTH_LONG).show();

                } else {
                    btBroca.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(getBaseContext(), InfoBroca.class);
                            startActivity(intent);
                        }

                    });

                }

            }
        });
    }

    public void botaoConsultar(){
        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btConsultar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getBaseContext(), Consulta.class);
                        startActivity(intent);
                    }
                });
            }

        });

    }


}