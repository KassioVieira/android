package com.example.paulo.controledepeso;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by paulo on 09/05/2017.
 */

public class Login extends Activity {

    private EditText usuario, senha;
    private BD_CPESO bd_CPESO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.Usuario);
        senha = (EditText) findViewById(R.id.Senha);

        bd_CPESO = new BD_CPESO(this);
    }

    public void Inicio(View view) {

        switch (view.getId()) {
            case R.id.Proseguir:

                bd_CPESO.setDb(Constantes.LEITURA_BD);
                String[] args = new String[]{usuario.getText().toString(), senha.getText().toString()};
                String queryOne = "SELECT * FROM perfil where nome = ? and senha = ?";

                Cursor cursor = bd_CPESO.getDb().rawQuery(queryOne, args);

                if (cursor.moveToFirst()) {
                    String nome = cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.NOME));
                    String acesso = cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.SENHA));
                    String id = cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL._ID));
                    startActivity(new Intent(this, Inicio.class));
                    Intent intent = new Intent(this, Inicio.class);
                    intent.putExtra(Inicio.EXTRA_ID, id);
                    intent.putExtra(Inicio.EXTRA_PERFIL, nome);
                    intent.putExtra(Inicio.EXTRA_SENHA, acesso);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Não Localizei", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Abertura.class));
                }
//                bd_CPESO.getDb().query(BancoDeDadosAjudante.TABELA_PERFIL.COLUNAS, "_id = ?", where);
                break;

            case R.id.Retonas:
                startActivity(new Intent(this, Abertura.class));
                break;
        }
//        String where[] = new String[]{String.valueOf(lista.get(i).get(Constantes.KEY_ID))};
//        Remove instancias de Gastos e Viagem do BD (tabela)
//        bd_IMC.getDb().delete(BancoDeDadosAjudante.BD_IMC.TABELA, "_id = ?", where);
//        switch (view.getId()) {
//            case R.id.Proseguir:
//                startActivity(new Intent(this, Inicio.class));
//                break;
//            case R.id.Retonas:
//                startActivity(new Intent(this, Abertura.class));
//                break;
//        }
    }
}