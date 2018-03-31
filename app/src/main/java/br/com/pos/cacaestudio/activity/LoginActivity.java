package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.modelo.dao.EstudioDAO;
import br.com.pos.cacaestudio.modelo.dao.UsuarioDAO;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

public class LoginActivity extends AppCompatActivity {
    private Button entrar;
    private Button cadastrar;
    private EditText login;
    private EditText senha;
    private Usuario usuario;

    private boolean validaLogin() {

        boolean res;
        String uNome = login.getText().toString();
        String uSenha = senha.getText().toString();
        if (res = isCampoVazio(uNome)) {
            login.requestFocus();
        }else if (res = isCampoVazio(uSenha)) {
            senha.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Campo Login ou Senha vazios!");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
        return res;
     }

    // Valida se campo veio vazio
    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entrar = (Button)findViewById(R.id.id_entrar);
        cadastrar = (Button)findViewById(R.id.id_cadastrar);
        login = (EditText)findViewById(R.id.text_login);
        senha = (EditText)findViewById(R.id.text_senha);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validaLogin()) {
                    UsuarioDAO usuarioDao = new UsuarioDAO(LoginActivity.this);
                    usuario = new Usuario();
                    usuario.setNome(login.getText().toString());
                    usuario.setSenha(senha.getText().toString());
                    if (!usuarioDao.isSenhaValida(usuario)) {
                        AlertDialog.Builder dlg;
                        dlg = new AlertDialog.Builder(LoginActivity.this);
                        dlg.setTitle("Aviso");
                        dlg.setMessage("Campo Login ou Senha inválidos!");
                        dlg.setNeutralButton("OK", null);
                        dlg.show();

                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário: " + (String.valueOf(login.getText())) +
                                        " logado com sucesso"
                                , Toast.LENGTH_LONG).show();
                        Intent it = new Intent(LoginActivity.this, ListaActivity.class);
                        it.putExtra("usuario", usuario);
                        Log.e("user loginActivity: ", "" + usuario.getNome());
                        startActivity(it);
                    }

                    usuarioDao.close();
                }
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

    }}