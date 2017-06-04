package com.example.paulo.controledepeso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by paulo on 13/05/2017.
 */

public class CalcularIMC extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calimc);


    }

    public void Salvar (View view) {
        switch (view.getId()) {
            case R.id.Salvar1:
                startActivity(new Intent(this, Inicio.class));
                break;
            case R.id.Menu:
                startActivity(new Intent(this, Inicio.class));
                break;

        }
    }
}
