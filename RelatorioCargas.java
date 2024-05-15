package paradigmasTrabalhoUm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.Connection;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class RelatorioCargas {
    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");
    String exercicioSelecionado;
    String[] exercicios = {
            "Leg Press",
            "Cadeira Adutora",
            "Supino Máquina",
            "Crucifixo Máquina",
            "Abdominal Máquina",
            "Desenvolvimento Máquina A."
    };
    int valorBuscado;
    JList listaExercicios;
    JScrollPane listaExerciciosScroll;
    JFrame frameEscolhaExercicios;

    List<DatabaseMetodos.ParametrosUsados> dataGeral;

    String nomeSelecionado;

    RelatorioCargas(String nomeSelecionado) {
        seletorExercicios();
        this.nomeSelecionado = nomeSelecionado;
    }

    private void seletorExercicios() {
        frameEscolhaExercicios = new JFrame();
        frameEscolhaExercicios.setSize(435, 300);
        frameEscolhaExercicios.setTitle("Adicionar cliente");
        frameEscolhaExercicios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameEscolhaExercicios.setLayout(null);
        frameEscolhaExercicios.setResizable(false);

        listaExercicios = new JList(exercicios);
        listaExercicios.setFont(new Font("Arial", Font.PLAIN, 15));
        listaExerciciosScroll = new JScrollPane(listaExercicios);
        listaExerciciosScroll.setBounds(10, 30, 400, 150);

        frameEscolhaExercicios.add(listaExerciciosScroll);
        frameEscolhaExercicios.setVisible(true);

        listaExercicios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                exercicioSelecionado = (String) listaExercicios.getSelectedValue();
                frameEscolhaExercicios.dispose();

                if (exercicioSelecionado.equals("Leg Press")) {
                    valorBuscado = 1;
                }
                if (exercicioSelecionado.equals("Cadeira Adutora")) {
                    valorBuscado = 5;
                }
                if (exercicioSelecionado.equals("Supino Máquina")) {
                    valorBuscado = 20;
                }
                if (exercicioSelecionado.equals("Crucifixo Máquina")) {
                    valorBuscado = 26;
                }
                if (exercicioSelecionado.equals("Abdominal Máquina")) {
                    valorBuscado = 40;
                }
                if (exercicioSelecionado.equals("Desenvolvimento Máquina A.")) {
                    valorBuscado = 50;
                }
                retornaDadosCompletos();
            }
        });
    }

    private void retornaDadosCompletos() {
        dataGeral = databaseMetodos.resgatandoDadosCarga(conexao, nomeSelecionado, valorBuscado);
        arrumaParametrosPorData(dataGeral);
        seletorProgressao();
    }

    private void arrumaParametrosPorData(List<DatabaseMetodos.ParametrosUsados> dataGeral) {
        Collections.sort(dataGeral, new Comparator<DatabaseMetodos.ParametrosUsados>() {
            @Override
            public int compare(DatabaseMetodos.ParametrosUsados o1, DatabaseMetodos.ParametrosUsados o2) {
                return o1.data_treino.compareTo(o2.data_treino);
            }
        });
    }

    private void seletorProgressao ()
    {
        HistogramPanel histogramPanel = new HistogramPanel();
        histogramPanel.createAndShowGUI(dataGeral);
    }


}
