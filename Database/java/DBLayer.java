package com.example.robertopinotti.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBLayer {

    // variabili database
    private static final String DATABASE_NAME = "testDB";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private  static Context ourContext;
    private SQLiteDatabase ourDatabase;

    //classe statica
    private static class DbHelper extends SQLiteOpenHelper {

        // costruttore
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // creazione database
            db.execSQL("CREATE TABLE tabella1 (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cognome TEXT);");

            // popolazione database
            db.execSQL("INSERT INTO tabella1 (nome, cognome) VALUES ('Roberto','Pinotti');");
            db.execSQL("INSERT INTO tabella1 (nome, cognome) VALUES ('Carlo','Iocco');");
            db.execSQL("INSERT INTO tabella1 (nome, cognome) VALUES ('Luca','Leporini');");
            db.execSQL("INSERT INTO tabella1 (nome, cognome) VALUES ('Antonio','De Simone');");
            db.execSQL("INSERT INTO tabella1 (nome, cognome) VALUES ('Nicolas','Poletti');");
        }

        // aggiornamento database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    // costruttore
    public DBLayer(Context c){
        this.ourContext = c;
    }

    // apertura
    public DBLayer open() throws SQLException {
        this.ourHelper = new DbHelper(ourContext);
        this.ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    // chiusura
    public void close(){
        this.ourHelper.close();
    }


    public Cursor Execute(String Query){
        Cursor c;
        c = ourDatabase.rawQuery(Query,null);
        return c;
    }
}