package isso;

import isso.DatabaseFuncoes;
import isso.Gui;

import java.sql.Connection;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        Gui gui = new Gui();
        Scanner scanner = new Scanner(System.in);
        DatabaseFuncoes novaDatabase = new DatabaseFuncoes();
        Connection conexao = null;
        conexao = novaDatabase.conectaDb("paradigmas_database", "postgres", "admin");
        novaDatabase.createTableCliente(conexao);
        novaDatabase.createListaTreinos(conexao);
        novaDatabase.createRowTreinoLista(conexao, "treino_1");
        novaDatabase.createLinkClienteTreino(conexao);
        String nome = scanner.nextLine();
        String cpf = scanner.nextLine();
        String dataNascimento = scanner.nextLine();
        novaDatabase.createRowClientes(conexao, nome, cpf, dataNascimento);
        /*System.out.println("digite o n do cpf");
        String cpfBusca = scanner.nextLine();
        novaDatabase.printaClientes(conexao, cpfBusca);
        System.out.println("digite os codigos e depois -1 para finalizar");
        int seletorExer = 0;
        StringBuilder queryTreino = new StringBuilder(200);
        queryTreino.append ("insert into treino ");
        System.out.println("digite o nome do seu treino (ex:treino_1)");
        String nomeTreino = scanner.nextLine();
        novaDatabase.createTableTreino(conexao, nomeTreino);
        novaDatabase.createListaTreinos(conexao);
        novaDatabase.createRowTreinoLista(conexao, nomeTreino);
        while (seletorExer != -1)
        {
            System.out.println ("digite 01 para legpress");
            System.out.println ("digite 05 para adutores");
            System.out.println ("digite 20 para supino maquina");
            System.out.println ("digite 26 para crucifixo maquina");
            System.out.println ("digite 40 para abdominal maquina");
            System.out.println ("digite 50 para desenvolvimento maquina aberto");
            seletorExer = scanner.nextInt();

            switch (seletorExer) {
                case 1:
                    queryTreino.append("(exercicioid, series, repeticoes) values ('01', ");
                    insereSeriesRepeticoes(queryTreino);
                    break;

                case 5:
                    queryTreino.append("(exercicioid, series, repeticoes) values ('05', ");
                    insereSeriesRepeticoes(queryTreino);
                    break;

                case 20:
                    queryTreino.append("(exercicioid, series, repeticoes) values ('20', ");
                    insereSeriesRepeticoes(queryTreino);
                    break;

                case 26:
                    queryTreino.append("(exercicioid, series, repeticoes) values ('26', ");
                    insereSeriesRepeticoes(queryTreino);
                    break;

                case 40:
                    queryTreino.append("(exercicioid, series, repeticoes) values ('40', ");
                    insereSeriesRepeticoes(queryTreino);
                    break;

                case 50:
                    queryTreino.append("(exercicioid, series, repeticoes) values ('50', ");
                    insereSeriesRepeticoes(queryTreino);
                    break;

            }
        }
        String queryFinal = queryTreino.toString();
        novaDatabase.createRowTreino(conexao, queryFinal); */

        System.out.println("digite o nome do cliente");
        String nomeString = scanner.nextLine();
        String tableNome = "clientes";
        String nome1 = novaDatabase.buscaIDPorNome (conexao, nomeString, tableNome);
        System.out.println("nome :" + nome1);
        System.out.println("digite o nome do treino");
        String treinoString = scanner.nextLine();
        String tableNome2 = "lista_treinos";
        String treino = novaDatabase.buscaIDPorNome (conexao, treinoString, tableNome2);
        System.out.println("treino: " + treino);
        System.out.println("insira o dia");
        String data = scanner.nextLine();
        novaDatabase.createRowLink(conexao, nome1, treino, data);


    }

    public static void insereSeriesRepeticoes (StringBuilder query)
    {
        String repeticoes;
        String series;
        System.out.println("insira o numero de series");
        Scanner scanner = new Scanner(System.in);
        series = scanner.nextLine();
        query.append("'" + series + "', ");
        System.out.println("insira o numero de repeticoes");
        repeticoes = scanner.nextLine();
        query.append("'" + repeticoes + "');");
    }
}
