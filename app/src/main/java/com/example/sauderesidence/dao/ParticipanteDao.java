package com.example.sauderesidence.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.sauderesidence.modelo.Participante;

/**
 * Created by sauderesidence on 23/10/17.
 */

public class ParticipanteDao extends SQLiteOpenHelper {
    private static final String DATABASE = "appEventos";
    private static final int VERSAO = 1;
    private static final String TABELA = "participantes";
    private static final String COL_ID = "id";
    private static final String COL_NOME = "nome";
    private static final String COL_EMAIL = "email";
    private static final String COL_TELFONE = "telefone";


    public ParticipanteDao(Context context){
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABELA + " ("
                + COL_ID + " INTEGER PRIMARY KEY, "
                + COL_NOME + " TEXT UNIQUE NOT NULL, "
                + COL_EMAIL + " TEXT, "
                + COL_TELFONE + " TEXT"
                + ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS PARTICIPANTES";

        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Participante participante){
        ContentValues values = new ContentValues();

        values.put(COL_NOME, participante.getNome());
        values.put(COL_EMAIL, participante.getEmail());
        values.put(COL_TELFONE, participante.getTelefone());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Participante> getLista(){
        List<Participante> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        Participante participante = null;


        while (c.moveToNext()){
            participante = new Participante();

            participante.setId( c.getLong( c.getColumnIndex(COL_ID) ) );
            participante.setNome( c.getString( c.getColumnIndex(COL_NOME) ) );
            participante.setEmail( c.getString( c.getColumnIndex( COL_EMAIL ) ) );
            participante.setTelefone( c.getString( c.getColumnIndex( COL_TELFONE ) ) );

            lista.add(participante);
        }

        return lista;
    }


    public void deletar(Participante participante){
        String[] argumentos =  {participante.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", argumentos);
    }
}
