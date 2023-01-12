package com.aply.taller.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aply.taller.Entidades.Persona;
import com.aply.taller.R;

import java.util.ArrayList;

public class AdapterIntegrantes extends RecyclerView.Adapter<AdapterIntegrantes.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Persona> estudiante;
    private View.OnClickListener listener;

    public AdapterIntegrantes(Context context, ArrayList <Persona> estudiante){
        this.inflater = LayoutInflater.from(context);
        this.estudiante = estudiante;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.integrates, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = estudiante.get(position).getNombre();
        String telefono = estudiante.get(position).getTelefono();
        String correo = estudiante.get(position).getCorreo();
        int imagen = estudiante.get(position).getImagenid();
        holder.nombre.setText(nombre);
        holder.telefono.setText(telefono);
        holder.correo.setText(correo);
        holder.imagen.setImageResource(imagen);

    }
    public void setOnlistener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return estudiante.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, telefono, correo;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.txtnombre);
            telefono = itemView.findViewById(R.id.txtTelefono);
            correo = itemView.findViewById(R.id.txtCorreo);
            imagen = itemView.findViewById(R.id.img);
        }
    }
}
