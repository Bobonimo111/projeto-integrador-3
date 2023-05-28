package com.william.finalpi.screams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.william.finalpi.R;
import com.william.finalpi.bd.MyDataBaseHelper;
import com.william.finalpi.objetos.ObjLista;
import com.william.finalpi.personAdpaters.AdapterListas;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewListas;
    Button buttonCreateListScrean;
    List<ObjLista> listaLista = new ArrayList<>();
    AdapterListas adapater ;


    MyDataBaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setFindViewById();
        setRecyclerViewListas();
        selectAllLists();
        //Test_AddLista();

        buttonCreateListScrean.setOnClickListener(toCreateNewList);






    }



    private void setFindViewById(){
        recyclerViewListas = findViewById(R.id.recyclerViewListas);
        buttonCreateListScrean = findViewById(R.id.buttonCreateListScrean);

    }
    private void setRecyclerViewListas(){
        adapater = new AdapterListas(this,listaLista);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());

        recyclerViewListas.setAdapter(adapater);

        recyclerViewListas.setLayoutManager(layoutManager);
    }




    private void selectAllLists(){
        mydb = new MyDataBaseHelper(this);
        Cursor cursor = mydb.getDateListas();
        try {
            while (cursor.moveToNext()){
                ObjLista newLista;
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dateEnd = cursor.getString(2);
                newLista = new ObjLista(id,name,dateEnd);
                listaLista.add(newLista);
            }

        }catch (Exception e){
            Log.e("testes","read dataBase "+e);
        }
    }



    private View.OnClickListener toCreateNewList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,AddList.class);
            startActivity(intent);
        }
    };
//    private View.OnClickListener VisibleMenu = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if(floatingActionButton.getVisibility() == (View.INVISIBLE)){
//                floatingActionButton.setVisibility(View.VISIBLE);
//            }else{
//                floatingActionButton.setVisibility(View.INVISIBLE);
//            }
//        }
//    };

    private void Test_AddLista(){
        ObjLista objLista = new ObjLista(1,"willim", "wilim", "willw");
        this.listaLista.add(objLista);
        objLista = new ObjLista(2,"Daniel", "wilim", "willw");
        this.listaLista.add(objLista);
    }


}