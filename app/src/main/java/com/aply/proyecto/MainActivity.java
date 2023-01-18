package com.aply.proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aply.proyecto.Fragments.Grupo_fragment;
import com.aply.proyecto.Fragments.Main_fragment;
import com.aply.proyecto.Fragments.MenuPrincipal;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;



    // variables para cargar el fragment
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.navegar);
        navigationView = findViewById(R.id.navegar_a);
        //establecer evento onclick al navegation view
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //cargar fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content , new MenuPrincipal());
        fragmentTransaction.commit();

    }

    public void nuevo(View view){
        Intent ob = new Intent(getApplicationContext(), VistaMenu.class);
        startActivity(ob);
    }

    public void Borrar(View v){
        try{
            SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId()== R.id.btnMenuPrincipal){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content , new MenuPrincipal());
            fragmentTransaction.commit();
        }

        if(item.getItemId() == R.id.acerca){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content , new Grupo_fragment());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.registrarCliente){
            Intent rc = new Intent(getApplicationContext(), RegistroActivity.class);
            startActivity(rc);
        }


        if(item.getItemId() == R.id.bnsalir){
            finish();
            System.exit(0);
        }
        return false;
    }




}