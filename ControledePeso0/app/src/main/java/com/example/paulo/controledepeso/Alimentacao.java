package com.example.paulo.controledepeso;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by paulo on 13/05/2017.
 */

public class Alimentacao extends Activity {

    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9, spinner10;
    Button Clear;
    EditText nome, data, hora, caloriaalimentacao, caloriabebida, alimentacao1, alimentacao2, alimentacao3, alimentacao4, bebida1, bebida2, bebida3, bebida4;
    double c_alimentacao, c_bebidas;
    private long id_novo_item;

    public static final String EXTRA_ID = "Alimentacao.EXTRA_ID";
    public static final String EXTRA_PERFIL = "Alimentacao.EXTRA_PERFIL";
    public static final String EXTRA_SENHA = "Alimentacao.EXTRA_SENHA";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    // OU
    SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");

    Date datahora = new Date();

    Calendar cal = Calendar.getInstance();
    Date data_atual = cal.getTime();

    String data_completa = dateFormat.format(data_atual);

    String hora_atual = dateFormat_hora.format(data_atual);

    private BD_CPESO bd_CPESO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alimentacao);

        bd_CPESO = new BD_CPESO(this);

        Bundle extras = getIntent().getExtras();

        Toast.makeText(this, extras.getInt(EXTRA_ID,0)+' '+extras.getString(EXTRA_PERFIL)+' '+extras.getString(EXTRA_SENHA), Toast.LENGTH_SHORT).show();

        bd_CPESO.setDb(Constantes.LEITURA_BD);

        String[] args = new String[]{extras.getString(EXTRA_PERFIL)};
        String queryOne = "SELECT * FROM perfil where nome = ? ";

        Cursor cursor = bd_CPESO.getDb().rawQuery(queryOne, args);

        nome = (EditText) findViewById(R.id.Nome);
        data = (EditText) findViewById(R.id.Data);
        hora = (EditText) findViewById(R.id.Hora);
        alimentacao1 = (EditText) findViewById(R.id.Alimentacao1);
        alimentacao2 = (EditText) findViewById(R.id.Alimentacao2);
        alimentacao3 = (EditText) findViewById(R.id.Alimentacao3);
        alimentacao4 = (EditText) findViewById(R.id.Alimentacao4);
        bebida1 = (EditText) findViewById(R.id.Bebida1);
        bebida2 = (EditText) findViewById(R.id.Bebida2);
        bebida3 = (EditText) findViewById(R.id.Bebida3);
        bebida4 = (EditText) findViewById(R.id.Bebida4);
        spinner1 = (Spinner) findViewById(R.id.MetaAlimentacao);
        spinner2 = (Spinner) findViewById(R.id.MetaGrama1);
        spinner3 = (Spinner) findViewById(R.id.MetaGrama2);
        spinner4 = (Spinner) findViewById(R.id.MetaGrama3);
        spinner5 = (Spinner) findViewById(R.id.MetaGrama4);
        spinner6 = (Spinner) findViewById(R.id.MetaBebida);
        spinner7 = (Spinner) findViewById(R.id.MetaML1);
        spinner8 = (Spinner) findViewById(R.id.MetaML2);
        spinner9 = (Spinner) findViewById(R.id.MetaML3);
        spinner10 = (Spinner) findViewById(R.id.MetaML4);
        caloriaalimentacao = (EditText) findViewById(R.id.Caloria);
        caloriabebida =(EditText) findViewById(R.id.Bebida);

        Clear = (Button) findViewById(R.id.Limpar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.MetaAlimentacao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaGrama, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaGrama, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaGrama, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner4.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaGrama, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner5.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaBebida, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner6.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaML, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner7.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaML, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner8.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaML, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner9.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.MetaML, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner10.setAdapter(adapter);

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alimentacao1.setText("");
                alimentacao2.setText("");
                alimentacao3.setText("");
                alimentacao4.setText("");

                bebida1.setText("");
                bebida2.setText("");
                bebida3.setText("");
                bebida4.setText("");

                spinner1.setAdapter(null);
                spinner2.setAdapter(null);
                spinner3.setAdapter(null);
                spinner4.setAdapter(null);
                spinner5.setAdapter(null);
                spinner6.setAdapter(null);
                spinner7.setAdapter(null);
                spinner8.setAdapter(null);
                spinner9.setAdapter(null);
                spinner10.setAdapter(null);

            }
        });
        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Localizei", Toast.LENGTH_SHORT).show();
            data.setText(data_completa);
            data.setEnabled(false);
            hora.setText(hora_atual);
            hora.setEnabled(false);
            nome.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.NOME)));
            nome.setEnabled(false);
        } else {
            Toast.makeText(this, "Não Localizei", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this, Abertura.class));
        }

    }

    public void Salvar(View view) {
        Bundle extras = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.salva:
                add_item();
//                startActivity(new Intent(this, Cas_Info_Alimentacao.class));
                break;
            case R.id.Retonas:
//                Intent intent2 = new Intent(this, Cas_Info_Alimentacao.class);
////                intent2.putExtra(Cas_Info_Alimentacao.EXTRA_ID, extras.getString(EXTRA_ID));
////                intent2.putExtra(Cas_Info_Alimentacao.EXTRA_PERFIL, extras.getString(EXTRA_PERFIL));
////                intent2.putExtra(Cas_Info_Alimentacao.EXTRA_SENHA, extras.getString(EXTRA_SENHA));
//                startActivity(new Intent(this, Cas_Info_Alimentacao.class));
                super.onBackPressed();
                break;

        }
    }
    private void add_item() {

        if (c_alimentacao == Double.POSITIVE_INFINITY || Double.isNaN(c_alimentacao)) {
            Toast.makeText(this, getString(R.string.e_divisao_por_0), Toast.LENGTH_SHORT).show();
        }  else
        if (c_alimentacao == Double.POSITIVE_INFINITY || Double.isNaN(c_alimentacao)) {
            Toast.makeText(this, getString(R.string.e_divisao_por_0), Toast.LENGTH_SHORT).show();
        }else{
            novo_item();
        }

    }

    private void novo_item() {

        novoRegistro();

        if (id_novo_item != -1) {
            Toast.makeText(this, getString(R.string.registro_salvo) + "\n +ID: " +
                    String.valueOf(id_novo_item), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, getString(R.string.erro_salvar_registro), Toast.LENGTH_SHORT).show();
        }

    }

    // Novo registro no Banco de Dados
    public void novoRegistro() {

        // Permite a realiza??o de opera??es de escrita nos banco de dados - BD
        bd_CPESO.setDb(Constantes.ESCRITA_BD);


        /***
         * ContentValues: usado para organizar o conjunto de dados no formato chave-valor,
         *                no qual a chave representa a coluna do banco de dados e o valor o dado
         *                a ser armazenado.
         * */

        Bundle extras = getIntent().getExtras();

        ContentValues values = new ContentValues();
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO._ID_PERFIL, extras.getString(EXTRA_ID));
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.DATA, data.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.HOTA, hora.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAALIMENTACAO, (String)spinner1.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.ALIMENTO1, alimentacao1.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.ALIMENTO2, alimentacao2.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.ALIMENTO3, alimentacao3.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.ALIMENTO4, alimentacao4.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METABEBIDA, (String)spinner6.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAGRAMA1, (String)spinner2.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAGRAMA2, (String)spinner3.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAGRAMA3, (String)spinner4.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAGRAMA4, (String)spinner5.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.BEBIDA1, bebida1.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.BEBIDA2, bebida2.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.BEBIDA3, bebida3.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.BEBIDA4, bebida4.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METABEBIDA, (String)spinner6.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAML1, (String)spinner7.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAML2, (String)spinner8.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAML3, (String)spinner9.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.METAML4, (String)spinner10.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.CALORIAALI, Double.parseDouble(caloriaalimentacao.getText().toString()));
        values.put(BancoDeDadosAjudante.TABELA_ALIMENTACAO.CALORIABEB, Double.parseDouble(caloriabebida.getText().toString()));

        /*
         * Método insert: utilizado para inserir dados no BD.
         *      Ele recebe três par?metros: nome da tabela,
         *                                  nome da chaves (colunas), e
         *                                  ContentValues.
         *      Retorna: O identificador do resgitro salvo (id) e -1 se ocorreu um erro.
         * */
        id_novo_item = bd_CPESO.getDb().insert(BancoDeDadosAjudante.TABELA_ALIMENTACAO.TABELA, null, values);

        if (id_novo_item > 0) {
            Toast.makeText(this, String.valueOf(id_novo_item), Toast.LENGTH_SHORT).show();
        }
    }

}
