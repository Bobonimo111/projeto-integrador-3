package com.william.finalpi.personAdpaters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.william.finalpi.OnClick.ClickToRequestId;
import com.william.finalpi.R;
import com.william.finalpi.bd.MyDataBaseHelper;
import com.william.finalpi.objetos.ObjLista;

import java.util.List;

public class AdapterListas extends RecyclerView.Adapter<AdapterListas.MyViewHolder>{
    private Context context;

    private List<ObjLista> listaListas;
    private MyDataBaseHelper myDb;

    private View.OnClickListener VisualizarLista = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Chamar proxima tela, e enviar o id da lista que foi clickada
            Log.i("Click","Lista clicada");
        }

    };

    public AdapterListas(Context context, List<ObjLista> listaListas) {
        myDb = new MyDataBaseHelper(context);
        this.context = context;
        this.listaListas = listaListas;
    }

    @NonNull
    @Override
    //Setar qual o layout personalizado utilizar
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_lista, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    //Quais ações seram tomadas por, item, como adicionar listeners entre outros.
    public void onBindViewHolder(@NonNull MyViewHolder Holder, int position) {
        ObjLista lista = listaListas.get(position);
        Holder.buttonListaName.setText(lista.getName());
        Holder.buttonListaName.setOnClickListener(new ClickToRequestId(lista.getName(),lista.getId(),this.context));
        Holder.imageViewIcon.setImageResource(R.drawable.icons8);
        Holder.buttonListaName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("" + lista.getId());
                builder.setItems(new String[]{"Editar","Deletar"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                editarLista(Holder.getPosition());
                                break;
                            case 1:
                                deleteLista(Holder.getPosition());
                                break;

                        }

                    }
                });
                builder.create().show();
                return true;
            }
        });

    }

    public void editarLista(int position){
        LayoutInflater inflater = new LayoutInflater(LayoutInflater.from(context), context) {
            @Override
            public LayoutInflater cloneInContext(Context newContext) {
                return null;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View v = inflater.inflate(R.layout.dialog_edit_lista,null);
        EditText editTextTxt = v.findViewById(R.id.editTextTxt);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String listaNome = editTextTxt.getText().toString().trim();
                if(!listaNome.equals("")){
                    Log.e("testes","Lista modificada");
                    updateItemName(position,listaNome);
                    myDb.uptdateLista(listaListas.get(position).getId(),listaNome);
                }else{
                    Toast.makeText(context, "Valor invalido", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }

            }
        });
        builder.setView(v);
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void deleteLista(int postion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("deletar lista " + listaListas.get(postion).getName());
        builder.setMessage("Você ira perder todos os itens salvos nessa lista \n tem certeza ?");
        builder.setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDb.deleteLista(listaListas.get(postion).getId());
                myDb.deleteTarefasOfList(listaListas.get(postion).getId());
                deleteItem(postion);
            }
        });
        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
         builder.create().show();


    }





    @Override
    public int getItemCount() {
        return listaListas.size();
    }
    public void add(int postion,ObjLista item){
        listaListas.add(item);


    }
    public void updateItemName(int postion,String name){
        ObjLista lista = listaListas.get(postion);
        lista.setName(name);
        listaListas.set(postion,lista);
        notifyDataSetChanged();
    }
    public void deleteItem(int positon){
        listaListas.remove(positon);
        notifyDataSetChanged();
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
