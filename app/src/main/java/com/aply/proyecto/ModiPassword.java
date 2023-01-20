package com.aply.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ModiPassword extends AppCompatActivity {
    String usuario, password;
    TextView txtUsuari;
    Button btnModPass;
    EditText newpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modi_password);
        extraerDatos();

        txtUsuari = (TextView)findViewById(R.id.txtUsername);
        btnModPass = (Button) findViewById(R.id.btnCambiar);
        newpass = (EditText) findViewById(R.id.PassNew);

        txtUsuari.setText(usuario);
        btnModPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPassword(usuario,newpass.getText().toString());
            }
        });


    }

    public void actualizarPassword(String validuser, String password){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("SELECT correo, password from usuario where correo = '"+validuser+"' and password = '"+password+"'" , null);
        if(fila.moveToFirst()){

            ContentValues registro = new ContentValues();
            registro.put("password", password);
            int cant = db.update("usuario", registro, "correo= '"+usuario+"'", null );
            db.close();
            if(cant == 1){
                Toast.makeText(this, "Se modificaron los datos ", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El registro no existe ", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText( this, "Los datos ingresados no son correctos", Toast.LENGTH_SHORT ).show();
        }
    }


    public int verificarStatusSD(){

        String estado = Environment.getExternalStorageState();
        if(estado.equals(Environment.MEDIA_MOUNTED)){

            //Toast.makeText(getApplicationContext(),"SD Localizada.",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            //Toast.makeText(getApplicationContext(),"SD - Solo lectura.",Toast.LENGTH_SHORT).show();
            return 1;
        }
        else {
            Toast.makeText(getApplicationContext(),"SD no localizada.",Toast.LENGTH_SHORT).show();
            return 2;
        }
    }


    public void extraerDatos(){
        int statusSD = verificarStatusSD ();
        String datosUsuario ="";
        if(statusSD == 0 ) {
            try {
                File rutaArchivo = new File(getExternalFilesDir(null),"DatosUsuario.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(rutaArchivo));
                BufferedReader reader = new BufferedReader(inputStreamReader);

                while((datosUsuario = reader.readLine())!=null){
                    String[] datos = datosUsuario.split(";");
                    usuario = datos[0];
                    password = datos[1];
                }
                reader.close();
            } catch (Exception e) {
                Log.e("Archivo","Error al acceder al archivo");
            }
        }
    }

}