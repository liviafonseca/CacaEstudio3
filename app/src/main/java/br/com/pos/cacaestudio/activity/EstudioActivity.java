package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.modelo.dao.EstudioDAO;
import br.com.pos.cacaestudio.modelo.entity.Estudio;

public class EstudioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudio);

        EstudioDAO dao =  new EstudioDAO(this);
        List<Estudio> lista = dao.listar();
        int c= 0;
        while(c<lista.size()){
            Estudio estudio = lista.get(c);
            Log.d("estudio: ", String.valueOf(estudio));
            c++;
        }

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
                startActivity(intent);
                 break;
        }
        return super.onContextItemSelected(item);
    }
}
