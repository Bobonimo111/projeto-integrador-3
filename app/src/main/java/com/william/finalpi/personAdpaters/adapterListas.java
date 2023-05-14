package com.william.finalpi.personAdpaters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.william.finalpi.R;
import com.william.finalpi.objetos.objLista;

import java.util.List;

public class adapterListas extends RecyclerView.Adapter<adapterListas.MyViewHolder>{
    private Context context;
    private List<objLista> listaListas;
    private View.OnClickListener VisualizarLista = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Chamar proxima tela, e enviar o id da lista que foi clickada
            Log.i("Click","Lista clicada");
        }

    };

    public adapterListas(Context context, List<objLista> listaListas) {
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
        objLista lista = listaListas.get(position);
        Holder.textViewListaName.setText(lista.getName());
        Holder.textViewListaName.setOnClickListener(VisualizarLista);

    }

    @Override
    public int getItemCount() {
        return listaListas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewIcon;
        private TextView textViewListaName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            textViewListaName = itemView.findViewById(R.id.textViewListaName);

        }

    }
}
