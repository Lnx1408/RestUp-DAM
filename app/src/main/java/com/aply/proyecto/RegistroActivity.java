package com.aply.proyecto;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity {

    Spinner opciones;
    TextView txtfecha;
    EditText txtNombres, txtApellidos, txtPhone, txtEmail, txtPassword;
    Button btnRegistrar, btnCancelar, btnfecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        opciones = (Spinner)findViewById(R.id.spopciones);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);


         txtNombres = (EditText) findViewById(R.id.txtNombres);
         txtApellidos = (EditText) findViewById(R.id.txtApellidos);
         txtPhone = (EditText) findViewById(R.id.txtPhone);
         txtfecha = (TextView) findViewById(R.id.tfecha);
         txtEmail = (EditText) findViewById(R.id.txtEmail);
         txtPassword = (EditText) findViewById(R.id.txtPassword);
         btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
         btnCancelar = (Button)findViewById(R.id.btnCancelar);


         btnfecha = (Button)findViewById(R.id.btnfecha);
        btnfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirCalen(view);
            }

        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarAlertas();
                RegistroU(view);
             //   RegistrarDatos(view);
                limpiarDatos();


            }
            public void limpiarDatos(){
                txtNombres.setText("");
                txtApellidos.setText("");;
                txtPhone.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
            }
            public void MostrarAlertas(){
                if(txtNombres.getText().toString().isEmpty()){
                    txtNombres.setError("Asigne un nombre");
                }
                if(txtApellidos.getText().toString().isEmpty()) {
                    txtApellidos.setError("Asigne los apellidos");
                }
                if(txtPhone.getText().toString().isEmpty()){
                    txtPhone.setError("Asigne un numero de telefono");
                }

                if(txtEmail.getText().toString().isEmpty()){
                    txtEmail.setError("Asigne un correo");
                }
                if(txtPassword.getText().toString().isEmpty()){
                    txtPassword.setError("Asigne una contarseña");
                }
                if(txtNombres.getText().toString().isEmpty() || txtApellidos.getText().toString().isEmpty() ||
                        txtPhone.getText().toString().isEmpty()  || txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Existen campos vacios.", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"El usuario "+txtNombres.getText().toString()+" "+txtApellidos.getText().toString()+ " se registro con exito.", Toast.LENGTH_SHORT).show();
                }
            }
            public void RegistrarDatos(View v){
                int statusSD = verificarStatusSD ();
                String DatosUsuario;

                if(statusSD == 0 ){
                    try{
                        File f =new File(getExternalFilesDir(null), "DatosUsuario.txt");
                        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f,false));
                        String genero ="";
                        DatosUsuario = txtNombres.getText().toString ()+";"+
                                txtApellidos.getText().toString ()+";"+
                                opciones.getSelectedItem().toString()+";"+
                                txtPhone.getText().toString()+";"+
                                genero +";"+
                                txtEmail.getText().toString()+";"+
                                txtPassword.getText().toString();
                        writer.write(DatosUsuario);
                        writer.close();
                        Toast.makeText (getApplicationContext (), "Guardado con éxito en SD", Toast.LENGTH_SHORT).show() ;
                    }catch (Exception ex ){
                        Log.e("Archivo","Error al guardar el archivo");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"No se puede guardar ",Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });


    }

    public void RegistroU(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String name = txtNombres.getText().toString();
        String ape = txtApellidos.getText().toString();
        String telf = txtPhone.getText().toString();
        String fnaci = txtfecha.getText().toString();
        String correo = txtEmail.getText().toString();
        String pass = txtPassword.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", name);
        registro.put("apellido", ape);
        registro.put( "telefono", telf);
        registro.put("fnacimiento", fnaci);
        registro.put("correo", correo);
        registro.put("password", pass);
        db.insert("usuario", null, registro);
        db.close();

        Toast.makeText(this, "Se realizo el registro con exito", Toast.LENGTH_SHORT).show();

    }


    public int verificarStatusSD(){

        String estado = Environment.getExternalStorageState();
        if(estado.equals(Environment.MEDIA_MOUNTED)){

            Toast.makeText(getApplicationContext(),"SD Localizada.",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            Toast.makeText(getApplicationContext(),"SD - Solo lectura.",Toast.LENGTH_SHORT).show();
            return 1;
        }
        else {
            Toast.makeText(getApplicationContext(),"SD no localizada.",Toast.LENGTH_SHORT).show();
            return 2;
        }
    }


    public void AbrirCalen(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dp = new DatePickerDialog(this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {
                String fecha = dayofmonth +"/" + month +1 + "/" + year;
                txtfecha.setText(fecha);
            }
        }, anio, mes, dia);
        dp.show();



    }
}

