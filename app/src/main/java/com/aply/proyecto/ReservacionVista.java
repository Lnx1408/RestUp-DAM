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
import com.aply.proyecto.Entidades.Reservacion;

import java.util.ArrayList;

public class ReservacionVista extends AppCompatActivity {
    Button  bbuscar, bsalir;
    EditText id;
    ListView lvData;
    ArrayList<String> datos = new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_vista);

        bbuscar = (Button)findViewById(R.id.bbuscar);
        id = (EditText)findViewById(R.id.id);
        bsalir = (Button)findViewById(R.id.bsalir);
        lvData = findViewById(R.id.lvData);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();


        final Cursor fila = db.rawQuery("SELECT * from reservacion", null);
        int codi = fila.getColumnIndex("codigo");
        int fech = fila.getColumnIndex("fecha");
        int hora = fila.getColumnIndex("hora");
        int cant = fila.getColumnIndex("cantidadp");
        datos.clear();

        adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,datos);
        lvData.setAdapter(adapter);

        final ArrayList<Reservacion> re = new ArrayList<>();
        if(fila.moveToFirst()){
            do{
                Reservacion r = new Reservacion();
                r.codigo = fila.getString(codi);
                r.fecha = fila.getString(fech);
                r.hora = fila.getString(hora);
                r.cantidadP = fila.getString(cant);
                re.add(r);

                datos.add( fila.getString(codi) + " \t "  + fila.getString(fech) + " \t "  + fila.getString(hora)+ " \t "  + fila.getString(cant) );
            }while(fila.moveToNext());
            adapter.notifyDataSetChanged();
            lvData.invalidateViews();

        }else{
            Toast.makeText(this, "no existe datos en la descripcion", Toast.LENGTH_SHORT).show();
            db.close();
        }
        bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(p);
            }
        });

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String op = datos.get(position).toString();
                Reservacion r = re.get(position);
                Intent n = new Intent(getApplicationContext(), ReservacionEditar.class);
                n.putExtra("codigo", r.codigo);
                n.putExtra("fecha", r.fecha);
                n.putExtra("hora", r.hora);
                n.putExtra("cantidadp", r.cantidadP);
                startActivity(n);

            }
        });
    }
    public void abrirNue(View v){
        Intent re = new Intent(getApplicationContext(), ReservacionRegistro.class);
        startActivity(re);
    }

    public void consultarDA(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String cod =  id.getText().toString();
        Cursor fila = db.rawQuery("SELECT * from reservacion where codigo='"+cod+"'", null);

        int codi = fila.getColumnIndex("codigo");
        int fech = fila.getColumnIndex("fecha");
        int hora = fila.getColumnIndex("hora");
        int cant = fila.getColumnIndex("cantidadp");
        datos.clear();

        adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,datos);
        lvData.setAdapter(adapter);

        final ArrayList<Reservacion> re = new ArrayList<>();
        if(fila.moveToFirst()){
            do{
                Reservacion r = new Reservacion();
                r.codigo = fila.getString(codi);
                r.fecha = fila.getString(fech);
                r.hora = fila.getString(hora);
                r.cantidadP = fila.getString(cant);
                re.add(r);

                datos.add( fila.getString(codi) + " \t "  + fila.getString(fech) + " \t "  + fila.getString(hora)+ " \t "  + fila.getString(cant) );
            }while(fila.moveToNext());
            adapter.notifyDataSetChanged();
            lvData.invalidateViews();
        }else{
            Toast.makeText(this, "no existe la id", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

}