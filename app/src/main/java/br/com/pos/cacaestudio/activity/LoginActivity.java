package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.pos.cacaestudio.R;

public class LoginActivity extends AppCompatActivity {
    private Button entrar;
    private Button cadastrar;
    private ImageView login_google;
    private EditText login;
    private EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entrar = (Button)findViewById(R.id.id_entrar);
        cadastrar = (Button)findViewById(R.id.id_cadastrar);
        login_google = (ImageView)findViewById(R.id.id_log_google);
        login = (EditText)findViewById(R.id.text_login);
        senha = (EditText)findViewById(R.id.text_senha);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Usuário: " + (String.valueOf(login.getText()))+
                        " logado com sucesso"
                        ,Toast.LENGTH_LONG).show();
                Intent it = new Intent(LoginActivity.this, ListaActivity.class);
                startActivity (it);
            }

        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, CadastrarActivity.class);
                it.putExtra("origem","login");
                startActivity (it);
            }

        });
        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Usuário Google logado com sucesso"
                        ,Toast.LENGTH_LONG).show();
                Intent it = new Intent(LoginActivity.this, ListaActivity.class);
                startActivity (it);
            }
        });

    }}