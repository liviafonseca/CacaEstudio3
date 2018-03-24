package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.modelo.dao.ComentarioDAO;
import br.com.pos.cacaestudio.modelo.dao.EstudioDAO;
import br.com.pos.cacaestudio.modelo.entity.Comentario;
import br.com.pos.cacaestudio.modelo.entity.Estudio;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

public class AvaliarActivity extends AppCompatActivity {

    private Estudio estudio;
    private Usuario usuario;
    private RatingBar rate;
    private Comentario comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar);

        getSupportActionBar().setTitle("Avaliar Estúdio");
        //Seta voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        estudio = (Estudio) getIntent().getSerializableExtra("estudio");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        Log.e("user avaliarActivity: ", ""+usuario.getNome());

        TextView campoNome = findViewById(R.id.avaliar_nome);
        TextView campoEndereco = findViewById(R.id.avaliar_endereco);
        rate = findViewById(R.id.rate);
        final TextView campoComentario = findViewById(R.id.avaliacao_comentario);

        campoNome.setText(estudio.getNome());
        campoEndereco.setText(estudio.getEndereco());


        Button btn = findViewById(R.id.avaliar_btn_enviar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AvaliarActivity.this, "Nota: " + rate.getRating() +
                        "texto: " + campoComentario.getText(),
                        Toast.LENGTH_LONG).show();

                ComentarioDAO comentarioDao = new ComentarioDAO(AvaliarActivity.this);
                EstudioDAO estudioDAO = new EstudioDAO(AvaliarActivity.this);
                comentario = new Comentario();
                comentario.setComentario(campoComentario.getText().toString());
                comentario.setNota(rate.getRating());
                comentario.setEstudio(estudio);
                comentario.setUsuario(usuario);
                comentarioDao.criarComentario(comentario);
                comentarioDao.close();
                estudioDAO.atualizarMedia(estudio, comentario, comentarioDao);
                estudioDAO.close();

                Intent intent = new Intent(AvaliarActivity.this, EstudioActivity.class);
                intent.putExtra("estudio_selecionado", estudio);
                intent.putExtra("usuario", usuario);
                startActivity(intent);

            }
        });
    }

    //Para seta voltar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, ListaActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }
}
