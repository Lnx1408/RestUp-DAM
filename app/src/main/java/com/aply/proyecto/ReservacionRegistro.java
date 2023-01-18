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

public class ReservacionRegistro extends AppCompatActivity {
    EditText tid, tfecha, thora, tcantidad ;
    Button sal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_registro);

        tid=(EditText)findViewById(R.id.tid);
        tfecha=(EditText)findViewById(R.id.tfecha);
        thora=(EditText)findViewById(R.id.thora);
        tcantidad=(EditText)findViewById(R.id.tcantidad);
        sal = (Button) findViewById(R.id.btncancel2);

        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ReservacionVista.class);
                startActivity(i);
            }
        });
    }//on create
    public void RegistroR(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codi = tid.getText().toString();
        String fech = tfecha.getText().toString();
        String hora = thora.getText().toString();
        String cant = tcantidad.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("codigo", codi);
        registro.put("fecha", fech);
        registro.put( "hora", hora);
        registro.put( "cantidadp", cant);

        db.insert("reservacion", null, registro);
        db.close();

        Toast.makeText(this, "Se realizo la reservacion con exito", Toast.LENGTH_SHORT).show();

    }


}