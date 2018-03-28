package br.com.pos.cacaestudio.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.adapter.AgendadosAdapter;
import br.com.pos.cacaestudio.modelo.dao.AgendaDAO;
import br.com.pos.cacaestudio.modelo.entity.Agenda;
import br.com.pos.cacaestudio.modelo.entity.Estudio;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

public class AgendadosActivity extends AppCompatActivity {

    private Agenda estudioAgendado;
    private List<Agenda> agendas;
    private AgendaDAO dao;
    private Usuario usuario;
    private ListView listaEstudiosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendados);

        //Seta voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Estúdios Agendados");     //Titulo para ser exibido na sua Action Bar em frente à seta

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

       carregarLista();

        //para menu de contexto
        registerForContextMenu(listaEstudiosView);
        listaEstudiosView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                estudioAgendado = agendas.get(position);
                return false;
            }
        });

    }

    //menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_estudio_agendado, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_agendamento_cancelar:
                excluir(item.getItemId());
                break;
            case (R.id.menu_agendamento_ligar):
                Toast.makeText(this, "Menu ligar clicado", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void excluir(final int id) {
        new AlertDialog.Builder(this)
                .setTitle("Cancelar Agendamento")
                .setMessage("Tem certeza que deseja cancelar este agendamento?")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao = new AgendaDAO(AgendadosActivity.this);
                        dao.excluirAgendamento(estudioAgendado);
                        dao.close();
                        carregarLista();
                        estudioAgendado = null;
                        Toast.makeText(AgendadosActivity.this, "Agendamento cancelado.",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("não",null)
                .show();
    }

    private void carregarLista() {
        dao = new AgendaDAO(this);
        agendas = dao.listarAgendamento(usuario);
        dao.close();

        //Adapter da Lista
        listaEstudiosView = findViewById(R.id.lista_estudios_agendados);
        final AgendadosAdapter adapter = new AgendadosAdapter(this,agendas);
        listaEstudiosView.setAdapter(adapter);
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

    //Back na navigationBar
    @Override
    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, ListaActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }

}
