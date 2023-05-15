package com.william.finalpi.personAdpaters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.william.finalpi.R;
import com.william.finalpi.objetos.ObjLista;

import java.util.List;

public class AdapterListas extends RecyclerView.Adapter<AdapterListas.MyViewHolder>{
    private Context context;
    private List<ObjLista> listaListas;
    private View.OnClickListener VisualizarLista = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Chamar proxima tela, e enviar o id da lista que foi clickada
            Log.i("Click","Lista clicada");
        }

    };

    public AdapterListas(Context context, List<ObjLista> listaListas) {
        this.context = context;
        this.listaListas = listaListas;
    }

    @NonNull
    @Override
    //Setar qual o layout personalizado utilizar
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    //Quais ações seram tomadas por, item, como adicionar listeners entre outros.
    public void onBindViewHolder(@NonNull MyViewHolder Holder, int position) {
        ObjLista lista = listaListas.get(position);
        Holder.buttonListaName.setText(lista.getName());
        Holder.buttonListaName.setOnClickListener(VisualizarLista);
        Holder.imageViewIcon.setImageResource(R.drawable.icons8);

    }

    @Override
    public int getItemCount() {
        return listaListas.size();
    }
    public void add(int postion,ObjLista item){
        listaListas.add(item);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewIcon;
        private Button buttonListaName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            buttonListaName = itemView.findViewById(R.id.buttonListaName);

        }

    }
}
