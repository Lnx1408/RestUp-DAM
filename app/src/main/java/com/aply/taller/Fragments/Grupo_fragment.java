package com.aply.taller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aply.taller.Adaptador.AdapterIntegrantes;
import com.aply.taller.Entidades.Persona;
import com.aply.taller.R;

import java.util.ArrayList;

public class Grupo_fragment extends Fragment {
    AdapterIntegrantes adapterIntegrantes;
    RecyclerView recyclerView;
    ArrayList <Persona> lista;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
     View view = inflater.inflate(R.layout.framnet_grupo, container,  false);
     recyclerView = view.findViewById(R.id.ReciclerView);
     lista = new ArrayList<>();
     // cargar lista
        cargarLista();
        // mostrar lista
        mostrarLIsta();
    return view;
    }
    public void cargarLista(){
        lista.add(new Persona("Beltrán Santistevan Steven", "+593964061358", "carlos.beltrans@ug.edu.ec",R.mipmap.steven));
        lista.add(new Persona("Campoverde Leiva Sergio", "+593986809251", "sergio.compoverdel@ug.edu.ec",R.mipmap.sergio));
        lista.add(new Persona("Lino Sánchez Yermin", "+593996546788", "yermin.linos@ug.edu.ec",R.mipmap.yermin));
        lista.add(new Persona("Peralta Peralta Arlette", "+593990836334", "arlette.peraltap@ug.edu.ec",R.mipmap.arlette));

    }
    public void mostrarLIsta(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterIntegrantes = new AdapterIntegrantes(getContext(), lista);
        recyclerView.setAdapter(adapterIntegrantes);
    }


}
