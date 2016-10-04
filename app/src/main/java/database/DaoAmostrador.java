package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import model.Amostrador;

/**
 * Created by Fabiano on 27/09/2016.
 */

public class DaoAmostrador {

        private Context context;

        public DaoAmostrador(Context context) {
            this.context = context;
        }

        public boolean gravar(Amostrador amostrador) {
            ContentValues content = new ContentValues();

            content.put("nome", amostrador.getNome());
            content.put("area", amostrador.getSetor());


            DataBaseHelper.initializeInstance(this.context);
            SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

            long resultado = db.insert("amostrador", null, content);

            DataBaseHelper.getInstance().closeDatabase();

            if (resultado != -1) {
                return true;
            } else {
                return false;
            }
        }

    public Amostrador getAmostradorByID(long id){
        Amostrador amostrador = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db =  DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery(" SELECT _id, nome, setor FROM amostrador d " +
                            " where d._id = ?", new String[]{id + ""});
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            amostrador = new Amostrador();
            amostrador.setId(cursor.getInt(0));
            amostrador.setNome(cursor.getString(1));
            amostrador.setSetor(cursor.getInt(2));
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return amostrador;
    }
}

