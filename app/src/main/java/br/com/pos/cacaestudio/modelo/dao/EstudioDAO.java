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

import br.com.pos.cacaestudio.helper.EstudioHelper;
import br.com.pos.cacaestudio.modelo.entity.Estudio;

/**
 * Created by livia on 14/03/2018.
 */

public class EstudioDAO extends SQLiteOpenHelper{

    private static final String BD = "CacaEstudio";
    private static final String TABELA = "estudio";
    private static final String TAG = Estudio.class.getCanonicalName();
    private static final int VERSAO = 1;

    public EstudioDAO(Context context) {
        super(context, BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("entrou no metodo","entrou no metodo onCreate do EstudioDAO");
        String sql = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, nome TEXT, " +
                "endereco TEXT, telefone TEXT, preco DOUBLE, img_url TEXT, avaliacao DOUBLE );";
       // db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    private void popularTabela(){

        EstudioHelper helper = new EstudioHelper();
        List<Estudio> listaDeEstudios = helper.gerarListaDeEstudios();
        for(Estudio e : listaDeEstudios) {
            salvarEstudio(e);
        }
    }

    private void salvarEstudio(Estudio estudio) {
        ContentValues values = new ContentValues();

        values.put("nome",estudio.getNome());
        values.put("endereco",estudio.getEndereco());
        values.put("telefone",estudio.getTelefone());
        values.put("preco",estudio.getPreco());
        values.put("img_url",estudio.getImg());
        values.put("avaliacao",estudio.getAvaliacao());
        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Estudio> listarEstudios(){

        List<Estudio> lista = new ArrayList<>();


        String sql="Select * from " + TABELA + " order by nome";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount()>0) {
            try {
                while (cursor.moveToNext()) {
                    Estudio estudio = new Estudio();
                    estudio.setId((int) cursor.getLong(0));
                    estudio.setNome(cursor.getString(1));
                    estudio.setEndereco(cursor.getString(2));
                    estudio.setTelefone(cursor.getString(3));
                    estudio.setPreco(cursor.getDouble(4));
                    estudio.setImg(cursor.getString(5));
                    estudio.setAvaliacao(cursor.getDouble(6));

                    lista.add(estudio);
                }
            } catch (SQLException e) {
                Log.e(TAG, e.getMessage());
            } finally {
                cursor.close();
            }
        }else {
            popularTabela(); //popular os estudios, se tabela estiver vazia.
        }
        return lista;
    }


    public Estudio getEstudioById(String id) {
        Estudio estudio = new Estudio();
            String sql="SELECT * FROM estudio WHERE id=?";
            String args[] = {id};
            Cursor cursor = getReadableDatabase().rawQuery(sql,args);
            try{
                if(cursor.moveToFirst()){
                    estudio.setId((int) cursor.getLong(0));
                    estudio.setNome(cursor.getString(1));
                    estudio.setEndereco(cursor.getString(2));
                    estudio.setTelefone(cursor.getString(3));
                    estudio.setPreco(cursor.getDouble(4));
                    estudio.setImg(cursor.getString(5));
                    estudio.setAvaliacao(cursor.getDouble(6));
                }
            }catch(SQLException e){
                Log.e(TAG, e.getMessage());
            }finally {
                cursor.close();
            }

            return  estudio;
        }

}













