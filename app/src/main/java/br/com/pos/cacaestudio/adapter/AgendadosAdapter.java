package br.com.pos.cacaestudio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.modelo.entity.Agenda;
import br.com.pos.cacaestudio.modelo.entity.Estudio;

/**
 * Created by livia on 17/02/2018.
 */

public class AgendadosAdapter extends BaseAdapter {

    private final Context context;
    private final List<Agenda> agendas;

    public AgendadosAdapter(Context context, List<Agenda> agendas){
        this.context = context;
        this.agendas = agendas;
    }


    @Override
    public int getCount() {
        return agendas.size();
    }

    @Override
    public Object getItem(int position) {
        return agendas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return agendas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Agenda agenda = agendas.get(position);
        Estudio estudio = agenda.getEstudio();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.layout_lista_agendados, parent, false);
        }


        if(context.getResources().getConfiguration().orientation == 2){
            TextView campoEndereco = view.findViewById(R.id.agendados_endereco);
            campoEndereco.setText(estudio.getEndereco());
        }

        TextView campoNome = view.findViewById(R.id.agendados_nome);
        TextView campoHora = view.findViewById(R.id.agendados_hr);
        TextView campoHrMarcada = view.findViewById(R.id.hr_marcada);


        campoNome.setText(estudio.getNome());
        campoHora.setText("R$" + estudio.getPreco() +" / hora");
        campoHrMarcada.setText("Data: " + agenda.getData()+ " às " + agenda.getHora() + ":00");

        return view;
    }

}
