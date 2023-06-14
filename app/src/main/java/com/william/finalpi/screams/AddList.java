package com.william.finalpi.screams;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.william.finalpi.R;
import com.william.finalpi.bd.MyDataBaseHelper;
import com.william.finalpi.dialogs.Date;
import com.william.finalpi.objetos.ObjLista;

import java.util.Calendar;

public class AddList extends AppCompatActivity {
    MyDataBaseHelper mydb;
    ObjLista lista;

    private EditText editTextTextTitle;
    private Button
            buttonDate,
            buttonTime,
            buttonNovaLista;
    private String
            inputDate = "",
            inputTime = "";
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        setFindViewById();
        buttonDate.setOnClickListener(clickToOpenDate);
        buttonTime.setOnClickListener(clickToOpenTime);
        buttonNovaLista.setOnClickListener(clickToCreateNewList);
        mydb = new MyDataBaseHelper(this);


    }

    private void setFindViewById(){
        buttonDate = findViewById(R.id.buttonDate);
        buttonTime = findViewById(R.id.buttonTime);
        buttonNovaLista = findViewById(R.id.buttonNovaLista);
        editTextTextTitle = findViewById(R.id.editTextTextTitle);
    }
    private View.OnClickListener clickToOpenDate =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("testes","butão date clicado");
            dateDialog();
            datePickerDialog.show();
        }
    };
    private View.OnClickListener clickToOpenTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            timeDialog();
            timePickerDialog.show();
        }
    };
    private View.OnClickListener clickToCreateNewList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Checar se o title esta vazio, ou se o item ja existe;
            if(!editTextTextTitle.getText().toString().equals("")){
                confirmAddListDialog();
            }else{
                Toast.makeText(AddList.this , R.string.value_invalid,Toast.LENGTH_SHORT).show();
            }


        }
    };
    public void dateDialog(){
        DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int daySelected, int monthSelected, int yearSelected) {
                String fullDate;
                int year,month,day;
                monthSelected = monthSelected + 1;
                fullDate = String.format("%s - %s - %s",daySelected,monthSelected,yearSelected);
                year = yearSelected;
                month = monthSelected;
                day = daySelected;

                Log.i("testes","day: "+daySelected);
                Log.i("testes","year: "+monthSelected);
                Log.i("testes","month: "+yearSelected);
                Log.i("testes","DateFormater: "+ fullDate);
                Log.i("testes","datePicker: "+datePicker);
                buttonDate.setText(fullDate);
                inputDate = fullDate;
            }
        };


        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        this.datePickerDialog = new DatePickerDialog(AddList.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,DateListener,year,month,day);

    }
    private void timeDialog() {

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String editedTime = hour + " : " + minute;
                hour = hour;
                minute = minute;
                Log.i("testes","hour "+hour);
                Log.i("testes","minute "+minute);
                buttonTime.setText(hour + " : " + minute);
                inputTime = editedTime;
            }

        };

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR),
            minute = c.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(AddList.this,timeSetListener,hour,minute,true);

    }
    private void limparCampos(){
        //editTextTextTitle.setText("Title");
        buttonDate.setText(R.string.date_text);
        buttonTime.setText(R.string.hour_text);

    }
    private void confirmAddListDialog(){
        AlertDialog.Builder builder = new  AlertDialog.Builder(AddList.this);
        builder.setMessage(R.string.confirm_new_list);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("dialog","cancel clicado");
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Calendar c = Calendar.getInstance();
                c.getTime();
                if(!inputDate.equals("")){
                    if(!inputTime.equals("")){
                        lista = new ObjLista(0,editTextTextTitle.getText().toString().trim(),inputDate,inputTime);
                    }else{
                        lista = new ObjLista(0,editTextTextTitle.getText().toString().trim(),inputDate);
                    }
                }else{
                    lista = new ObjLista(0,editTextTextTitle.getText().toString().trim(),"");
                }
                Log.i("testes","adionar listas a lista");
                mydb.addLista(lista);
                startActivity(new Intent(AddList.this,MainActivity.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}