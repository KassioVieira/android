package com.example.paulo.controledepeso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by paulo on 06/05/2017.
 */

public class Inicio extends Activity {

    public static final String EXTRA_ID = "Inicio.EXTRA_ID";
    public static final String EXTRA_PERFIL = "Inicio.EXTRA_PERFIL";
    public static final String EXTRA_SENHA = "Inicio.EXTRA_SENHA";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inicio);

        Bundle extras = getIntent().getExtras();

        Toast.makeText(this, extras.getString(EXTRA_ID)+" - "+extras.getString(EXTRA_PERFIL)+" - "+extras.getString(EXTRA_SENHA), Toast.LENGTH_SHORT).show();

   }

    public void Telas (View view) {
        Bundle extras = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.Catastro:
                Intent intent = new Intent(this, Cadastro.class);
                intent.putExtra(Cadastro.EXTRA_ID, extras.getString(EXTRA_ID));
                intent.putExtra(Cadastro.EXTRA_PERFIL, extras.getString(EXTRA_PERFIL));
                intent.putExtra(Cadastro.EXTRA_SENHA, extras.getString(EXTRA_SENHA));
                intent.putExtra(Constantes.INST_PERFIL_ID, extras.getString(EXTRA_ID));
                startActivity(intent);
                break;
            case R.id.CalIMC:
                startActivity(new Intent(this, CalcularIMC.class));
                break;
            case R.id.Alimentacao:
                Intent intent2 = new Intent(this, Cas_Info_Alimentacao.class);
                intent2.putExtra(Cas_Info_Alimentacao.EXTRA_ID, extras.getInt(EXTRA_ID,0));
                intent2.putExtra(Cas_Info_Alimentacao.EXTRA_PERFIL, extras.getString(EXTRA_PERFIL));
                intent2.putExtra(Cas_Info_Alimentacao.EXTRA_SENHA, extras.getString(EXTRA_SENHA));
                startActivity(intent2);
//                startActivity(new Intent(this, Cas_Info_Alimentacao.class));
                break;
            case R.id.Informacao:
                startActivity(new Intent(this, Informacao.class));
                break;
        }
    }
}
