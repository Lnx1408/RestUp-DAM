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

public class RegistroPromociones extends AppCompatActivity {

    EditText idPromo, descripcionPromo, rutaImagenPromo, tiempoPromo;
    Button botonModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_promociones);

        idPromo=(EditText)findViewById(R.id.txtIdPromo);
        descripcionPromo=(EditText)findViewById(R.id.txtDescripcionPromo);
        rutaImagenPromo=(EditText)findViewById(R.id.txtImagen);
        tiempoPromo= (EditText)findViewById(R.id.txtTiempo);
        botonModificar= (Button) findViewById(R.id.btnCancelarPromo);


        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vp = new Intent(getApplicationContext(), VistaPromociones.class);
                startActivity(vp);
            }
        });

    }

    public void RegistrarPromocion(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = idPromo.getText().toString();
        String descripcion = descripcionPromo.getText().toString();
        String tiempo = tiempoPromo.getText().toString();
        String imagen = rutaImagenPromo.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("idPromocion", id);
        registro.put("descripcion", descripcion);
        registro.put( "tiempo", tiempo);
        registro.put( "imagen", imagen);
        db.insert("promociones_tabla", null, registro);
        db.close();

        Toast.makeText(this, "Se realizo el registro con exito", Toast.LENGTH_SHORT).show();

    }

}