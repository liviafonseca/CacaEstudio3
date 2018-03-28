package br.com.pos.cacaestudio.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pos.cacaestudio.modelo.entity.Agenda;
import br.com.pos.cacaestudio.modelo.entity.Comentario;
import br.com.pos.cacaestudio.modelo.entity.Estudio;
import br.com.pos.cacaestudio.modelo.entity.Usuario;

/**
 * Created by livia on 23/03/2018.
 */

public class AgendaDAO extends SQLiteOpenHelper {

    private static final String BD = "CacaEstudio";
    private static final String TABELA = "agenda";
    private static final String TAG = Comentario.class.getCanonicalName();
    private static final int VERSAO = 1;
 //   private UsuarioDAO usuarioDAO;
    private EstudioDAO estudioDAO;

    public AgendaDAO(Context context) {
        super(context, BD, null, VERSAO);
     //   usuarioDAO = new UsuarioDAO(context);
        estudioDAO = new EstudioDAO(context);
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
        values.put("data", agenda.getData());
        values.put("hora", agenda.getHora());

        getWritableDatabase().insert(TABELA,null, values);
    }

    public List listarAgendamento(Usuario usuario){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        List<Agenda> lista = new ArrayList<>();
        Agenda agenda;
        Estudio estudio;

        String sql = "SELECT * FROM "+TABELA+" Where id_usuario=?";
        String[] args = {String.valueOf(usuario.getId())};

        Cursor cursor = getReadableDatabase().rawQuery(sql, args);
        try{
            while(cursor.moveToNext()){
                agenda = new Agenda();
                estudio =  estudioDAO.getEstudioById(String.valueOf(cursor.getLong(2)));
                agenda.setId((int) cursor.getLong(0));
                agenda.setUsuario(usuario);
                agenda.setEstudio(estudio);
                agenda.setData(cursor.getString(3));
                agenda.setHora(cursor.getInt(4));

                lista.add(agenda);
            }
        } catch (SQLException e){
            Log.e("", e.getMessage());
        }  finally {
            cursor.close();
        }
        return lista;
    }
}









