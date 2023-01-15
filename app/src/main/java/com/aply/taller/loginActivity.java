package com.aply.taller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    final String password="123456";
    final String validuser="Grupo9";

    private boolean iniciarSesion() {
        try {
            SharedPreferences sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
            if (login(sp.getString("user", null), sp.getString("pass", null))) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        iniciarSesion();

        setContentView(R.layout.activity_login);

        EditText user = (EditText)findViewById(R.id.username);
        EditText pass =(EditText)findViewById(R.id.password);
        Button ingresar = (Button)findViewById(R.id.login);
        Button salir = (Button)findViewById(R.id.btnSalir);



        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validar();

            }
            /*private String resultado(Context context, String clave){
                SharedPreferences sp = context.getSharedPreferences("Login", MODE_PRIVATE);
                return sp.getString(clave, "");
            }*/
            private void GuardaSharedPre(String usuario, String contrasenia){
                try{
                    SharedPreferences sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("user", usuario);
                    editor.putString("pass", contrasenia);
                    editor.commit();
                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
            private void mensaje(){
                String u=user.getText().toString(), c=pass.getText().toString();
                AlertDialog.Builder alerta = new AlertDialog.Builder(loginActivity.this);
                alerta.setMessage("Desea mantener la sesión activa")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GuardaSharedPre(u, c);
                                Intent principal = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(principal);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                 Intent principal = new Intent(getBaseContext(), MainActivity.class);
                                  startActivity(principal);
                            }
                        });
                AlertDialog ad = alerta.create();
                ad.setTitle("Aviso");
                ad.show();


            }
            private void validar(){
                if(user.getText().toString().isEmpty()){
                    user.setError("Ingrese un usuario");
                }
                if(pass.getText().toString().isEmpty()){
                    pass.setError("Ingrese una contraseña");
                }
                if(login(user.getText().toString(), pass.getText().toString())){
                    mensaje();
                    user.setText("");
                    pass.setText("");
                }else{

                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
    public boolean login(String validuser, String password){
        return validuser.equals(this.validuser) && password.equals(this.password);
    }
}
