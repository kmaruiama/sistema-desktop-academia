package paradigmasTrabalhoUm;

import paradigmasTrabalhoUm.GUI.MenuPrincipal;
import java.sql.Connection;
import paradigmasTrabalhoUm.Database.DatabaseMetodos;

public class Main
{
    public static void main(String[] args) {
        Connection conexao = DatabaseMetodos.conectaDb();
        DatabaseMetodos.createTableCliente(conexao);
        DatabaseMetodos.createListaTreino(conexao);
        MenuPrincipal menuPrincipal = new MenuPrincipal();
    }
}