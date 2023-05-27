package com.william.finalpi.screams;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.william.finalpi.R;
import com.william.finalpi.bd.MyDataBaseHelper;
import com.william.finalpi.objetos.ObjTarefa;
import com.william.finalpi.personAdpaters.AdapterTarefa;

import java.util.ArrayList;
import java.util.List;

public class Tarefas extends AppCompatActivity {
    private MyDataBaseHelper mydb;
    private int lista_id;
    private String lista_name;
    private List<ObjTarefa> listaTarefas = new ArrayList<>();
    private TextView textViewTitle;
    private EditText editTextTextAdd;
    private RecyclerView recyclerView;
    private Button buttonAdd;
    private AdapterTarefa adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        //Funções para simplificar o codigo princial
        findViewByIds();
        setInteractions();
        getOuterActiviesData();
        //---------------------------------------------------
        //definir o titulo da lista
        textViewTitle.setText(this.lista_name);
        //Se o retorno for igual a -1 ocorreu um erro no envio do ID

        if (lista_id != -1){
            mydb =new MyDataBaseHelper(this);
            adapter = new AdapterTarefa(this,this.listaTarefas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
            selectAllLists(lista_id);
        }else{

            this.textViewTitle.setText("Ocorreu um erro, verificar com o desenvolvedor: "+ lista_id);
        }





    }

    private void setInteractions() {
        buttonAdd.setOnClickListener(OnClickAdd);
    }
    private void getOuterActiviesData(){

        this.lista_id = getIntent().getIntExtra("lista_id",-1);
        this.lista_name = getIntent().getStringExtra("lista_name");
    }
    private void findViewByIds (){
        this.textViewTitle = findViewById(R.id.textViewtTitle);
        this.editTextTextAdd = findViewById(R.id.editTextTextAdd);
        this.buttonAdd = findViewById(R.id.buttonAdd);
        this.recyclerView = findViewById(R.id.recyclerView);
    }
    private void selectAllLists(int fk_id){
        mydb = new MyDataBaseHelper(this);
        Cursor cursor = mydb.getDateTarefasByListas(fk_id);
        try {
            while (cursor.moveToNext()){
                ObjTarefa newTarefa;
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int finalizado = cursor.getInt(2);
                int lista_fk = cursor.getInt(3);
                newTarefa = new ObjTarefa(id,
                        name,
                        (finalizado == 1 ? true:false),
                        lista_fk);
                listaTarefas.add(newTarefa);

            }

        }catch (Exception e){
            Log.e("testes","read Tarefas sdataBase "+e);
        }
    }
    private View.OnClickListener OnClickAdd = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!editTextTextAdd.getText().toString().equals("")){
                ObjTarefa tarefa = new ObjTarefa(editTextTextAdd.getText().toString(),false, lista_id);
                mydb.addTarefa(tarefa);
                adapter.addItem(tarefa);
                adapter.notifyDataSetChanged();

            }else{
                Toast.makeText(Tarefas.this,"Campo vazio",Toast.LENGTH_SHORT).show();
            }
        }
    };

}