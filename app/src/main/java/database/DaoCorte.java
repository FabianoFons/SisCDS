package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import model.Corte;

/**
 * Created by Fabiano on 04/10/2016.
 */

public class DaoCorte {

    private Context context;

    public DaoCorte(Context context) {
        this.context = context;
    }

    public boolean gravar(Corte corte) {
        ContentValues content = new ContentValues();

        content.put("tipo", corte.getTipo());


        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("corte", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        if (resultado != -1) {
            return true;
        } else {
            return false;
        }
    }

    public Corte getTipo(){
        Corte corte = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db =  DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery(" SELECT _id, tipo FROM corte c " +
                        " where c._id = ?", null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            corte = new Corte();
            corte.setId(cursor.getInt(0));
            corte.setTipo(cursor.getString(1));
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return corte;
    }
}