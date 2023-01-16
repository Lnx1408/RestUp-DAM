package com.aply.taller.Fragments;
//androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aply.taller.MainActivity;
import com.aply.taller.R;

public class MenuPrincipal extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista;
        vista = inflater.inflate(R.layout.fragment_menu_principal, container, false);

        return vista;
    }


}