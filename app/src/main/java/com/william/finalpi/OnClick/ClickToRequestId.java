package com.william.finalpi.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.william.finalpi.screams.Tarefas;

public class ClickToRequestId implements View.OnClickListener {

    private int id;
    private String name;
    private Context context;
    public ClickToRequestId(String name,int id, Context context) {
        this.name = name;
        this.id = id;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Log.i("idTest",""+this.id);
        Intent i = new Intent(context, Tarefas.class);
        i.putExtra("lista_name",this.name);
        i.putExtra("lista_id",this.id);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

}
