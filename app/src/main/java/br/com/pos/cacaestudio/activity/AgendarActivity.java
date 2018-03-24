package br.com.pos.cacaestudio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.adapter.AgendadosAdapter;
import br.com.pos.cacaestudio.modelo.dao.AgendaDAO;
import br.com.pos.cacaestudio.modelo.entity.Agenda;
import br.com.pos.cacaestudio.modelo.entity.Estudio;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

public class AgendarActivity extends AppCompatActivity {

    private Agenda agenda;
    private Estudio estudio;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        getSupportActionBar().setTitle("Agendar Estúdio");

        estudio = (Estudio) getIntent().getSerializableExtra("estudio");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        final CalendarView clView = findViewById(R.id.calendario);
        final CheckBox cb8 = findViewById(R.id.id_ck_08h);
        final CheckBox cb9 = findViewById(R.id.id_ck_09h);
        final CheckBox cb10 = findViewById(R.id.id_ck_10h);
        final CheckBox cb11 = findViewById(R.id.id_ck_11h);
        final CheckBox cb12 = findViewById(R.id.id_ck_12h);


        Button btn = findViewById(R.id.agendar_btn_agendar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgendaDAO dao = new AgendaDAO(AgendarActivity.this);

                List<Integer> horario = new ArrayList<Integer>();
                if(cb8.isChecked())
                    horario.add(8);
                if(cb9.isChecked())
                    horario.add(9);
                if(cb10.isChecked())
                    horario.add(10);
                if(cb11.isChecked())
                    horario.add(11);
                if(cb12.isChecked())
                    horario.add(12);

                agenda.setUsuario(usuario);
                agenda.setEstudio(estudio);
                Date dt = new Date(clView.getDate()); // converter Long em Date
                agenda.setData(dt);
                for (int hr: horario) {
                    agenda.setHora(hr);
                    dao.novoAgendamento(agenda);
                }
                dao.close();

                Intent intent = new Intent(AgendarActivity.this, PagamentoActivity.class);
                intent.putExtra("estudio", estudio);
                intent.putExtra("usuario", usuario);
                startActivity(intent);

            }
        });
    }
}
