package com.example.paulo.controledepeso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by GILZACK on 16/05/2017.
 */

public class BancoDeDadosAjudante extends SQLiteOpenHelper {

    private static final String BANCO_DE_DADOS = "BD_CPESO";
    private static int VERSAO = 1;

    public static class TABELA_PERFIL {

        //Nome da Tabela (Entidade)
        public static final String TABELA = "perfil";

        //Nome das colunas da Tabela
        public static final String _ID       = "_id";
        public static final String NOME      = "nome";
        public static final String SENHA     = "senha";
        public static final String IDADE     = "idade";
        public static final String SEXO      = "sexo";
        public static final String PESO      = "peso";
        public static final String ALTURA    = "altura";
        public static final String VALOR_IMC = "valor_imc";
        public static final String META      = "meta";
        public static final String PESO_ADQ  = "peso_adq";
        public static final String PESO_CNQ  = "peso_cnq";
        public static final String NOVO_PESO  = "novo_peso";
        public static final String NOVO_IMC  = "novo_imc";

        // Vetor de colunas da Tabela (Atributos)
        public static final String[] COLUNAS = new String[]{_ID, NOME, SENHA, SEXO, IDADE, PESO,
                ALTURA, VALOR_IMC, META, PESO_ADQ, PESO_CNQ, NOVO_PESO, NOVO_IMC};

    }

    public static class TABELA_ALIMENTACAO {

        //Nome da Tabela (Entidade)
        public static final String TABELA = "alimentacao";

        //Nome das colunas da Tabela
        public static final String _ID       = "_id";
        public static final String _ID_PERFIL       = "_id_perfil";
        public static final String DATA       = "data";
        public static final String HOTA       = "hora";
        public static final String METAALIMENTACAO = "metaalimentacao";
        public static final String ALIMENTO1  = "alimento1";
        public static final String ALIMENTO2  = "alimento2";
        public static final String ALIMENTO3  = "alimento3";
        public static final String ALIMENTO4  = "alimento4";
        public static final String METAGRAMA1 = "metagrama1";
        public static final String METAGRAMA2 = "metagrama2";
        public static final String METAGRAMA3 = "metagrama3";
        public static final String METAGRAMA4 = "metagrama4";
        public static final String CALORIAALI = "caloriaali";
        public static final String METABEBIDA = "metabebida";
        public static final String BEBIDA1 = "bebida1";
        public static final String BEBIDA2 = "bebida2";
        public static final String BEBIDA3 = "bebida3";
        public static final String BEBIDA4 = "bebida4";
        public static final String METAML1 = "metaml1";
        public static final String METAML2 = "metaml2";
        public static final String METAML3 = "metaml3";
        public static final String METAML4 = "metaml4";
        public static final String CALORIABEB = "caloriabeb";


        // Vetor de colunas da Tabela (Atributos)
        public static final String[] COLUNAS = new String[]{_ID, _ID_PERFIL, DATA, HOTA, METAALIMENTACAO, ALIMENTO1, ALIMENTO2,
                ALIMENTO3, ALIMENTO4, METAGRAMA1, METAGRAMA2, METAGRAMA3, METAGRAMA4, CALORIAALI, METABEBIDA,
                BEBIDA1, BEBIDA2, BEBIDA3, BEBIDA4, METAML1, METAML2, METAML3, METAML4, CALORIABEB};

    }
    // Construtor da Classe para criação BD
    public BancoDeDadosAjudante(Context context) {
        super(context, BANCO_DE_DADOS, null, VERSAO);
    }

    // Método de criação do BD (tabelas e inclusão de dados iniciais)
    @Override
    public void onCreate(SQLiteDatabase db) {

        /*
         * OBS.:
         *   Todas as colunas que são chaves primárias possuem o nome de _id.
         *   Esta é uma convenção utilizada no Android.
         *
         * */

        // Criação da TABELA PERFIL

        db.execSQL("CREATE TABLE perfil (_id INTEGER PRIMARY KEY, nome TEXT, senha TEXT, sexo TEXT, " +
                                         "idade TEXT, peso DOUBLE, altura DOUBLE, valor_imc DOUBLE, meta TEXT," +
                                        " peso_adq DOUBLE, peso_cnq DOUBLE, novo_peso DOUBLE, novo_imc DOUBLE);");

        db.execSQL("CREATE TABLE alimentacao (_id INTEGER PRIMARY KEY, _id_perfil INTEGER, data integer, hora integer, " +
                "metaalimentacao, TEXT, alimento1 TEXT, alimento2 TEXT, alimento3 TEXT, alimento4 TEXT," +
                "metagrama1 TEXT, metagrama2 TEXT, metagrama3 TEXT, metagrama4 TEXT, caloriaali DOUBLE," +
                "metabebida TEXT, bebida1 TEXT, bebida2 TEXT, bebida3 TEXT, bebida4 TEXT," +
                "metaml1 TEXT, metaml2 TEXT, metaml3 TEXT, metaml4 TEXT, caloriabeb DOUBLE" +
                " );");
    }

    // Método para descrever as regras de atualização do BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        /*   Para caso de uma nova versão do app em que seja incluso a possibilidade
         * de adicionar o nome da pessoa que realizou o calculo do IMC;         *
         * */
        db.execSQL("ALTER TABLE perfil ADD COLUMN nome TEXT");
    }
}




