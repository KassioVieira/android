package com.example.paulo.controledepeso;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by paulo on 09/05/2017.
 */

public class Abertura extends Activity implements AdapterView.OnItemClickListener {

    // Atributos ----------------------
    private ListView listView;
    private List<Map<String, Object>> lista;
    private EditText pesoEdit, alturaEdit, resultadoEdit;

    /***
     * OBS.:
     *      Estrutura de dados para manipulação do Banco de dados
     *          Atributos ->    ajudante ({@link BancoDeDadosAjudante});
     *                          bd ({@link SQLiteDatabase})
     */
    private BD_CPESO bd_CPESO;

    // Variaveis de construção dos itens da ListView
    private final String[] de = {Constantes.KEY_CHECKBOX, Constantes.KEY_IMG,
            Constantes.KEY_META, Constantes.KEY_META};
    //    private final int[] para = {R.id.checkbox_item, R.id.image_item, R.id.title_item,
//            R.id.result_item};
    private String msg;
    private double resultado;
    private long id_novo_item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.abertura);

//        Inicialização do Banco de Dados
        bd_CPESO = new BD_CPESO(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Fecha o Banco de Dados
        bd_CPESO.close();
    }

    public void Login (View view) {
        switch (view.getId()) {
            case R.id.Entrar:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.CriaPerfil:
                startActivity(new Intent(this, Cadastro.class));
                break;
        }
    }

    // Trata o evento de clicar em um item da ListView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        boolean check = (boolean) lista.get(position).get("checkBox");

        if (check) {
            lista.get(position).put("checkBox", false);
        } else {
            lista.get(position).put("checkBox", true);
        }
        listView.invalidateViews();
    }

    // Ler os registros no Banco de Dados
    public void lerRegistro() {

        bd_CPESO.setDb(Constantes.LEITURA_BD);
        Cursor cursor = bd_CPESO.getDb().query(BancoDeDadosAjudante.TABELA_PERFIL.TABELA,
                BancoDeDadosAjudante.TABELA_PERFIL.COLUNAS, null, null, null, null, null);

        // Move o cursor para o primeiro registro
        cursor.moveToFirst();

        lista = new ArrayList<Map<String, Object>>();
        CPESO cpeso = new CPESO();

        for (int i = 0; i < cursor.getCount(); i++) {

            cpeso.setCPESO(cursor);
            cpeso.createNewItem();
            lista.add(cpeso.getItem());
            // Move para linha da tabela do BD
            cursor.moveToNext();
        }
        // Finaliza a navegação usando o cursor (Finaliza o acesso a Tabela)
        cursor.close();
    }

}
