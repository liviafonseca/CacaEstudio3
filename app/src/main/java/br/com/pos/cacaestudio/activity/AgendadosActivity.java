package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import br.com.pos.cacaestudio.modelo.entity.Estudio;

public class AgendadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendados);

        //Seta voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Estúdios Agendados");     //Titulo para ser exibido na sua Action Bar em frente à seta

        //Lista de Estúdios
        Estudio estudio1 = new Estudio("Estúdio Tupira", "08:00 - 09:00");
        Estudio estudio2 = new Estudio("Estúdio Sonora", "12:00 - 14:00");
        List<Estudio> estudios = new ArrayList<Estudio>();
        estudios.add(estudio1);
        estudios.add(estudio2);

        //Adapter da Lista
        ListView listaEstudiosView = findViewById(R.id.lista_estudios_agendados);
        final AgendadosAdapter adapter = new AgendadosAdapter(this,estudios);
        listaEstudiosView.setAdapter(adapter);



        listaEstudiosView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //View v = adapter.getView(position, view, parent);

                ImageView lixeira = view.findViewById(R.id.agendados_lixeira);
                lixeira.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AgendadosActivity.this, "Esta ação no futuro fará com que" +
                                " o agendamento seja cancelado", Toast.LENGTH_SHORT).show();
                    }
                });

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

    //Back na navigationBar
    @Override
    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, ListaActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }

}
