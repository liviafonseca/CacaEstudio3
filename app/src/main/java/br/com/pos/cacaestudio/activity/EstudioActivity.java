package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.adapter.ComentariosAdapater;
import br.com.pos.cacaestudio.helper.EstudioHelper;
import br.com.pos.cacaestudio.modelo.dao.ComentarioDAO;
import br.com.pos.cacaestudio.modelo.dao.EstudioDAO;
import br.com.pos.cacaestudio.modelo.entity.Comentario;
import br.com.pos.cacaestudio.modelo.entity.Estudio;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

public class EstudioActivity extends AppCompatActivity {

    private Estudio estudio;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudio);

        //Seta voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        estudio = (Estudio) getIntent().getSerializableExtra("estudio_selecionado");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        Log.e("user estudioActivity: ", ""+usuario.getNome());

        TextView campoNome = findViewById(R.id.estudio_nome);
        TextView campoEndereco = findViewById(R.id.estudio_endereco);
        TextView campoTelefone = findViewById(R.id.estudio_telefone);
        TextView campoPreco = findViewById(R.id.estudio_preco);
        TextView campoNota = findViewById(R.id.estudio_nota);

        campoNome.setText(estudio.getNome());
        campoEndereco.setText(estudio.getEndereco());
        campoTelefone.setText(estudio.getTelefone());
        campoPreco.setText("R$"+(int)estudio.getPreco() + " / hora");
        campoNota.setText(""+estudio.getAvaliacao());

        //Pegar lista de comentários
        ComentarioDAO comentarioDAO = new ComentarioDAO(this);
        List<Comentario> comentarios = comentarioDAO.listarCometarios(usuario, estudio);

        //Adapter da lista de comentários
        ListView lvComentarios = findViewById(R.id.lv_lista_comentarios);
        ComentariosAdapater adapater = new ComentariosAdapater(this, comentarios);
        lvComentarios.setAdapter(adapater);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_estudio, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.menu_mapa:

                break;
            case R.id.menu_agendar:
                intent = new Intent(this,AgendarActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_avaliar:
                intent = new Intent(this, AvaliarActivity.class);
                intent.putExtra("estudio", estudio);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                 break;
        }
        return super.onContextItemSelected(item);
    }

}
