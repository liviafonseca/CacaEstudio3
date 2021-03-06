package br.com.pos.cacaestudio.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

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

        Log.e("USUARIODAO", "entrou no metodo onCreate do UsuarioDAO");

        String sql="CREATE TABLE "+"usuario"+" (id INTEGER PRIMARY KEY, nome TEXT, senha TEXT, " +
                "telefone TEXT, email TEXT);";
        db.execSQL(sql);

        sql = "CREATE TABLE " + "estudio" + " (id INTEGER PRIMARY KEY, nome TEXT, " +
                "endereco TEXT, telefone TEXT, preco DOUBLE, img_url TEXT, media DOUBLE);";
        db.execSQL(sql);

        sql="CREATE TABLE "+"comentario"+" (id INTEGER PRIMARY KEY, " +
                " id_usuario INTEGER REFERENCES usuario(id)," +
                " id_estudio INTEGER REFERENCES estudio(id)," +
                " comentario TEXT," +
                " nota DOUBLE ); ";

        db.execSQL(sql);

        sql = "CREATE TABLE agenda (id INTEGER PRIMARY KEY, " +
                " id_usuario INTEGER REFERENCES usuario(id)," +
                " id_estudio INTEGER REFERENCES estudio(id)," +
                " data TEXT," +
                " hora INTEGER ); ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    public void salvarUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("senha", usuario.getSenha());
        values.put("telefone", usuario.getTelefone());
        values.put("email", usuario.getEmail());
        getWritableDatabase().insert(TABELA, null, values);
    }

    //Query para validar se usuário e senha estão corretos.

      public Boolean isSenhaValida (Usuario usuario){
        Boolean isSenhaValida = false;
        String senha = usuario.getSenha();
        String sql="SELECT * FROM "+TABELA+" WHERE nome=?";
        String args[] = {String.valueOf(usuario.getNome())};
        Cursor c = getReadableDatabase().rawQuery(sql,args);
          try{
            if(c.moveToFirst()){
                usuario.setId((int) c.getLong(0));
                usuario.setNome(c.getString(1));
                usuario.setSenha(c.getString(2));
                usuario.setTelefone(c.getString(3));
                usuario.setEmail(c.getString(4));
                if (c.getCount()>0){
                    if(senha.equals(c.getString(2))){
                        isSenhaValida=true;
                    }
                }
            }
        }catch(SQLException e){
            Log.e(TAG, e.getMessage());
        }finally {
            c.close();
        }
        return isSenhaValida;
    }


    public void atualizarUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("senha", usuario.getSenha());
        values.put("telefone", usuario.getTelefone());
        values.put("email", usuario.getEmail());

        String[] args={String.valueOf(usuario.getNome())};

        getWritableDatabase().update(TABELA,values, "nome=?", args );
    }


    public Usuario getUsuarioById(Usuario usuario){

    /* ------------- AJEITAR ESSE CÓDIGO AQUI DEPOIS ---------- */

   /*     String sql="SELECT * FROM "+TABELA+" WHERE id=?";
        String args[] = {String.valueOf(usuario.getId())};
        c = getReadableDatabase().rawQuery(sql,args);
   */
/*
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
       }*/
    return  usuario;
 }

}