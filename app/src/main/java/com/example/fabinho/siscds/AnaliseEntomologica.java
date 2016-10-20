package com.example.fabinho.siscds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import database.DaoAmostrador;
import database.DaoCorte;
import database.DaoInfoBroca;
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
    private TextView edData;

    Integer tempConsulta;

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
        edData = (TextView) findViewById(R.id.edData);

        buscaAmostrador();
        buscaPropriedadesRurais();
        buscaTalhao();
        botaoBroca();
        botaoConsultar();
        data();
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

                if (spPR.getSelectedItem() == null) {
                    Toast.makeText(getBaseContext(), "Selecione uma Propriedade", Toast.LENGTH_LONG).show();
                } else {
                    if (spTL.getSelectedItem() == null) {
                        Toast.makeText(getBaseContext(), "Selecione um Talhão", Toast.LENGTH_LONG).show();
                    } else {
                        if ((edData == null)) {
                            Toast.makeText(getBaseContext(), "Informe a Data", Toast.LENGTH_LONG).show();
                        } else {
                                    PropriedadeRural propriedadeRural = (PropriedadeRural) spPR.getSelectedItem();
                                    Talhao talhao = (Talhao) spTL.getSelectedItem();
                                    Intent intent = new Intent(getBaseContext(), InfoBroca.class);
                                    intent.putExtra("codPropriedade",propriedadeRural.getId());
                                    intent.putExtra("nomePropriedade",propriedadeRural.getNome());
                                    intent.putExtra("codTalhao",talhao.getId());
                                    intent.putExtra("talhao",talhao.getIdentificacao());
                                    intent.putExtra("corte",talhao.getCorte());
                                    startActivityForResult(intent,1);
                        }
                    }
                }
            }
        });
    }

    public void botaoConsultar(){
        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), Consulta.class);
                startActivity(intent);
            }
        });
    }




    private void data(){
        //Pega data atual
        Date data = new Date();

        //Formata a data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        //Insere data no textview
        edData.setText(sdf.format(data));
    }

    //Recebe as informações vindas de InfoBroca
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                String  brocaGrande = data.getStringExtra("brocaGrande");
                String  brocaPequena = data.getStringExtra("brocaPequena");
                String crisalida = data.getStringExtra("crisalida");
                String pupas  =data.getStringExtra("pupas");
                String totalNos = data.getStringExtra("totalNos");
                String brocados =data.getStringExtra("brocados");
                String podridaoVermelha =data.getStringExtra("podridaoVermelha");

                DaoInfoBroca daoInfoBroca = new DaoInfoBroca();
                Broca broca = new Broca();
                broca.setBrocaGrande(Integer.parseInt(brocaGrande));
                broca.setBrocaPequena(Integer.parseInt(brocaPequena));
                broca.setCrisalida(Integer.parseInt(crisalida));
                broca.setPupas(Integer.parseInt(pupas));
                broca.setTotalNos(Integer.parseInt(totalNos));
                broca.setBrocados(Integer.parseInt(brocados));
                broca.setPodridaoVermelha(Integer.parseInt(podridaoVermelha));

                daoInfoBroca.gravar(broca);
            }
        }
    }
}