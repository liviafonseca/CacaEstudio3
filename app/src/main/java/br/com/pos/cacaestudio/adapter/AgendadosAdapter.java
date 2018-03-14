package br.com.pos.cacaestudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.modelo.entity.Estudio;

/**
 * Created by livia on 17/02/2018.
 */

public class AgendadosAdapter extends BaseAdapter {

    private final Context context;
    private final List<Estudio> estudios;

    public AgendadosAdapter(Context context, List<Estudio> estudios){
        this.context = context;
        this.estudios = estudios;
    }


    @Override
    public int getCount() {
        return estudios.size();
    }

    @Override
    public Object getItem(int position) {
        return estudios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return estudios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Estudio estudio = estudios.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.layout_lista_agendados, parent, false);
        }

        TextView campoNome = view.findViewById(R.id.agendados_nome);
        TextView campoHora = view.findViewById(R.id.agendados_hr);

        campoNome.setText(estudio.getNome());
        campoHora.setText(" / hora");

        return view;
    }

}
