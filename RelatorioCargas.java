package paradigmasTrabalhoUm.Relatorios;

import paradigmasTrabalhoUm.Database.DatabaseMetodos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.Connection;
import java.util.List;
import java.util.Comparator;

public class RelatorioCargas {
    Connection conexao = DatabaseMetodos.conectaDb();
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

    public RelatorioCargas(String nomeSelecionado) {
        seletorExercicios();
        this.nomeSelecionado = nomeSelecionado;
    }

    private void seletorExercicios() {
        frameEscolhaExercicios = new JFrame();
        frameEscolhaExercicios.setSize(435, 300);
        frameEscolhaExercicios.setTitle("Selecionar exercício");
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
        dataGeral = DatabaseMetodos.resgatandoDadosCarga(conexao, nomeSelecionado, valorBuscado);
        if (dataGeral.isEmpty())
        {
            JOptionPane.showMessageDialog(frameEscolhaExercicios, "O cliente ainda não praticou esse exercício",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else {
            dataGeral.sort(Comparator.comparing(o -> o.data_treino));
            seletorProgressao();
        }
    }

    private void seletorProgressao ()
    {
        HistogramPanel histogramPanel = new HistogramPanel();
        histogramPanel.createAndShowGUI(dataGeral);
    }
}
