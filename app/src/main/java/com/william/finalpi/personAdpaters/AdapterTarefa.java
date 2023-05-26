package com.william.recycleradapterperson.tarefas.CustomAdapters;

import android.content.Context;

import android.graphics.Paint;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.william.recycleradapterperson.R;

import java.util.List;


public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder>{



    private List<TarefaObj> lista;
    private Context context;


    public TarefaAdapter(Context context, List<TarefaObj> lista ){
        this.lista = lista;
        this.context = context;
    }



    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tarefas, parent, false);
        itemLista.setOnClickListener(mOnClickListener);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder Holder, int position) {
        TarefaObj tarefa = lista.get(position);






        if(tarefa.isConcluida()){
            //Cria uma UnderLine
//            Holder.campoTarefaTxt.setPaintFlags(Holder.campoTarefaTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//            Faz uma Middle Line
            SpannableString spannableString = new SpannableString(tarefa.getTitle());
            spannableString.setSpan(new StrikethroughSpan(),0,spannableString.length(),0);
            Holder.campoTarefaTxt.setText(spannableString);
            Holder.campoTarefaConcluida.setChecked(tarefa.isConcluida());
        }else{
            Holder.campoTarefaTxt.setText(tarefa.getTitle());
        }

        // Quando a chekBox for clicada é verificado se ela está clicada ou não
        Holder.campoTarefaConcluida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Adapter","Caixinha pressionada");
                if(Holder.campoTarefaConcluida.isChecked()){
                    SpannableString spannableString = new SpannableString(tarefa.getTitle());
                    spannableString.setSpan(new StrikethroughSpan(),0,spannableString.length(),0);
                    Holder.campoTarefaTxt.setText(spannableString);
                }else{
                    Holder.campoTarefaTxt.setText(tarefa.getTitle());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public TarefaObj removeItem(int position) {
        final TarefaObj model = lista.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem( TarefaObj model) {
        lista.add(model);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final TarefaObj model = lista.remove(fromPosition);
        lista.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }





    public class MyViewHolder extends  RecyclerView.ViewHolder{
        CheckBox campoTarefaConcluida;
        TextView campoTarefaTxt;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            campoTarefaConcluida = itemView.findViewById(R.id.CampoTarefaConcluida);
            campoTarefaTxt= itemView.findViewById(R.id.CampoTarefaTxt);
                   }

    }



}
