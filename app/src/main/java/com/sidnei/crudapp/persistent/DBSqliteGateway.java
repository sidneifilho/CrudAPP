package com.sidnei.crudapp.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBSqliteGateway {

    private static DBSqliteGateway sInstance;
    private SQLiteDatabase db;
    private DBSqliteHelper helper;

    private DBSqliteGateway(Context cx){
        helper = new DBSqliteHelper(cx);
        open();
    }

    /**
     * Singleton instance to guarantee a unique instance of the Database Sqlite
     * */
    public static DBSqliteGateway getInstance(Context cx){
        if(sInstance == null){
            sInstance = new DBSqliteGateway(cx);
        }

        return sInstance;
    }

    /**
     * Function that open a connetion to the database if the connection doens't exist or if was closed
     * */
    public void open(){
        if(db == null){
            db = helper.getWritableDatabase();
        }else{
            if(!db.isOpen()){
                db = helper.getWritableDatabase();
            }
        }
    }

    /**
     * Function that close the database connection when is not useful anymore
     * */
    public void close(){
        if(db != null){
            if(db.isOpen()) {
                db.close();
            }
        }
    }

    /**
     * Get the current database object, if doens't is open, we will return the database connection opened
     * */
    public SQLiteDatabase getDatabase(){
        if(db == null){
            db = helper.getWritableDatabase();
        }else if(!db.isOpen()){
            db = helper.getWritableDatabase();
        }
        return db;
    }
}
