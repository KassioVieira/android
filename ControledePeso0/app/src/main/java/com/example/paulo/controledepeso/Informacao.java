package com.example.paulo.controledepeso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by paulo on 08/05/2017.
 */

public class Informacao extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.informacao);
    }

    public void Volta (View view) {
        switch (view.getId()) {
            case R.id.Retonas:
                super.onBackPressed();
                break;

        }
    }

}
