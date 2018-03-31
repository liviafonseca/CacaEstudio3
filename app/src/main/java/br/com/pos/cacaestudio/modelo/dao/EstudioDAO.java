package br.com.pos.cacaestudio.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.pos.cacaestudio.helper.EstudioHelper;
import br.com.pos.cacaestudio.modelo.entity.Comentario;
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
        String sql1 = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, nome TEXT, " +
                "endereco TEXT, telefone TEXT, preco DOUBLE, img_url TEXT, avaliacao DOUBLE );";
        //db.execSQL(sql);

        //----------------------------------------------------------------------------


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
        values.put("media",estudio.getMedia());
        getWritableDatabase().insert(TABELA, null, values);
    }

    //Este método faz o que deveria ser feito em um servidor Web.
    //Este cria uma lista de Estúdios, de maneira estática.
    public List<Estudio> listarEstudios(){

        List<Estudio> listaDeEstudios = new ArrayList<>();

        //Verificar se tem dados ta tabela
        Cursor cursor = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM "+TABELA, null);

        if(cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0) { //tabela está vazia
                popularTabela();
                listaDeEstudios = preencherLista();
            }
            else { //Tabela não está vazia, apenas preenche a lista.
                listaDeEstudios = preencherLista();
            }
        }
        cursor.close();
        return listaDeEstudios;
    }


    private List<Estudio> preencherLista() {
        List<Estudio> lista = new ArrayList<>();
        String sql="Select * from " + TABELA + " order by nome";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        try {
            while (cursor.moveToNext()) {
                Estudio estudio = new Estudio();
                estudio.setId((int) cursor.getLong(0));
                estudio.setNome(cursor.getString(1));
                estudio.setEndereco(cursor.getString(2));
                estudio.setTelefone(cursor.getString(3));
                estudio.setPreco(cursor.getDouble(4));
                estudio.setImg(cursor.getString(5));
                estudio.setMedia(cursor.getDouble(6));

                lista.add(estudio);
            }
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
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
                    estudio.setMedia(cursor.getDouble(6));
                }
            }catch(SQLException e){
                Log.e(TAG, e.getMessage());
            }finally {
                cursor.close();
            }

            return  estudio;
        }

    public void atualizarMedia(Estudio estudio, Comentario novoComentario, ComentarioDAO comentarioDAO) {

        List<Double> listaNotas =  comentarioDAO.getNotasPorEstudio(estudio);
        double somatoria = 0;

        if(listaNotas.size()>0) {
            for (double nota : listaNotas) {
                somatoria += nota;
            }
            DecimalFormat decimal = new DecimalFormat("0.0");
            double media = Double.parseDouble(decimal.format(somatoria / listaNotas.size()));
            estudio.setMedia(media);
            atualizarEstudio(estudio);
        }
    }

    private void atualizarEstudio(Estudio estudio) {
        ContentValues values = new ContentValues();

        values.put("nome",estudio.getNome());
        values.put("endereco",estudio.getEndereco());
        values.put("telefone",estudio.getTelefone());
        values.put("preco",estudio.getPreco());
        values.put("img_url",estudio.getImg());
        values.put("media",estudio.getMedia());

        String[] args={String.valueOf(estudio.getId())};

        getWritableDatabase().update(TABELA,values, "id=?", args );
    }
}













