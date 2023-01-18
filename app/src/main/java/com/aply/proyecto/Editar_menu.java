package com.aply.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Editar_menu extends AppCompatActivity {
    EditText id, name, precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_menu);

        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        precio=(EditText)findViewById(R.id.precio);

        Intent i = getIntent();

        String t1 = i.getStringExtra("codigo").toString();
        String t2 = i.getStringExtra("descripcion").toString();
        String t3 = i.getStringExtra("precio").toString();

        id.setText(t1);
        name.setText(t2);
        precio.setText(t3);
    }

    public void eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String cod = id.getText().toString();
        //  Cursor fila = db.rawQuery("SELECT descripcion , precio from articulo where codigo='"+cod+"'", null);
        int cant = db.delete("menu_restaurant", "codigo=" +cod, null);
        db.close();
        id.setText("");
        name.setText("");
        precio.setText("");
        if(cant == 1){
            Toast.makeText(this, "Se borro el producto con esa codigo", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "no existe el producto con ese codigo", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
    public void modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String cod = id.getText().toString();
        String des = name.getText().toString();
        String pre = precio.getText().toString();

        if(!cod.isEmpty() && !des.isEmpty() && !pre.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", cod);
            registro.put("descripcion", des);
            registro.put( "precio", pre);
            int cant = db.update("menu_restaurant", registro, "codigo="+cod, null );
            db.close();
            if(cant == 1){
                Toast.makeText(this, "Se modificaron los datos ", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El producto no existe ", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "no existe el producto con ese codigo", Toast.LENGTH_SHORT).show();
        }

    }


    public void regresar(View view){
        Intent ob = new Intent(this, VistaMenu.class);
        startActivity(ob);
    }
}