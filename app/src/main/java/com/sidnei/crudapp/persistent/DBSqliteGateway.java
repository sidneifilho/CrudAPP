package com.sidnei.crudapp.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBSqliteGateway {

    private static DBSqliteGateway sInstance;
    private SQLiteDatabase db;

    private DBSqliteGateway(Context cx){
        DBSqliteHelper helper = new DBSqliteHelper(cx);
        db = helper.getWritableDatabase();
    }

    public static DBSqliteGateway getInstance(Context cx){
        if(sInstance == null){
            sInstance = new DBSqliteGateway(cx);
        }

        return sInstance;
    }

    public SQLiteDatabase getDatabase(){
        return db;
    }
}
