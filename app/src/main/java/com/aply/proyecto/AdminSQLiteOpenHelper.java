package com.aply.proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper  extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sql) {
        sql.execSQL("create table menu_restaurant(codigo int primary key, descripcion text, precio real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int oldVersion, int newVersion) {

    }
}
