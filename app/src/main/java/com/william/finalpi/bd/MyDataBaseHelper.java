package com.william.finalpi.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.william.finalpi.objetos.ObjLista;
import com.william.finalpi.objetos.ObjTarefa;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context context;

    public static final String DATABASE_NAME = "ListasTarefas.db";
    public static final int DATABASE_VERSION = 1;
    //Configs da lista de tarefas
    public static final String LISTAS_TABLE_NAME = "Listas";
    public static final String LISTAS_COLUMN_ID = "_id";
    public static final String LISTAS_COLUMN_NAME = "list_name";

    public static final String LISTAS_COLUMN_DATE_INIT = "date_init";
    public static final String LISTAS_COLUMN_DATE_END = "date_end";
    public static final  String LISTAS_COLUMN_QTD_TAREFAS = "qtd_tarefas";

    //Config da tabelas de tarefas
    public static final  String TAREFA_TABLE_NAME = "tarefas";
    public static final  String TAREFA_COLUMN_ID = "_id";
    public static final  String TAREFA_COLUMN_TITLE = "title";
    public static final  String TAREFA_COLUMN_CONCLUIDO = "FINALIZADO";
    public static final  String TAREFA_COLUMN_LISTA_FK = "LISTA_FK";







    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_listas =
                "CREATE TABLE " + LISTAS_TABLE_NAME +
                "("+LISTAS_COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                LISTAS_COLUMN_NAME +" TEXT,"+
                LISTAS_COLUMN_DATE_INIT + " TEXT, "+
                LISTAS_COLUMN_DATE_END + " TEXT, "+
                LISTAS_COLUMN_QTD_TAREFAS + " INTEGER"+
                        " );";
        String query_tarefas =
                "CREATE TABLE " + TAREFA_TABLE_NAME +
                "( "+ TAREFA_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                TAREFA_COLUMN_TITLE + " TEXT ," +
                        TAREFA_COLUMN_CONCLUIDO +" NUMERIC ," +
                TAREFA_COLUMN_LISTA_FK +" INTEGER " +");";
        db.execSQL(query_listas);
        db.execSQL(query_tarefas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + LISTAS_TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + TAREFA_TABLE_NAME );
        onCreate(db);
    }




    public void addLista(ObjLista lista){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(LISTAS_COLUMN_NAME,lista.getName());
        cv.put(LISTAS_COLUMN_DATE_END,lista.getDateEnd());
        cv.put(LISTAS_COLUMN_DATE_INIT,lista.getDateInit());
        cv.put(LISTAS_COLUMN_DATE_END,lista.getDateEnd());

        long result = db.insert(LISTAS_TABLE_NAME,null,cv);

        if (result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Sucesse",Toast.LENGTH_LONG).show();
        }

    }

    public Cursor getDateListas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+LISTAS_TABLE_NAME+";",null);
        return  cursor;
    }
    public long uptdateLista(int id, String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LISTAS_COLUMN_NAME,name);
        long update = db.update(LISTAS_TABLE_NAME,cv,LISTAS_COLUMN_ID + " = ?",new String[]{""+id});
        return update;

    }
    public long deleteLista(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(LISTAS_TABLE_NAME,LISTAS_COLUMN_ID + " = ?", new String[]{""+id});
        Log.d("MyDataBase","Resultado "+delete);
        return delete;
    }

    public long deleteTarefasOfList(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(TAREFA_TABLE_NAME, TAREFA_COLUMN_LISTA_FK + " = ?",new String[]{""+id});

        return delete;
    }
    public Cursor getDateTarefas() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TAREFA_TABLE_NAME+";",null);
        return  cursor;
    }

    public Cursor getDateTarefasByListas(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TAREFA_TABLE_NAME+" WHERE "+ TAREFA_COLUMN_LISTA_FK + " = " + id +" ;",null);
        return  cursor;
    }
    public void addTarefa(ObjTarefa obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TAREFA_COLUMN_TITLE,obj.getTitle());
        cv.put(TAREFA_COLUMN_CONCLUIDO,obj.isConcluida());
        cv.put(TAREFA_COLUMN_LISTA_FK,obj.getLista_fk_id());

        long result = db.insert(TAREFA_TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(context,"Faled",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Sucesse",Toast.LENGTH_LONG).show();
        }

    }
    public long updateTarefaConcluidas(int id,boolean concluido){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TAREFA_COLUMN_CONCLUIDO,concluido);

        long update = db.update(TAREFA_TABLE_NAME, cv, TAREFA_COLUMN_ID + " = ?", new String[]{""+id});

        return update;
    }
    public long updateTarefa(int id, String tarefa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TAREFA_COLUMN_TITLE,tarefa);

        long update = db.update(TAREFA_TABLE_NAME,cv,TAREFA_COLUMN_ID + " = ? ",new String[]{""+id});
        return update;
    }
    public long deleteTarefa(int id){
        SQLiteDatabase db = this.getWritableDatabase();


        long delete = db.delete(TAREFA_TABLE_NAME,TAREFA_COLUMN_ID + " = ?", new String[]{""+id});
        return delete;
    }
}
