package com.sidnei.crudapp.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSqliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Fortbrasil.db";
    private static final int DATABASE_VERSION = 2;
    private final String CREATE_TABLE_PERSON = "CREATE TABLE Persons (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, CPF TEXT UNIQUE NOT NULL, CEP TEXT, UF TEXT NOT NULL, Address TEXT, Sex TEXT);";
    private final String TABLE_PERSON = "Persons";

    public DBSqliteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
            onCreate(db);
        }
    }

}
