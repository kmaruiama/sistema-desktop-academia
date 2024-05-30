package paradigmasTrabalhoUm.Estrutural;

/*Essa classe serve para verificar se existe um nome ou CPF igual já inserido
na database, retorna um warning caso seja nome mas nao deixa inserir caso seja
CPF */

import paradigmasTrabalhoUm.Database.DatabaseMetodos;

import java.sql.Connection;

public class ChecaConcordanciaBanco
{
    public boolean checaBanco (String stringVerificada, int campo)
    {
        String coluna = null;
        switch (campo)
        {
            case 0:
                coluna = "nome";
                break;

            case 1:
                coluna = "cpf";
                break;
        }
        Connection conexao = DatabaseMetodos.conectaDb();
        return DatabaseMetodos.checaExistencia(conexao, coluna, stringVerificada) > 0;
    }
}
