package br.com.pos.cacaestudio.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.pos.cacaestudio.modelo.entity.Comentario;
import br.com.pos.cacaestudio.modelo.entity.Estudio;
import br.com.pos.cacaestudio.modelo.entity.Usuario;


/**
 * Created by livia on 14/03/2018.
 */

public class ComentarioDAO extends SQLiteOpenHelper {

    private static final String BD = "CacaEstudio";
    private static final String TABELA = "comentario";
    private static final String TAG = Comentario.class.getCanonicalName();
    private static final int VERSAO = 1;
    private UsuarioDAO usuarioDAO;
    private EstudioDAO estudioDAO;

    public ComentarioDAO(Context context) {
        super(context, BD, null, VERSAO);
        usuarioDAO = new UsuarioDAO(context);
        estudioDAO = new EstudioDAO(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE "+TABELA+" (id INTEGER PRIMARY KEY, " +
                "FOREIGN KEY (id_usuario, id_estudio) REFERENCES (usuario, estudio), " +
                "comentario TEXT ) ";
      //  db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    public void criarComentario(Comentario comentario){

        ContentValues values = new ContentValues();

        values.put("id_estudio", comentario.getEstudio().getId());
        values.put("comentario", comentario.getComentario());
        values.put("id_usuario", comentario.getUsuario().getId());
        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Comentario> listarCometarios(Usuario usuario, Estudio estudio){
        List<Comentario> lista = new ArrayList<>();

        String sql="SELECT * FROM "+TABELA+" WHERE id_usuario = ? AND id_estudio = ?;";
        String args[] = {String.valueOf(usuario.getId()), String.valueOf(estudio.getId())};
        Cursor cursor = getReadableDatabase().rawQuery(sql, args);
        try{
            while(cursor.moveToNext()){
                Comentario comentario = new Comentario();
                usuario = usuarioDAO.getUsuarioById(usuario);  //AJEITAR ISSO AQUI DEPOIS. <--------
                estudio = estudioDAO.getEstudioById(String.valueOf(cursor.getLong(2)));
                comentario.setId((int) cursor.getLong(0));
                comentario.setUsuario(usuario);
                comentario.setEstudio(estudio);
                comentario.setComentario(cursor.getString(3));

                lista.add(comentario);
            }
        }catch (SQLException e){
            Log.e(TAG, e.getMessage());
        }finally {
            cursor.close();
        }

        return lista;
    }
}
