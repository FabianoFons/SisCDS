package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.PropriedadeRural;


/**
 * Created by Fabiano on 27/09/2016.
 */

public class DaoPropriedadeRural {

        private Context context;

        public DaoPropriedadeRural(Context context) {
            this.context = context;
        }

        public boolean gravar(PropriedadeRural propriedadeRural) {
            ContentValues content = new ContentValues();

            content.put("nome", propriedadeRural.getNome());


            DataBaseHelper.initializeInstance(this.context);
            SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

            long resultado = db.insert("propriedadeRural", null, content);

            DataBaseHelper.getInstance().closeDatabase();

            if (resultado != -1) {
                return true;
            } else {
                return false;
            }
        }

    public List<PropriedadeRural> getPropriedadesRurais(){
        List<PropriedadeRural> lista = new ArrayList<PropriedadeRural>();
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db =  DataBaseHelper.getInstance().openDatabase();
        Cursor cursor =
                db.rawQuery(" SELECT _id, nome, identificacao " +
                            " FROM propriedaderural" +
                            " order by identificacao",null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            PropriedadeRural propriedadeRural = new PropriedadeRural();
            propriedadeRural.setId(cursor.getInt(0));
            propriedadeRural.setNome(cursor.getString(1));
            propriedadeRural.setIdentificacao(cursor.getString(2));
            lista.add(propriedadeRural);
            cursor.moveToNext();
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return lista;
    }
}

