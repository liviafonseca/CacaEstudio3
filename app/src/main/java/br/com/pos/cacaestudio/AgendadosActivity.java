package br.com.pos.cacaestudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pos.cacaestudio.adapter.AgendadosAdapter;
import br.com.pos.cacaestudio.adapter.EstudiosAdapter;
import br.com.pos.cacaestudio.modelo.Estudio;

public class AgendadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendados);

        //Lista de Estúdios
        Estudio estudio1 = new Estudio("Estúdio Tupira", "08:00 - 09:00");
        Estudio estudio2 = new Estudio("Estúdio Sonora", "12:00 - 14:00");
        List<Estudio> estudios = new ArrayList<Estudio>();
        estudios.add(estudio1);
        estudios.add(estudio2);

        //Adapter da Lista
        ListView listaEstudios = findViewById(R.id.lista_estudios_agendados);
        AgendadosAdapter adapter = new AgendadosAdapter(this,estudios);
        listaEstudios.setAdapter(adapter);


    }

}
