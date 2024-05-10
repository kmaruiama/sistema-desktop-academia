package isso;

import javax.swing.*;
import java.sql.Connection;

public class MenuTreinos {
    EventoTreino eventoTreino;

    JFrame menuTreino;

    String[] rawListaTreino;
    String[] listaTreino;

    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");

    MenuTreinos (EventoTreino eventoTreino)
    {
        this.eventoTreino = eventoTreino;
        rawListaTreino = databaseMetodos.resgataTreinosDatabase(conexao);
        listaTreino = new String[rawListaTreino.length];
        for (int i = 0; i<rawListaTreino.length; i++)
        {
            listaTreino[i] = converteBackspace(rawListaTreino[i]);
            System.out.println(listaTreino[i]);
        }


    }

    public void retornaTreinoParaTreino (String string)
    {
        eventoTreino.treinoSelecionado = string;
        menuTreino.dispose();
    }

    private String converteBackspace(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char underline : string.toCharArray()) {
            if (underline == '_') {
                stringBuilder.append(' ');
            } else {
                stringBuilder.append(underline);
            }
        }
        return stringBuilder.toString();
    }

}
