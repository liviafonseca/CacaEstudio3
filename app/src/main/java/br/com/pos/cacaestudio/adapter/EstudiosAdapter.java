package br.com.pos.cacaestudio.adapter;

import android.app.Activity;
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

public class EstudiosAdapter extends BaseAdapter {

    private final List<Estudio> estudios;
    private final Activity activity;
    private Estudio estudio;

    public EstudiosAdapter(Activity activity, List<Estudio> estudios){
        this.activity = activity;
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

        estudio = estudios.get(position);

        LayoutInflater inflater = LayoutInflater.from(activity);

        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.estudio_item, parent, false);
        }


        TextView campoNome = view.findViewById(R.id.lista_estudios_nome);
        TextView campoEndereco = view.findViewById(R.id.lista_estudios_endereco);
        TextView campoPreco = view.findViewById(R.id.lista_estudios_preco);


        campoNome.setText(estudio.getNome());
        campoEndereco.setText(estudio.getEndereco());
        campoPreco.setText(""+estudio.getPreco());

        return view;
    }



}
