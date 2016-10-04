package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Talhao;


/**
 * Created by Fabiano on 27/09/2016.
 */

public class DaoTalhao {
    private Context context;

    public DaoTalhao(Context context) {
        this.context = context;
    }

    public boolean gravar(Talhao talhao) {
        ContentValues content = new ContentValues();

        content.put("identificacao", talhao.getIdentificacao());
        content.put("area", talhao.getArea());
        content.put("data", String.valueOf(talhao.getData()));

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("talhao", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        if (resultado != -1) {
            return true;
        } else {
            return false;
        }
    }

    public List<Talhao> getTalhao(){
        List<Talhao> lista = new ArrayList<Talhao>();
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db =  DataBaseHelper.getInstance().openDatabase();
        Cursor cursor =
                db.rawQuery(" SELECT _id, identificacao, area " +
                        " FROM talhao" +
                        " order by identificacao",null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Talhao talhao = new Talhao();
            talhao.setId(cursor.getInt(0));
            talhao.setIdentificacao(cursor.getString(1));
            talhao.setArea(cursor.getString(2));
            lista.add(talhao);
            cursor.moveToNext();
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return lista;
    }
}