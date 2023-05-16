package com.william.finalpi.screams;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.william.finalpi.R;
import com.william.finalpi.dialogs.Date;

import java.util.Calendar;

public class AddList extends AppCompatActivity {
    Button buttonDate;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        setFindViewById();
        buttonDate.setOnClickListener(clickToOpenDate);

    }

    private void setFindViewById(){
        buttonDate = findViewById(R.id.buttonDate);
    }
    private View.OnClickListener clickToOpenDate =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("testes","butão date clicado");
            dateDialog();
            datePickerDialog.show();
        }
    };
    public void dateDialog(){
        DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int daySelected, int monthSelected, int yearSelected) {
                String fullDate;
                int year,month,day;
                monthSelected = monthSelected + 1;
                fullDate = String.format("%s  %s  %s",daySelected,monthSelected,yearSelected);
                year = yearSelected;
                month = monthSelected;
                day = daySelected;

                Log.i("testes","day: "+daySelected);
                Log.i("testes","year: "+monthSelected);
                Log.i("testes","month: "+yearSelected);
                Log.i("testes","DateFormater: "+ fullDate);
                Log.i("testes","datePicker: "+datePicker);
                buttonDate.setText(fullDate);
            }
        };

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        this.datePickerDialog = new DatePickerDialog(AddList.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,DateListener,year,month,day);

    }
}