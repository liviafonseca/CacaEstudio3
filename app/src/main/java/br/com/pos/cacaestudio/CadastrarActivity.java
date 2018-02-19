package br.com.pos.cacaestudio;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        getSupportActionBar().setTitle("Cadastrar Novo Usuário");
        
        Button btn = findViewById(R.id.cadastrar_btn_salvar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                String origem = (String) getIntent().getSerializableExtra("origem");
                if(origem.equals("login")){
                    intent = new Intent(CadastrarActivity.this, LoginActivity.class);
                }else if(origem.equals("main")){
                    intent = new Intent(CadastrarActivity.this, ListaActivity.class);
                }
                startActivity(intent);
            }
        });




    }
}
