package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

/**
 * Created by Fabiano on 15/09/2016.
 */
public class DaoUsuarioLogado {

    private Context context;

    public DaoUsuarioLogado(Context context) { this.context = context; }

    public boolean gravar(Usuario usuario){
        SQLiteDatabase db =  DataBaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuariologado ",null);

        long resultado = 0;

        if (cursor.getCount() > 0){
            //Altera Tabela
            ContentValues content = new ContentValues();
            content.put("_id", usuario.getId());
            resultado = db.update("usuariologado", content, null,null);
        }else{
            //Insere Registro
            ContentValues content = new ContentValues();
            content.put("_id", usuario.getId());
            resultado = db.insert("usuariologado", null, content);
        }

        DataBaseHelper.getInstance().closeDatabase();

        if (resultado != -1){
            return true;
        }else {
            return false;
        }
    }





    public Usuario getUsuarioLogado(){
        Usuario usuario = null;
        DataBaseHelper.initializeInstance(this.context);
        SQLiteDatabase db =  DataBaseHelper.getInstance().openDatabase();

        Cursor cursor =
                db.rawQuery(" SELECT u._id, u.nome, u.login, u.senha" +
                            " FROM usuario u" +
                            " join usuariologado l on u._id = l._id",null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setLogin(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
        }

        cursor.close();
        DataBaseHelper.getInstance().closeDatabase();

        return usuario;
    }
}
