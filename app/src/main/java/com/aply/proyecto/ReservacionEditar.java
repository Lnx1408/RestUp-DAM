package com.aply.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReservacionEditar extends AppCompatActivity {
    EditText tid, tfecha, thora, tcantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_editar);

        tid=(EditText)findViewById(R.id.tid);
        tfecha=(EditText)findViewById(R.id.tfecha);
        thora=(EditText)findViewById(R.id.thora);
        tcantidad=(EditText)findViewById(R.id.tcantidad);

        Intent o = getIntent();

        String t1 = o.getStringExtra("codigo").toString();
        String t2 = o.getStringExtra("fecha").toString();
        String t3 = o.getStringExtra("hora").toString();
        String t4 = o.getStringExtra("cantidadp").toString();

        tid.setText(t1);
        tfecha.setText(t2);
        thora.setText(t3);
        tcantidad.setText(t4);
    }//on create

    public void eliminarR(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codi = tid.getText().toString();
        int cant = db.delete("reservacion", "codigo=" +codi, null);
        db.close();
        tid.setText("");
        tfecha.setText("");
        thora.setText("");
        tcantidad.setText("");
        if(cant == 1){
            Toast.makeText(this, "Se borro la reservacion con ese codigo", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "no existe la reservacion con ese codigo", Toast.LENGTH_LONG).show();
            db.close();
        }
    }

    public void modificarR(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codi = tid.getText().toString();
        String fech = tfecha.getText().toString();
        String hora = thora.getText().toString();
        String cant = tcantidad.getText().toString();

        if(!codi.isEmpty() && !fech.isEmpty() && !hora.isEmpty() && !cant.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codi);
            registro.put("fecha", fech);
            registro.put( "hora", hora);
            registro.put( "cantidadp", cant);

            int cano = db.update("reservacion", registro, "codigo="+codi, null );
            db.close();
            if(cano == 1){
                Toast.makeText(this, "Se modificaron los datos ", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "La reservacion no existe ", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(this, "no existe reservacion con ese codigo", Toast.LENGTH_LONG).show();
        }
    }
    public void regresar1(View view){
        Intent up = new Intent(this, ReservacionVista.class);
        startActivity(up);
    }
}