package com.william.finalpi.screams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.william.finalpi.R;
import com.william.finalpi.objetos.ObjLista;
import com.william.finalpi.personAdpaters.AdapterListas;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewListas;
    Button buttonCreateListScrean;
    List<ObjLista> listaLista = new ArrayList<>();
    AdapterListas adapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFindViewById();
        setRecyclerViewListas();
        Test_AddLista();


        buttonCreateListScrean.setOnClickListener(toCreateNewList);


    }
    private void setFindViewById(){
        recyclerViewListas = findViewById(R.id.recyclerViewListas);
        buttonCreateListScrean = findViewById(R.id.buttonCreateListScrean);
    }
    private void setRecyclerViewListas(){
        adapater = new AdapterListas(this,listaLista);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getApplicationContext());

        recyclerViewListas.setAdapter(adapater);
        recyclerViewListas.setLayoutManager(layoutManager);
    }
    private void Test_AddLista(){
        ObjLista objLista = new ObjLista(1,"willim", "wilim", "willw");
        this.listaLista.add(objLista);
        objLista = new ObjLista(2,"Daniel", "wilim", "willw");
        this.listaLista.add(objLista);
    }

    private View.OnClickListener toCreateNewList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,AddList.class);
            startActivity(intent);
        }
    };
}