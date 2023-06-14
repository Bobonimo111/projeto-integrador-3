package com.william.finalpi.personAdpaters;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;

import com.william.finalpi.R;
import com.william.finalpi.bd.MyDataBaseHelper;
import com.william.finalpi.objetos.ObjTarefa;

import java.util.List;


public class AdapterTarefa extends RecyclerView.Adapter<AdapterTarefa.MyViewHolder>{



    private List<ObjTarefa> lista;
    private Context context;
    MyDataBaseHelper mydb;


    public AdapterTarefa(Context context, List<ObjTarefa> lista ){
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
        ObjTarefa tarefa = lista.get(position);
        mydb = new MyDataBaseHelper(context);





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
                    //STYLE
                    SpannableString spannableString = new SpannableString(tarefa.getTitle());
                    spannableString.setSpan(new StrikethroughSpan(),0,spannableString.length(),0);
                    Holder.campoTarefaTxt.setText(spannableString);

                    moveItem(Holder.getAdapterPosition(), lista.size()-1);

                    //BACK-END
                    try{
                        mydb.updateTarefaConcluidas(tarefa.getId(),Holder.campoTarefaConcluida.isChecked());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Holder.campoTarefaTxt.setText(tarefa.getTitle());
                    //BACK-END
                    try{
                        mydb.updateTarefaConcluidas(tarefa.getId(),Holder.campoTarefaConcluida.isChecked());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        Holder.campoTarefaTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                boolean isVoid = Holder.campoTarefaTxt.getText().toString().equals("");
                try{
                    if(!b && isVoid){
                        mydb.deleteTarefa(tarefa.getId());
                        lista.remove(Holder.getAdapterPosition());
                        notifyDataSetChanged();

                    }
                    if(!isVoid){
                        mydb.updateTarefa(tarefa.getId(), Holder.campoTarefaTxt.getText().toString().trim());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public ObjTarefa removeItem(int position) {
        ObjTarefa model = lista.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem( ObjTarefa model) {
        lista.add(model);
    }

    public void moveItem(int fromPosition, int toPosition) {
        ObjTarefa model = lista.remove(fromPosition);
        lista.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }






    public class MyViewHolder extends  RecyclerView.ViewHolder{
        CheckBox campoTarefaConcluida;
        EditText campoTarefaTxt;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            campoTarefaConcluida = itemView.findViewById(R.id.CampoTarefaConcluida);
            campoTarefaTxt= itemView.findViewById(R.id.CampoTarefaTxt);
        }

    }



}
