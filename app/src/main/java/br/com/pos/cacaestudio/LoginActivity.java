package br.com.pos.cacaestudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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

    }
}
