package com.example.paulo.controledepeso;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by paulo on 09/05/2017.
 */

public class Cadastro extends Activity {

    public static final String EXTRA_ID = "Cadastro.EXTRA_ID";
    public static final String EXTRA_PERFIL = "Cadastro.EXTRA_PERFIL";
    public static final String EXTRA_SENHA = "Cadastro.EXTRA_SENHA";

    protected static final int TIMER_RUNTIME = 10000;

    protected boolean mbActive;
    private android.util.Log log;
    private BD_CPESO bd_CPESO;
    private String msg;
    private double resultado;
    private long id_novo_item;
    private EditText nomeEdit, idadeEdit, pesoEdit, alturaEdit, senhaEdit, pesoatualEdit, pesoadquirirEdit, imcEdit;
    private Spinner metaEdit;
    RadioGroup rgSexo;
    private String id = null;
    private Button botaoSalvar;
    Thread runner = null;
    private Class_Thread_IMC thread_imc;
    int escolhido;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cadastro);

        Bundle extras = getIntent().getExtras();
//        Inicialização do Banco de Dados

        bd_CPESO = new BD_CPESO(this);

        nomeEdit = (EditText) findViewById(R.id.Nome);
        senhaEdit = (EditText) findViewById(R.id.Senha);
        idadeEdit = (EditText) findViewById(R.id.Data);
        pesoEdit = (EditText) findViewById(R.id.Peso);
        alturaEdit = (EditText) findViewById(R.id.Altura);
        imcEdit = (EditText) findViewById(R.id.IMC2);
        pesoatualEdit = (EditText) findViewById(R.id.PesoAtual);
        pesoadquirirEdit = (EditText) findViewById(R.id.MetaAdquirir);
        metaEdit = (Spinner) findViewById(R.id.Meta);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        final int posicao;


        alturaEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double altura = Double.parseDouble(alturaEdit.getText().toString());
                double peso = Double.parseDouble(pesoEdit.getText().toString());
                calculoIMC(altura,peso);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        metaEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    calculo(position);
                }else if(position == 1){
                    calculo(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pesoatualEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double peso2 = Double.parseDouble(pesoatualEdit.getText().toString());
                double peso = Double.parseDouble(pesoEdit.getText().toString());
                if (escolhido == 1){
                  calculoSoma(peso, peso2);
               }else{
                    calculoSubtracao(peso,peso2);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Meta, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        metaEdit.setAdapter(adapter);

        id = getIntent().getStringExtra(Constantes.INST_PERFIL_ID);

        Toast.makeText(this, " Identificação : " + id, Toast.LENGTH_SHORT).show();

        if (id != null) {
            botaoSalvar = (Button) findViewById(R.id.Salvar);
            botaoSalvar.setText("Atualizar Cadastro");
            carregaRegistro();
        }


    }

    private void calculoSoma(double peso, double pesoNovo) {
      double resultado = peso + pesoNovo;
        pesoadquirirEdit.setText(""+resultado);
    }

    private void calculoSubtracao(double peso, double pesoNovo) {
        double resultado = peso - pesoNovo;
        pesoadquirirEdit.setText(""+resultado);
    }

    public void calculoIMC(double altura, double peso) {
        double resultado = peso / (altura * altura);
        imcEdit.setText("" + resultado);
    }
    public  void calculo(int posicao) {
       escolhido = posicao;
    }

    public void Perfil(View view) {
        Bundle extras = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.Salvar:
                double peso = Double.parseDouble(pesoEdit.getText().toString());
                double altura2 = Double.parseDouble(alturaEdit.getText().toString());
                resultado = (peso / Math.pow(altura2, 2));
                add_item();
                Intent intent2 = new Intent(this, Cas_Info_Alimentacao.class);
                if (id != null) {
                    intent2.putExtra(Cas_Info_Alimentacao.EXTRA_ID, extras.getString(EXTRA_ID));
                    intent2.putExtra(Cas_Info_Alimentacao.EXTRA_PERFIL, extras.getString(EXTRA_PERFIL));
                    intent2.putExtra(Cas_Info_Alimentacao.EXTRA_SENHA, extras.getString(EXTRA_SENHA));
                } else {
                    intent2.putExtra(Cas_Info_Alimentacao.EXTRA_ID, id_novo_item);
                    intent2.putExtra(Cas_Info_Alimentacao.EXTRA_PERFIL, nomeEdit.getText().toString());
                    intent2.putExtra(Cas_Info_Alimentacao.EXTRA_SENHA, senhaEdit.getText().toString());
                }
                startActivity(intent2);
                break;
            case R.id.Volta:
                super.onBackPressed();
//                startActivity(new Intent(this, Abertura.class));
                break;
        }
    }

    private boolean verificalogin() {
        bd_CPESO.setDb(Constantes.LEITURA_BD);
//                +usuario.getText().toString()
        String[] args = new String[]{nomeEdit.getText().toString()};
        String queryOne = "SELECT * FROM perfil where nome = ?";

        Cursor cursor = bd_CPESO.getDb().rawQuery(queryOne, args);

        if (cursor.moveToFirst()) {
            String nome = cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.NOME));
            Toast.makeText(this, " Já existe esse Perfil! " + nome, Toast.LENGTH_SHORT).show();
            return (true);
        } else {
            return false;
        }
    }


    private void add_item() {

        if (resultado == Double.POSITIVE_INFINITY || Double.isNaN(resultado)) {
            Toast.makeText(this, getString(R.string.e_divisao_por_0), Toast.LENGTH_SHORT).show();
        } else if (resultado < 16) {
            msg = getString(R.string.mag_grave);
            novo_item();
        } else if (resultado < 17) {
            msg = getString(R.string.mag_moderada);
            novo_item();
        } else if (resultado < 18.5) {
            msg = getString(R.string.mag_leve);
            novo_item();
        } else if (resultado < 25) {
            msg = getString(R.string.peso_normal);
            novo_item();
        } else if (resultado < 30) {
            msg = getString(R.string.sobrepeso);
            novo_item();
        } else if (resultado < 35) {
            msg = getString(R.string.obesidade_grau_1);
            novo_item();
        } else if (resultado < 40) {
            msg = getString(R.string.obesidade_grau_2);
            novo_item();
        } else {
            msg = getString(R.string.obesidade_grau_3);
            novo_item();
        }
    }

    private void novo_item() {

        novoRegistro();

//            Map<String, Object> item = new HashMap<String, Object>();
//
//            item.put(Constantes.KEY_ID, id_novo_item);
//            item.put(Constantes.KEY_CHECKBOX, true);
//            item.put(Constantes.KEY_IMG, R.drawable.ic_assignment_black_36dp);
//            item.put(Constantes.KEY_CATEGORIA, msg);
//            item.put(Constantes.KEY_RESULTADO, String.format("%.2f", resultado));

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


        ContentValues values = new ContentValues();
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.NOME, nomeEdit.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.SENHA, senhaEdit.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.IDADE, idadeEdit.getText().toString());
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.PESO, Double.parseDouble(pesoEdit.getText().toString()));
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.ALTURA, Double.parseDouble(alturaEdit.getText().toString()));
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.VALOR_IMC, resultado);
        switch (rgSexo.getCheckedRadioButtonId()) {
            case R.id.Masculino:
                values.put(BancoDeDadosAjudante.TABELA_PERFIL.SEXO, "M");
                break;
            case R.id.Femenino:
                values.put(BancoDeDadosAjudante.TABELA_PERFIL.SEXO, "F");
                break;
        }
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.META, (String) metaEdit.getSelectedItem());
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.PESO_ADQ, Double.parseDouble(pesoatualEdit.getText().toString()));
        values.put(BancoDeDadosAjudante.TABELA_PERFIL.PESO_CNQ, Double.parseDouble(pesoadquirirEdit.getText().toString()));

        /*
         * Método insert: utilizado para inserir dados no BD.
         *      Ele recebe três par?metros: nome da tabela,
         *                                  nome da chaves (colunas), e
         *                                  ContentValues.
         *      Retorna: O identificador do resgitro salvo (id) e -1 se ocorreu um erro.
         * */
        if (id != null) {
            bd_CPESO.setDb(Constantes.ESCRITA_BD);
            id_novo_item = bd_CPESO.getDb().update(BancoDeDadosAjudante.TABELA_PERFIL.TABELA, values,
                    "_id = ?", new String[]{id});

            if (id_novo_item != -1) {
                Toast.makeText(this, "Registro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro, não foi possível atualizar o seu Registro!",
                        Toast.LENGTH_SHORT).show();
            }

        } else {
            id_novo_item = bd_CPESO.getDb().insert(BancoDeDadosAjudante.TABELA_PERFIL.TABELA, null, values);
            if (id_novo_item > 0) {
                Toast.makeText(this, String.valueOf(id_novo_item), Toast.LENGTH_SHORT).show();
            }
            if (id_novo_item != -1) {
                Toast.makeText(this, getString(R.string.registro_salvo) + "\n +ID: " +
                        String.valueOf(id_novo_item), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.erro_salvar_registro), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void carregaRegistro() {

        String cmdSQL;
        bd_CPESO.setDb(Constantes.LEITURA_BD);

        cmdSQL = "SELECT * FROM " + BancoDeDadosAjudante.TABELA_PERFIL.TABELA + " WHERE _id = ?";

        Cursor cursor = bd_CPESO.getDb().rawQuery(cmdSQL, new String[]{id});
        cursor.moveToFirst();

        nomeEdit.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.NOME)));
        senhaEdit.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.SENHA)));
        idadeEdit.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.IDADE)));
        pesoEdit.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.PESO)));
        alturaEdit.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.ALTURA)));
//        rgSexo.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.SEXO)));
//        metaEdit.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.META)));
        pesoatualEdit.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.PESO_ADQ)));
        pesoadquirirEdit.setText(cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.PESO_CNQ)));

    }

}
