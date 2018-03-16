package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.adapter.EstudiosAdapter;
import br.com.pos.cacaestudio.fragments.AboutDialog;
import br.com.pos.cacaestudio.modelo.dao.EstudioDAO;
import br.com.pos.cacaestudio.modelo.entity.Estudio;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

public class ListaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Estudio> estudios;
    private Estudio estudioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
  //      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Lista de Estúdios
        EstudioDAO dao = new EstudioDAO(this);
        estudios = dao.listarEstudios();
        dao.close();

        //Adapter da Lista
        final ListView listaEstudiosView = findViewById(R.id.lista_estudios);
        EstudiosAdapter adapter = new EstudiosAdapter(this,estudios);
        listaEstudiosView.setAdapter(adapter);

        //devolve o item da lista que foi clicado
        listaEstudiosView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                estudioSelecionado = estudios.get(position);
                Intent intent = new Intent(ListaActivity.this, EstudioActivity.class);
                intent.putExtra("estudio_selecionado", estudioSelecionado);
                Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
                intent.putExtra("usuario", usuario);
                Log.e("user listaActivity: ", ""+usuario.getNome());
                startActivity(intent);
            }
        });


        //View
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

     }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dados) {
            Intent intent = new Intent(ListaActivity.this,CadastrarActivity.class);
            intent.putExtra("origem","main");
            startActivity(intent);
        } else if (id == R.id.nav_agendados) {
            Intent intent = new Intent(ListaActivity.this, AgendadosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sobre) {
            AboutDialog.showAbout(getSupportFragmentManager());
            return true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}