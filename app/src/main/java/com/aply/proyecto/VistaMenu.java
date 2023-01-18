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

import java.util.ArrayList;

public class VistaMenu extends AppCompatActivity {

    Button b2, bp;
    EditText plat;
    ListView lis;
    ArrayList<String> datos = new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_menu);

        bp = (Button)findViewById(R.id.bcp);
        plat = (EditText)findViewById(R.id.product);
        b2 = (Button)findViewById(R.id.salir);
        lis = findViewById(R.id.lista);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();


        final Cursor fila = db.rawQuery("SELECT * from menu_restaurant", null);
        int id = fila.getColumnIndex("codigo");
        int name = fila.getColumnIndex("descripcion");
        int precio = fila.getColumnIndex("precio");
        datos.clear();

        adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,datos);
        lis.setAdapter(adapter);

        final ArrayList<MenuRest> me = new ArrayList<>();
        if(fila.moveToFirst()){
            do{
                MenuRest m = new MenuRest();
                m.codigo = fila.getString(id);
                m.decripcion = fila.getString(name);
                m.precio = fila.getString(precio);
                me.add(m);

                datos.add( fila.getString(id) + " \t "  + fila.getString(name) + " \t "  + fila.getString(precio) );
            }while(fila.moveToNext());
            adapter.notifyDataSetChanged();
            lis.invalidateViews();

        }else{
            Toast.makeText(this, "no existe datos en la descripcion", Toast.LENGTH_SHORT).show();
            db.close();
        }

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(p);
            }
        });

        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String op = datos.get(position).toString();
                MenuRest m = me.get(position);
                Intent i = new Intent(getApplicationContext(), Editar_menu.class);
                i.putExtra("codigo", m.codigo);
                i.putExtra("descripcion", m.decripcion);
                i.putExtra("precio", m.precio);
                startActivity(i);

            }
        });

    }
    public void abrirN(View v){
        Intent re = new Intent(getApplicationContext(), MenuRegistro.class);
        startActivity(re);
    }

    public void consultarDescripcion(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String des =  plat.getText().toString();
        Cursor fila = db.rawQuery("SELECT * from menu_restaurant where descripcion='"+des+"'", null);

        int id = fila.getColumnIndex("codigo");
        int name = fila.getColumnIndex("descripcion");
        int precio = fila.getColumnIndex("precio");
        datos.clear();

        adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,datos);
        lis.setAdapter(adapter);

        final ArrayList<MenuRest> me = new ArrayList<>();
        if(fila.moveToFirst()){
            do{
                MenuRest m = new MenuRest();
                m.codigo = fila.getString(id);
                m.decripcion = fila.getString(name);
                m.precio = fila.getString(precio);
                me.add(m);

                datos.add( fila.getString(id) + " \t "  + fila.getString(name) + " \t "  + fila.getString(precio) );
            }while(fila.moveToNext());
            adapter.notifyDataSetChanged();
            lis.invalidateViews();
        }else{
            Toast.makeText(this, "no existe el plato con esa descripcion", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
}