package com.home.rhounsell.example.dropdownlistitem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RHounsell on 14/03/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="vaccines.db";
    private static final int DATABASE_VERSION=1;
    private static SQLiteHelper helper;


    public static synchronized SQLiteHelper getInstance(Context context){
        if(helper == null){
            helper = new SQLiteHelper(context.getApplicationContext());
        }
        return helper;
    }
    private SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for(DatabaseCreateStrings sql:DatabaseCreateStrings.values()){
            sqLiteDatabase.execSQL(sql.create());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "+oldVersion+
                " to "+ newVersion+", which will destroy all old data");
    }
}
