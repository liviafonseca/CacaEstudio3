package br.com.pos.cacaestudio.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.pos.cacaestudio.modelo.entity.Agenda;
import br.com.pos.cacaestudio.modelo.entity.Comentario;

/**
 * Created by livia on 23/03/2018.
 */

public class AgendaDAO extends SQLiteOpenHelper {

    private static final String BD = "CacaEstudio";
    private static final String TABELA = "agenda";
    private static final String TAG = Comentario.class.getCanonicalName();
    private static final int VERSAO = 1;
    private UsuarioDAO usuarioDAO;
    private EstudioDAO estudioDAO;

    public AgendaDAO(Context context) {
        super(context, BD, null, VERSAO);
     //   usuarioDAO = new UsuarioDAO(context);
     //   estudioDAO = new EstudioDAO(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void novoAgendamento(Agenda agenda){

        ContentValues values = new ContentValues();
        values.put("id_usuario", agenda.getUsuario().getId());
        values.put("id_estudio", agenda.getEstudio().getId());
        values.put("data", String.valueOf(agenda.getData()));
        values.put("hora", agenda.getHora());

        getWritableDatabase().insert(TABELA,null, values);
    }
}
