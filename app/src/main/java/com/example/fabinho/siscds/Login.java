package com.example.fabinho.siscds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DaoUsuario;

public class Login extends AppCompatActivity {

    private EditText edLogin;
    private EditText edSenha;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        edLogin = (EditText) findViewById(R.id.edLogin);
        edSenha = (EditText) findViewById(R.id.edSenha);
        btLogin = (Button)   findViewById(R.id.btLogin);

        botaoLogin ();
    }

    public class botaoLogin (){
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edLogin.getText().toString().trim().equals("") || edSenha.getText().toString().trim().equals("")){
                    Toast.makeText(getBaseContext(),"Usuário/Senha Inválido",Toast.LENGTH_LONG).show();
                }else {
                    DaoUsuario daoUsuario = new DaoUsuario(getBaseContext());
                    Usuario usuario = daoUsuario.getUsuarioByLoginSenha(edLogin.getText().toString(), edSenha.getText().toString());
                    if (usuario == null){
                        Toast.makeText(getBaseContext(),"Usuário/Senha Inválido",Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent = new Intent(getBaseContext(),AnaliseEntomologica.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
