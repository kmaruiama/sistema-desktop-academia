package isso;

import java.sql.Connection;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args) {
        DatabaseMetodos novaDatabase = new DatabaseMetodos();
        Connection conexao = novaDatabase.conectaDb("paradigmas_database", "postgres", "admin");
        novaDatabase.createTableCliente(conexao);
        novaDatabase.createListaTreino(conexao);
        MenuPrincipal menuPrincipal = new MenuPrincipal();
    }
}