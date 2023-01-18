package com.aply.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuRegistro extends AppCompatActivity {
    EditText id, nam, precio;
    Button bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registro);

        id=(EditText)findViewById(R.id.id);
        nam=(EditText)findViewById(R.id.name);
        precio=(EditText)findViewById(R.id.precio);
        bm = (Button) findViewById(R.id.btn2);

        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v = new Intent(getApplicationContext(), VistaMenu.class);
                startActivity(v);
            }
        });
    }

    public void RegistroM(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String cod = id.getText().toString();
        String des = nam.getText().toString();
        String pre = precio.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", des);
        registro.put( "precio", pre);
        db.insert("menu_restaurant", null, registro);
        db.close();

        Toast.makeText(this, "Se realizo el registro con exito", Toast.LENGTH_SHORT).show();

    }
}