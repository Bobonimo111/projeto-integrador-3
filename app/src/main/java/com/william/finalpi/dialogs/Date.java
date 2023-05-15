package com.william.finalpi.dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

public class Date {
    private DatePickerDialog datePickerDialog;
    private Context context;
    private int year;
    private int month;
    private int day;
    private String fullDate;

    public Date(Context context){
        this.context = context;
    }
    public void dateDialog(){
        DatePickerDialog.OnDateSetListener DateListener;
        DateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int daySelected, int monthSelected, int yearSelected) {
                monthSelected = monthSelected + 1;
                fullDate = String.format("%s  %s  %s",daySelected,monthSelected,yearSelected);
                year = yearSelected;
                month = monthSelected;
                day = daySelected;

                Log.i("testes","day: "+daySelected);
                Log.i("testes","year: "+monthSelected);
                Log.i("testes","month: "+yearSelected);
                Log.i("testes","DateFormater: "+ getFullDate());
                Log.i("testes","datePicker: "+datePicker);
            }
        };



        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this.context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,DateListener,year,month,day);




    }


    public void Show(){
        dateDialog();
        getDatePickerDialog().show();
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getFullDate() {
        return fullDate;
    }

    public DatePickerDialog getDatePickerDialog() {
        return datePickerDialog;
    }
}
