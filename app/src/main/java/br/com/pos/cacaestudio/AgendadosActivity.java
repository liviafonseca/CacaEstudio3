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

public class AgendadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendados);

        ListView listaEstudios = findViewById(R.id.lista_estudios_agendados);

        //Populando lista de forma estática
        String[] estudios = {"Estúdio Tupira", "Estúdio Música Certa", "Estúdio Curupira"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, estudios);
        listaEstudios.setAdapter(adapter);



    }

}
