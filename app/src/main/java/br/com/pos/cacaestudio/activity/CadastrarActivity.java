package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.modelo.dao.UsuarioDAO;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

public class CadastrarActivity extends AppCompatActivity {
    UsuarioDAO db;
    private EditText cadNome;
    private EditText cadTelefone;
    private EditText cadEmail;
    private EditText cadSenha;
    private EditText cadConfSenha;
    private Button salvar;
    private Usuario usuario;

    private void validaCampos() {

        boolean res;
        String nome = cadNome.getText().toString();
        String telefone = cadTelefone.getText().toString();
        String email = cadEmail.getText().toString();
        String senha = cadSenha.getText().toString();
        String confSenha = cadConfSenha.getText().toString();

        if (res = isCampoVazio(nome)) {
            cadNome.requestFocus();
        } else if (res = isCampoVazio(telefone)) {
            cadTelefone.requestFocus();
        } else if (res = !isEmailValido(email)) {
            cadEmail.requestFocus();
        } else if (res = isCampoVazio(senha)) {
            cadSenha.requestFocus();
        } else if (res = isCampoVazio(confSenha)) {
            cadConfSenha.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos inválidos ou em branco!");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

    }

    private void validaSenha() {

        String senha = cadSenha.getText().toString();
        String confSenha = cadConfSenha.getText().toString();
        if (isSenhaConfere(senha, confSenha)) {
            cadSenha.requestFocus();
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Senha não confere!");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

    }

    // Valida se campo veio vazio
    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    // Valida formato e-mail
    private boolean isEmailValido(String email) {
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    // Valida se campo SENHA confere com o CONFIRMAR SENHA
    private boolean isSenhaConfere(String senha, String confSenha) {
        boolean nConfere = (senha != confSenha);
        return nConfere;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        cadNome = (EditText) findViewById(R.id.cad_nome);
        cadTelefone = (EditText) findViewById(R.id.cad_telefone);
        cadEmail = (EditText) findViewById(R.id.cad_email);
        cadSenha = (EditText) findViewById(R.id.cad_senha);
        cadConfSenha = (EditText) findViewById(R.id.cad_conf_senha);

        getSupportActionBar().setTitle("Cadastrar Novo Usuário");


        Button btn = findViewById(R.id.cadastrar_btn_salvar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validaCampos();
                validaSenha();
                Intent intent = null;
                String origem = (String) getIntent().getSerializableExtra("origem");
                if (origem.equals("login")) {
                    UsuarioDAO usuarioDao = new UsuarioDAO(CadastrarActivity.this);
                    usuario = new Usuario();
                    usuario.setNome(cadNome.getText().toString());
                    usuario.setTelefone(cadTelefone.getText().toString());
                    usuario.setEmail(cadEmail.getText().toString());
                    usuario.setSenha(cadSenha.getText().toString());
                    usuario.setConfSenha(cadConfSenha.getText().toString());
                    usuarioDao.salvarUsuario(usuario);
                    usuarioDao.close();
                    intent = new Intent(CadastrarActivity.this, LoginActivity.class);
                } else if (origem.equals("main")) {
                    intent = new Intent(CadastrarActivity.this, ListaActivity.class);
                }
                startActivity(intent);
            }
        });
    }
}
