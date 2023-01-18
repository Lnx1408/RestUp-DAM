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

        sql.execSQL("create table promociones_tabla(idPromocion int primary key, descripcion text, tiempo text, imagen text)");

        sql.execSQL("create table reservacion(codigo int primary key, fecha text, hora text, cantidadp int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int oldVersion, int newVersion) {

    }
}
