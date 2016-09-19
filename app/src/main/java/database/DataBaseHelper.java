package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Fabiano on 15/09/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "combatbroca";
    private static int VERSAO = 2;
    public DataBaseHelper (Context context) {
        super(context,BANCO_DADOS,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Create","Create");
        db.execSQL("Create table usuario (_id INTEGER PRIMARY KEY." +
                "nome VARCHAR(50), login VARCHAR(50), senha VARCHAR(50)");

        db.execSQL("insert into usuario (nome, login, senha) values ('Admin','admin','123');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (newVersion > oldVersion){

        }
    }

    private AtomicInteger mOpenCounter = new AtomicInteger();
    private static DataBaseHelper instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
            mDatabaseHelper = instance;
        }
    }

    public static synchronized DataBaseHelper getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DataBaseHelper.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return instance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if(mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if(mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();

        }
    }
}
