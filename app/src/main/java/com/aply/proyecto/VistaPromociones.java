package com.aply.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aply.proyecto.Entidades.MenuRest;
import com.aply.proyecto.Entidades.clsPromociones;

import java.util.ArrayList;

public class VistaPromociones extends AppCompatActivity {

    Button btnRetiro, btnBuscar;
    EditText txtBusqueda;
    ListView lstPromo;

    ArrayList<String> datos = new ArrayList<String>();

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_promociones);


        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        txtBusqueda = (EditText)findViewById(R.id.txtBusqueda);
        btnRetiro = (Button)findViewById(R.id.btnSalir);
        lstPromo = findViewById(R.id.lstPromo);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();


        final Cursor fila = db.rawQuery("SELECT * from promociones_tabla", null);
        int id = fila.getColumnIndex("idPromocion");
        int descripcion = fila.getColumnIndex("descripcion");
        int tiempo = fila.getColumnIndex("tiempo");
        int imagen = fila.getColumnIndex("imagen");
        datos.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,datos);
        lstPromo.setAdapter(arrayAdapter);

        /*
        *
        *
        * */
        final ArrayList<clsPromociones> ArrayPromo = new ArrayList<>();
        if(fila.moveToFirst()){
            do{
                clsPromociones promociones = new clsPromociones();
                promociones.idPromocion = fila.getString(id);
                promociones.decripcion = fila.getString(descripcion);
                promociones.tiempo = fila.getString(tiempo);
                promociones.imagen = fila.getString(imagen);
                ArrayPromo.add(promociones);

                datos.add( fila.getString(id) + " \t "  + fila.getString(descripcion) + " \t "  + fila.getString(tiempo) + " \t "  + fila.getString(imagen) );
            }while(fila.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lstPromo.invalidateViews();

        }else{
            Toast.makeText(this, "no existe datos en la descripcion", Toast.LENGTH_SHORT).show();
            db.close();
        }

        btnRetiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(p);
            }
        });

        lstPromo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String op = datos.get(position).toString();
                clsPromociones promo = ArrayPromo.get(position);
                Intent i = new Intent(getApplicationContext(), EditarPromociones.class);
                i.putExtra("idPromocion", promo.idPromocion);
                i.putExtra("descripcion", promo.decripcion);
                i.putExtra("tiempo", promo.tiempo);
                i.putExtra("imagen", promo.imagen);
                startActivity(i);

            }
        });

    }
    public void abrirNuevaPromo(View v){
        Intent re = new Intent(getApplicationContext(), RegistroPromociones.class);
        startActivity(re);
    }

    public void consultarDescripcion(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String busqueda =  txtBusqueda.getText().toString();
        Cursor fila = db.rawQuery("SELECT * from promociones_tabla where descripcion='"+busqueda+"'", null);

        int id = fila.getColumnIndex("idPromocion");
        int descripcion = fila.getColumnIndex("descripcion");
        int tiempo = fila.getColumnIndex("tiempo");
        int imagen = fila.getColumnIndex("imagen");
        datos.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,datos);
        lstPromo.setAdapter(arrayAdapter);

        final ArrayList<clsPromociones> promos = new ArrayList<>();
        if(fila.moveToFirst()){
            do{
                clsPromociones promo = new clsPromociones();
                promo.idPromocion = fila.getString(id);
                promo.decripcion = fila.getString(descripcion);
                promo.tiempo = fila.getString(tiempo);
                promo.imagen = fila.getString(imagen);
                promos.add(promo);

                datos.add( fila.getString(id) + " \t "  + fila.getString(descripcion) + " \t "  + fila.getString(tiempo) + " \t "  + fila.getString(imagen) );
            }while(fila.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lstPromo.invalidateViews();
        }else{
            Toast.makeText(this, "no existe una promoción con esa descripcion", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
}