package br.com.pos.cacaestudio.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.com.pos.cacaestudio.modelo.entity.Usuario;

/**
 * Created by livia on 14/03/2018.
 */

public class UsuarioDAO extends SQLiteOpenHelper {

    private static final String BD = "CacaEstudio";
    private static final String TABELA = "usuario";
    private static final String TAG = Usuario.class.getCanonicalName();
    private static final int VERSAO = 1;

    public UsuarioDAO(Context context) {
        super(context, BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("entrou no metodo", "entrou no metodo onCreate do UsuarioDAO");
        String sql="CREATE TABLE "+TABELA+" (id INTEGER PRIMARY KEY, nome TEXT, senha TEXT, " +
                "telefone TEXT, email TEXT);";
        db.execSQL(sql);

        Log.e("sql usuario: ", sql);

         sql = "CREATE TABLE " + "estudio" + " (id INTEGER PRIMARY KEY, nome TEXT, " +
                "endereco TEXT, telefone TEXT, preco DOUBLE, img_url TEXT, avaliacao DOUBLE );";
        db.execSQL(sql);

        Log.e("sql estudio", sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    public void criarUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("senha", usuario.getSenha());
        values.put("telefone", usuario.getTelefone());
        values.put("email", usuario.getEmail());
        getWritableDatabase().insert(TABELA, null, values);
    }

    public Usuario getUsuarioById(String id){
        Usuario usuario = new Usuario();
        String sql="SELECT * FROM usuario WHERE id=?";
        String args[] = {id};
       Cursor c = getReadableDatabase().rawQuery(sql,args);
       try{
           if(c.moveToFirst()){
               usuario.setId((int) c.getLong(0));
               usuario.setNome(c.getString(1));
               usuario.setSenha(c.getString(2));
               usuario.setTelefone(c.getString(3));
               usuario.setEmail(c.getString(4));
           }
       }catch(SQLException e){
            Log.e(TAG, e.getMessage());
       }finally {
           c.close();
       }

       return  usuario;
    }


}
