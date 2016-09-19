package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import model.Usuario;

/**
 * Created by Fabiano on 15/09/2016.
 */
public class DaoUsuario {

    private Context context;

    public DaoUsuario(Context context) { this.context = context; }

    public boolean gravar(Usuario usuario){
        ContentValues content = new ContentValues();

        content.put("nome", usuario.getNome());
        content.put("login", usuario.getLogin());
        content.put("senha", usuario.getSenha());

        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db = DataBaseHelper.getInstance().openDatabase();

        long resultado = db.insert("usuario", null, content);

        DataBaseHelper.getInstance().closeDatabase();

        if (resultado != -1){
            return true;
        }else {
            return false;
        }
    }



}
