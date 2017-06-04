package com.example.paulo.controledepeso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by GILZACK on 16/05/2017.
 */

public class BD_CPESO {

    private BancoDeDadosAjudante ajudante;
    private SQLiteDatabase db;

    public BD_CPESO(Context context) {
        ajudante = new BancoDeDadosAjudante(context);
    }

    public void setDb(int tipoDeAcesso) {

        if (tipoDeAcesso == Constantes.ESCRITA_BD) {
            db = ajudante.getWritableDatabase();
        } else if (tipoDeAcesso == Constantes.LEITURA_BD) {
            db = ajudante.getReadableDatabase();
        }
    }

    // Fecha o Banco de Dados
    public void close() {
        ajudante.close();
    }

    // Deleta a Tabela inteira do Banco de Dados
    public void apagaTodosOsRegistros () {
        db.delete(BancoDeDadosAjudante.TABELA_PERFIL.TABELA, null, null);
    }

    // Retorna o Banco de Dados
    public SQLiteDatabase getDb() {
        return db;
    }

    // Return
}
