package com.example.paulo.controledepeso;

import android.database.Cursor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GILZACK on 16/05/2017.
 */

public class CPESO {
    private Long id;
    private double peso;
    private double altura;
    private double valor_imc;
    private double peso_adq;
    private double peso_cnq;
    private String nome;
    private String idade;
    private String senha;
    private String sexo;
    private String meta;

    // Item de viagem
    Map<String, Object> item;

    // Construtor
    public CPESO() {
    }

    public CPESO(Long id, double valor_imc, double peso, double altura, double peso_adq, double peso_cnq, String meta, String nome, String sexo, String idade) {
        this.id = id;
        this.peso = peso;
        this.altura = altura;
        this.valor_imc = valor_imc;
        this.peso_adq = peso_adq;
        this.peso_cnq = peso_cnq;
        this.nome = nome;
        this.meta = meta;
        this.sexo = sexo;
        this.idade = idade;
    }

    // Configura e cria um item para a estrutara Map
    public void createNewItem() {

        this.item = new HashMap<String, Object>();

        item.put(Constantes.KEY_ID, id);
        item.put(Constantes.KEY_CHECKBOX, true);
        item.put(Constantes.KEY_IMG, R.drawable.ic_assignment_black_36dp);
        item.put(Constantes.KEY_META, meta);
        item.put(Constantes.KEY_RESULTADO, String.format("%.2f", valor_imc));
    }

    // Acessa as Colunas da linha da Tabela do BD para recuperar os dados
    public void setCPESO(Cursor cursor) {

        /***
         *      cursor: estrutura de dados utilizada para navegar pela tabela do BD (especie
         *              de ponteiro).
         *      cursor.getColumnIndex:  método usado para retorna o índice da colunas passando
         *                              o nome da coluna como parâmetro.
         */
        this.id = cursor.getLong(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL._ID));
        this.valor_imc = cursor.getDouble(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.VALOR_IMC));
        this.meta = cursor.getString(cursor.getColumnIndex(BancoDeDadosAjudante.TABELA_PERFIL.META));
    }

    // Getter
    public Long getId() {
        return id;
    }

    public double getValor_imc() {
        return valor_imc;
    }

    public String getMeta() {
        return meta;
    }

    public Map<String, Object> getItem() {
        return item;
    }

    //Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setValor_imc(double valor_imc) {
        this.valor_imc = valor_imc;
    }

    public void setMeta(String categoria) {
        this.meta = categoria;
    }
}
