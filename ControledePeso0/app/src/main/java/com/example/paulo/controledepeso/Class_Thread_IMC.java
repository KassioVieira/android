package com.example.paulo.controledepeso;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by paulo on 30/05/2017.
 */

public class Class_Thread_IMC implements Runnable {

    private EditText peso, altura, imc;

    public Class_Thread_IMC (EditText peso, EditText altura, EditText imc) {

        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
    }


    @Override
    public void run() {
        String teste = peso.getText().toString();
        String teste2 = altura.getText().toString();
        boolean te = altura.getText().toString().isEmpty();

        if (!this.altura.getText().toString().isEmpty()) {

            double resultado = Double.parseDouble(peso.getText().toString()) /
                    (Math.pow( Double.parseDouble(altura.getText().toString()), 2));
            imc.setText(String.valueOf(resultado));
            Log.i("TESTE",""+resultado);
        }
    }
}
