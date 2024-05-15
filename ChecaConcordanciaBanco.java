package paradigmasTrabalhoUm;

/*Essa classe serve para verificar se existe um nome ou CPF igual já inserido
na database, retorna um warning caso seja nome mas nao deixa inserir caso seja
CPF */

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
        DatabaseMetodos checaLocal = new DatabaseMetodos();
        Connection conexao = null;
        conexao = checaLocal.conectaDb("paradigmas_database", "postgres", "admin");
        if (checaLocal.checaExistencia(conexao, coluna, stringVerificada) > 0)
        {
            return true;
        }
        else return false;
    }
}
