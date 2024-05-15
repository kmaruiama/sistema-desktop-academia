package paradigmasTrabalhoUm;

import java.sql.Connection;

public class RelatorioCargas {
    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");

}
