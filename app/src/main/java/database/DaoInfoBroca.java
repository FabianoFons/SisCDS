package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.fabinho.siscds.InfoBroca;

import model.Broca;

/**
 * Created by Fabiano on 08/10/2016.
 */

public class DaoInfoBroca {
    private Context context;
    public boolean gravar(Broca broca) {
        ContentValues content = new ContentValues();

        content.put("brocaGrande", broca.getBrocaGrande());
        content.put("brocaPequena", broca.getBrocaPequena());
        content.put("crisalida", broca.getCrisalida());
        content.put("pupa", broca.getPupas());
        content.put("totalNo", broca.getTotalNos());
        content.put("brocados", broca.getBrocados());
        content.put("podridaoVermelha", broca.getPodridaoVermelha());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("broca", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        if (resultado != -1) {
            return true;
        } else {
            return false;
        }
    }
}
