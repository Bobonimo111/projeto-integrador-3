package com.william.finalpi.screams;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.william.finalpi.R;
import com.william.finalpi.bd.MyDataBaseHelper;
import com.william.finalpi.objetos.ObjTarefa;

import java.util.ArrayList;
import java.util.List;

public class Tarefas extends AppCompatActivity {
    MyDataBaseHelper mydb;
    int id_lista;
    TextView textViewTitle;
    EditText editTextTextAdd;
    Button buttonAdd;
    List<ObjTarefa> listaTarefas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        findViewByIds();

        id_lista = getIntent().getIntExtra("id_lista",-1);
        if (id_lista != -1){

        }else{
            this.textViewTitle.setText("Ocorreu um erro, verificar com o desenvolvedor: "+id_lista);
        }


    }
    private void findViewByIds (){
        this.textViewTitle = findViewById(R.id.textViewtTitle);
        this.editTextTextAdd = findViewById(R.id.editTextTextAdd);
        this.buttonAdd = findViewById(R.id.buttonAdd);
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
}