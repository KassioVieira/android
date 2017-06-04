package com.example.paulo.controledepeso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by paulo on 23/05/2017.
 */

public class Cas_Info_Alimentacao extends Activity {

    public static final String EXTRA_ID = "Cas_Info_Alimentacao.EXTRA_ID";
    public static final String EXTRA_PERFIL = "Cas_Info_Alimentacao.EXTRA_PERFIL";
    public static final String EXTRA_SENHA = "Cas_Info_Alimentacao.EXTRA_SENHA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cas_info_alimentacao);

    }

    public void Telas(View view) {
        Bundle extras = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.catastraalimentacao:
                Intent intent = new Intent(this, Alimentacao.class);
                intent.putExtra(Alimentacao.EXTRA_ID, extras.getString(EXTRA_ID));
                intent.putExtra(Alimentacao.EXTRA_PERFIL, extras.getString(EXTRA_PERFIL));
                intent.putExtra(Alimentacao.EXTRA_SENHA, extras.getString(EXTRA_SENHA));
                startActivity(intent);
                break;
            case R.id.inforelatorio:
                startActivity(new Intent(this, Lista.class));
                break;
            case R.id.Volta:
                super.onBackPressed();
                break;
        }
    }

    }

