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
        db.execSQL("Create table usuario (" +
                " _id INTEGER PRIMARY_KEY," +
                "nome VARCHAR(50), login VARCHAR(50), senha VARCHAR(50));");

        db.execSQL("Create table usuariologado " +
                " (" +
                " _id INTEGER PRIMARY_KEY" +
                " );");

        db.execSQL("Create table amostrador (" +
                " _id INTEGER PRIMARY_KEY," +
                " nome VARCHAR(50), " +
                " setor int);");

        db.execSQL("Create table propriedaderural (" +
                " _id INTEGER PRIMARY_KEY," +
                " nome VARCHAR(50), " +
                " identificacao VARCHAR(20));");

        db.execSQL("Create table talhao (" +
                " _id INTEGER PRIMARY_KEY," +
                " identificacao VARCHAR(20), " +
                " area VARCHAR(20));");

        db.execSQL("Create table corte (" +
                " _id INTEGER PRIMARY_KEY," +
                " tipo VARCHAR(20));");

        db.execSQL("insert into usuario (_id, nome, login, senha) values (1,'RONALDO','ronaldo','123');");
        db.execSQL("insert into usuario (_id, nome, login, senha) values (2,'FABIANO','fabiano','123');");
        db.execSQL("insert into usuario (_id, nome, login, senha) values (3,'KLEBER','kleber','123');");

        db.execSQL("insert into amostrador (_id, nome, setor) values (1, 'ronaldo','1');");
        db.execSQL("insert into amostrador (_id, nome, setor) values (2, 'fabiano','2');");
        db.execSQL("insert into amostrador (_id, nome, setor) values (3, 'kleber','3');");

        db.execSQL("insert into propriedaderural (_id, nome, identificacao) values (1, 'Faz. São José','1');");
        db.execSQL("insert into propriedaderural (_id, nome, identificacao) values (2, 'Sítio Santo Antonio','2');");
        db.execSQL("insert into propriedaderural (_id, nome, identificacao) values (3, 'Chácara Mané','3');");

        db.execSQL("insert into talhao (_id, identificacao, area) values (1, 'IDTC:1','Area:12');");
        db.execSQL("insert into talhao (_id, identificacao, area) values (2, 'IDTC:2','Area:19');");
        db.execSQL("insert into talhao (_id, identificacao, area) values (3, 'IDTC:3','Area:5');");

        db.execSQL("insert into corte (_id, tipo) values (1, '3');");
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
