package com.aply.proyecto.Fragments;
//androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aply.proyecto.R;

public class MenuPrincipal extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista;
        vista = inflater.inflate(R.layout.fragment_menu_principal, container, false);

        return vista;
    }


}