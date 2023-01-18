package com.aply.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarPromociones extends AppCompatActivity {
     EditText idp, desp, tday, ietx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_promociones);

        idp=(EditText)findViewById(R.id.txtIdPromo);
        desp=(EditText)findViewById(R.id.txtDescripcionPromo);
        tday=(EditText)findViewById(R.id.txtTiempo);
        ietx=(EditText)findViewById(R.id.txtImagen);

        Intent o = getIntent();

        String t1 = o.getStringExtra("idPromocion").toString();
        String t2 = o.getStringExtra("descripcion").toString();
        String t3 = o.getStringExtra("tiempo").toString();
        String t4 = o.getStringExtra("imagen").toString();

        idp.setText(t1);
        desp.setText(t2);
        tday.setText(t3);
        ietx.setText(t4);
    }

    public void eliminarPro(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codr = idp.getText().toString();
        int cant = db.delete("promociones_tabla", "idPromocion=" +codr, null);
        db.close();
        idp.setText("");
        desp.setText("");
        tday.setText("");
        ietx.setText("");
        if(cant == 1){
            Toast.makeText(this, "Se borro la promocion con ese id", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "no existe la promocion con ese id", Toast.LENGTH_LONG).show();
            db.close();
        }
    }

    public void modificarPro(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String idpro = idp.getText().toString();
        String descp = desp.getText().toString();
        String time = tday.getText().toString();
        String img = ietx.getText().toString();

        if(!idpro.isEmpty() && !descp.isEmpty() && !time.isEmpty() && !img.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("idPromocion", idpro);
            registro.put("descripcion", descp);
            registro.put( "tiempo", time);
            registro.put( "imagen", img);

            int cano = db.update("promociones_tabla", registro, "idPromocion="+idpro, null );
            db.close();
            if(cano == 1){
                Toast.makeText(this, "Se modificaron los datos ", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "La promocion no existe ", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(this, "no existe la promocion con ese id", Toast.LENGTH_LONG).show();
        }
    }

    public void regresarPro(View view){
        Intent rp = new Intent(getApplicationContext(), VistaPromociones.class);
        startActivity(rp);
    }
}