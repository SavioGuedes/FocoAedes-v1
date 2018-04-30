package com.example.savio.focoaedes.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.savio.focoaedes.model.Ocorrencia;

import java.util.ArrayList;
import java.util.List;

public class BaseLocal extends SQLiteOpenHelper {

    public static final int BASE_VERSAO = 2;
    public static final String BASE_NOME = "ocorrencias";

    public static final String TB_OCORRENCIA = "tb_ocorrencias";

    public static final String COL_ID = "id";
    public static final String COL_FOTO = "foto";
    public static final String COL_TITULO = "titulo";
    public static final String COL_DATA = "data";
    public static final String COL_BAIRRO = "bairro";
    public static final String COL_RUA = "rua";
    public static final String COL_TEL = "telefone";
    public static final String COL_MAIL = "email";
    public static final String COL_DESC = "descricao";

    public BaseLocal(Context context) {
        super(context, BASE_NOME, null, BASE_VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_CREATE = "CREATE TABLE "+ TB_OCORRENCIA +"(" +
            COL_ID + " INTEGER PRIMARY KEY, " +
            COL_FOTO + " TEXT, " +
            COL_TITULO + " TEXT, " +
            COL_DATA + " TEXT, " +
            COL_BAIRRO + " TEXT, " +
            COL_RUA + " TEXT, " +
            COL_TEL + " TEXT, " +
            COL_MAIL + " TEXT, " +
            COL_DESC + " TEXT) ";

        db.execSQL(QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


//--------------CRUD--------------------------------------------------------------------------------//


    //CREATE
    public void addOcorrencia(Ocorrencia ocorrencia){

        //POST na base
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_FOTO, ocorrencia.getFoto());
        contentValues.put(COL_TITULO, ocorrencia.getTitulo());
        contentValues.put(COL_DATA, ocorrencia.getData());
        contentValues.put(COL_BAIRRO, ocorrencia.getBairro());
        contentValues.put(COL_RUA, ocorrencia.getRua());
        contentValues.put(COL_TEL, ocorrencia.getTelefone());
        contentValues.put(COL_MAIL, ocorrencia.getEmail());
        contentValues.put(COL_DESC, ocorrencia.getDescricao());

        db.insert(TB_OCORRENCIA, null, contentValues);
        db.close();
    }

    //SHOW
    public Ocorrencia verOcorrencia(int id){

        //GET na base
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TB_OCORRENCIA, new String[]{COL_ID, COL_TITULO},
        COL_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        Ocorrencia ocorrencia1 = new Ocorrencia(cursor.getString(0),
        cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
        cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));

        return ocorrencia1;
    }

    //READ
    public List<Ocorrencia> listarOcorrencias(){

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        String query = "SELECT * FROM " + TB_OCORRENCIA;

        //GET na base
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //verificar se possui informações
        if(cursor.moveToFirst()){
            do{

                Ocorrencia ocorrencia = new Ocorrencia();

                ocorrencia.setId(cursor.getString(0));
                ocorrencia.setFoto(cursor.getString(1));
                ocorrencia.setTitulo(cursor.getString(2));
                ocorrencia.setData(cursor.getString(3));
                ocorrencia.setBairro(cursor.getString(4));
                ocorrencia.setRua(cursor.getString(5));
                ocorrencia.setTelefone(cursor.getString(6));
                ocorrencia.setEmail(cursor.getString(7));
                ocorrencia.setDescricao(cursor.getString(8));

                //adiciona no arraylist
                ocorrencias.add(ocorrencia);

            }while (cursor.moveToNext());
        }

        db.close();

        return ocorrencias;
    }

    //DELETE
    public void apagarOcorrencia(Ocorrencia ocorrencia){

        //POST na base
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TB_OCORRENCIA, COL_ID + " = ?",
        new String[] {String.valueOf(ocorrencia.getId())});

        db.close();
    }


//--------------Fim de codigo-----------------------------------------------------------------------//

}
