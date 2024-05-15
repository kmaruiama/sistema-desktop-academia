package paradigmasTrabalhoUm;

import javax.swing.*;
import java.sql.Connection;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MenuTreinos {
    EventoTreino eventoTreino;
    JList listaTreinos;
    JFrame listaTreinosFrame;
    JScrollPane listaTreinosScroll;
    String[] rawListaTreino;
    String[] listaTreino;
    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");
    MenuTreinos(EventoTreino eventoTreino) {
        this.eventoTreino = eventoTreino;
        rawListaTreino = databaseMetodos.resgataTreinosDatabase(conexao);
        listaTreino = new String[rawListaTreino.length];

        for (int i = 0; i < rawListaTreino.length; i++) {
            listaTreino[i] = converteUnderline(rawListaTreino[i]);
        }

        listaTreinos = new JList(listaTreino);
        listaTreinosScroll = new JScrollPane(listaTreinos);
        listaTreinosScroll.setBounds(10, 30, 400, 150);

        listaTreinosFrame = new JFrame();
        listaTreinosFrame.setSize(435, 300);
        listaTreinosFrame.setTitle("Selecionar treino");
        listaTreinosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listaTreinosFrame.add(listaTreinosScroll);
        listaTreinosFrame.setLayout(null);
        listaTreinosFrame.setResizable(false);
        listaTreinosFrame.setVisible(true);

        listaTreinos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                eventoTreino.treinoSelecionado = (String) listaTreinos.getSelectedValue();
                retornaTreinoParaEventoTreino();
            }
        });
    }

    public void retornaTreinoParaEventoTreino() {
        eventoTreino.abrirEventoTreino();
        listaTreinosFrame.dispose();
    }

    private String converteUnderline(String string) {
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
