package br.com.pos.cacaestudio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.pos.cacaestudio.R;
import br.com.pos.cacaestudio.modelo.entity.Comentario;
import br.com.pos.cacaestudio.modelo.entity.Estudio;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

/**
 * Created by livia on 16/03/2018.
 */

public class ComentariosAdapater extends BaseAdapter {

    private Context context;
    private Comentario comentario;
    private Usuario usuario;
    private Estudio estudio;
    private List<Comentario> comentarioList;

    public ComentariosAdapater(Context context, List<Comentario> comentarioList) {
        this.context = context;
        this.comentarioList = comentarioList;
    }

    @Override
    public int getCount() {
        return comentarioList.size();
    }

    @Override
    public Object getItem(int position) {
        return comentarioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return comentarioList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Comentario comentario = comentarioList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view==null){
            view = inflater.inflate(R.layout.layout_lista_comentarios, parent, false);
        }

        TextView campoNomeUsuario = view.findViewById(R.id.comentario_nome);
        TextView campoTexto = view.findViewById(R.id.comentario_texto);

        campoNomeUsuario.setText(comentario.getUsuario().getNome());
        campoTexto.setText(comentario.getComentario());

        return view;
    }
}












