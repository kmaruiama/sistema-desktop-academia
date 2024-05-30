package paradigmasTrabalhoUm.Relatorios;

import paradigmasTrabalhoUm.Database.DatabaseMetodos;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatasComparecimento {
    JFrame listaComparecimento;
    JList listaPresencas;
    JScrollPane listaPresencasScroll;
    String cpfBase;
    String[] listaConvertida;
    Connection conexao = DatabaseMetodos.conectaDb();

    public DatasComparecimento(String cpfBase) {
        this.cpfBase = cpfBase;
        implementaGuiComparecimento();
    }
    public void implementaGuiComparecimento() {
        listaComparecimento = new JFrame();
        listaComparecimento.setTitle("Datas de comparecimento");
        listaComparecimento.setLayout(null);

        List<String[]> listaNaoConvertida = DatabaseMetodos.retornaListaFrequencia(conexao, cpfBase);
        converteArrayLista(listaNaoConvertida);

        listaPresencas = new JList(listaConvertida);
        listaPresencas.setFont(new Font("Arial", Font.PLAIN, 15));

        listaPresencasScroll = new JScrollPane(listaPresencas);
        listaPresencasScroll.setBounds(40, 30, 230, 300);

        listaComparecimento.add(listaPresencasScroll);
        listaComparecimento.setSize(330, 400);
        listaComparecimento.setResizable(false);
        listaComparecimento.setVisible(true);
        listaComparecimento.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void converteArrayLista(List<String[]> lista) {

        DateTimeFormatter padraoInput = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter padraoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        listaConvertida = lista.stream()
                .map(vetor -> {
                    LocalDate data = LocalDate.parse(vetor[1], padraoInput);
                    String dataFormatada = data.format(padraoBrasileiro);
                    return vetor[0] + "      " + dataFormatada;
                })
                .distinct()
                .toArray(String[]::new);
    }
}
