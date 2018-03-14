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

public class EstudiosAdapter extends BaseAdapter {

    private final Context context;
    private final List<Estudio> estudios;

    public EstudiosAdapter(Context context, List<Estudio> estudios){
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
            view = inflater.inflate(R.layout.estudio_item, parent, false);
        }

        TextView campoNome = view.findViewById(R.id.lista_estudios_nome);
        TextView campoEndereco = view.findViewById(R.id.lista_estudios_endereco);
        TextView campoPreco = view.findViewById(R.id.lista_estudios_preco);

        campoNome.setText(estudio.getNome());
        campoEndereco.setText(estudio.getEndereco());
        campoPreco.setText((int) estudio.getPreco());

        return view;
    }

}
