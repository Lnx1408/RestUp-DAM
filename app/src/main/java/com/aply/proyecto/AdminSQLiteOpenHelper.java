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
        sql.execSQL("create table if not exists menu_restaurant(codigo integer primary key, descripcion text, precio real)");

        sql.execSQL("create table if not exists promociones_tabla(idPromocion integer primary key, descripcion text, tiempo text, imagen text)");

        sql.execSQL("create table if not exists reservacion(codigo integer primary key, fecha text, hora text, cantidadp integer)");

        sql.execSQL("create table if not exists usuario(codigo integer primary key autoincrement, nombre text, apellido text, telefono text, fnacimiento text, correo text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int oldVersion, int newVersion) {

    }
}
