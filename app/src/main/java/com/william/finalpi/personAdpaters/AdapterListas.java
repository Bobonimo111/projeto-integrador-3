package com.william.finalpi.personAdpaters;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.william.finalpi.OnClick.ClickToRequestId;
import com.william.finalpi.R;
import com.william.finalpi.bd.MyDataBaseHelper;
import com.william.finalpi.objetos.ObjLista;

import java.util.List;
import java.util.zip.Inflater;

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

                                break;

                        }

                    }
                });
                builder.create().show();
                return true;
            }
        });

    }

    public void editarLista(int postion){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = new LayoutInflater(context) {
            @Override
            public LayoutInflater cloneInContext(Context context) {

                return null;

            }
        };
        View v = inflater.inflate(R.layout.dialog_edit_lista,null);

        builder.setView(R.layout.dialog_edit_lista);
        builder.setTitle(listaListas.get(postion).getName());



        builder.setPositiveButton(R.string.edit, new DialogInterface.OnClickListener()  {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText e =  v.findViewById(R.id.editTextTxt);
                Toast.makeText(context,(""+ e.getText()),Toast.LENGTH_SHORT).show();
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
