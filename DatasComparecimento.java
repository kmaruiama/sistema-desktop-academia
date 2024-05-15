package paradigmasTrabalhoUm;

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
    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");

    DatasComparecimento (String cpfBase)
    {
        this.cpfBase = cpfBase;
        implementaGuiComparecimento();
    }
    public void implementaGuiComparecimento ()
    {
        listaComparecimento = new JFrame();
        listaComparecimento.setLayout(null);
        List<String[]> listaNaoConvertida = databaseMetodos.retornaListaFrequencia(conexao, cpfBase);
        converteArrayLista(listaNaoConvertida);
        listaPresencas = new JList(listaConvertida);
        listaPresencas.setFont(new Font("Arial", Font.PLAIN, 15));
        listaPresencasScroll = new JScrollPane(listaPresencas);
        listaPresencasScroll.setBounds(40, 30, 200, 300);
        listaComparecimento.add(listaPresencasScroll);
        listaComparecimento.setSize(300,400);
        listaComparecimento.setResizable(false);
        listaComparecimento.setVisible(true);
        listaComparecimento.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void converteArrayLista(List<String[]> lista) {
        List<String> listaConcatenada = new ArrayList<>();

        DateTimeFormatter padraoInput = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter padraoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (String[] vetor : lista) {
            LocalDate data = LocalDate.parse(vetor[1], padraoInput);
            String dataFormatada = data.format(padraoBrasileiro);
            String stringConcatenada = vetor[0] + "      " + dataFormatada;
            listaConcatenada.add(stringConcatenada);
        }
        removeRepeticoes(listaConcatenada.toArray(new String[0]));
    }

    public void removeRepeticoes (String[] vetor)
    {
        Set<String> set = new HashSet<>();
        for (String string : vetor) {
            set.add(string);
        }
        listaConvertida = set.toArray(new String[0]);
    }
}
