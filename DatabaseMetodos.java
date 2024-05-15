package paradigmasTrabalhoUm;

import java.sql.*;
import java.util.*;

public class DatabaseMetodos {
    public Connection conectaDb(String nomeDatabase, String user, String password) {
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + nomeDatabase, user, password);
        } catch (Exception e) {
            System.out.println("no método conectaDb: " + e);
        }
        return conexao;
    }


    public void createTableCliente(Connection conexao) {
        Statement statement;
        try {
            String query =
                    "CREATE TABLE if NOT EXISTS clientes (" +
                            "clientid SERIAL, " +
                            "nome VARCHAR(50), " +
                            "data_nascimento VARCHAR(10), " +
                            "cpf VARCHAR(14), " +
                            "numero_cartao VARCHAR (19), " +
                            "cvv VARCHAR (3), " +
                            "data_cartao VARCHAR (7), " +
                            "data_matricula VARCHAR (10), " +
                            "plano INT, " +
                            "PRIMARY KEY (clientid));";
            statement = conexao.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("no método createTableCliente: " + e);
        }
    }


    public void createTableTreino(Connection conexao, String nomeTreino) {
        Statement statement;
        try {
            String query =
                    "CREATE TABLE if NOT EXISTS " + nomeTreino + " (" +
                            "exercicioid INT, " +
                            "series INT," +
                            "repeticoes INT);";

            statement = conexao.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("no método createTableTreino:" + e);
        }
    }

    public void createListaTreino(Connection conexao) {
        Statement statement;
        try {
            String query =
                    "CREATE TABLE if NOT EXISTS lista_treinos (" +
                            "treinoid SERIAL, " +
                            "nome_treino VARCHAR(50), " +
                            "PRIMARY KEY (treinoid));";

            statement = conexao.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("no método createListaTreinos: " + e);
        }
    }

    public void createRowTreinoLista(Connection conexao, String nomeTreino) {
        Statement statement = null;
        try {
            String query = String.format(
                    "insert into lista_treinos (nome_treino) values ('%s');", nomeTreino);
            statement = conexao.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("No método createRowTreinoLista" + e);
        }
    }

    public void createRowClientes(Connection conexao, String nome, String cpf, String dataNascimento,
                                  String dataMatricula, String numeroCartao, String cvvCartao, String
                                          dataCartao, int opcaoPlano, int funcaoEditarCriar, String cpfReferencia) {
        Statement statement = null;
        try {
            if (funcaoEditarCriar == 0) {
                String query = String.format(
                        "insert into " +
                                "clientes (nome, data_nascimento, cpf, data_matricula, numero_cartao, cvv, data_cartao, plano) " +
                                "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', %d);", nome, dataNascimento, cpf, dataMatricula,
                        numeroCartao, cvvCartao, dataCartao, opcaoPlano);
                statement = conexao.createStatement();
                statement.executeUpdate(query);
            } else {
                String query = String.format("UPDATE clientes SET nome = '%s', cpf = '%s', data_nascimento = '%s'," +
                                " numero_cartao = '%s', cvv = '%s', data_cartao = '%s', data_matricula =" +
                                " '%s', plano = %d WHERE cpf = '%s'", nome, cpf, dataNascimento, numeroCartao,
                        cvvCartao, dataCartao, dataMatricula, opcaoPlano, cpfReferencia);
                statement = conexao.createStatement();
                statement.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.out.println("no método createRowClientes: " + e);
        }
    }

    public List<Object[]> retornaInfoClientes(Connection conexao, String cpfNumero) {
        ResultSet resultSet = null;
        Statement statement = null;
        List<Object[]> resultadoColunas = new ArrayList<>();
        Object[] returnResultadoColunas = null;
        try {
            String query = String.format("SELECT * FROM clientes " +
                    "WHERE cpf ILIKE '%s'", cpfNumero);
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int clientid = resultSet.getInt("clientid");
                String nome = resultSet.getString("nome");
                String data_nascimento = resultSet.getString("data_nascimento");
                String cpfResult = resultSet.getString("cpf");
                String numero_cartao = resultSet.getString("numero_cartao");
                String cvv = resultSet.getString("cvv");
                String data_cartao = resultSet.getString("data_cartao");
                String data_matricula = resultSet.getString("data_matricula");
                int plano = resultSet.getInt("plano");
                Object[] resultado = {clientid, nome, data_nascimento, cpfResult, numero_cartao, cvv, data_cartao, data_matricula, plano};
                resultadoColunas.add(resultado);
            }

        } catch (SQLException e) {
            System.out.println("no método printaClientes: " + e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("No bloco finally do método printaClientes" + e);
            }
        }
        return resultadoColunas;
    }

    public void createRowTreino(Connection conexao, String query) {
        Statement statement = null;
        try {
            statement = conexao.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("no método createRowTreino:" + e);
        }
    }

    public void createEventoTable(Connection conexao) {
        Statement statement;
        try {
            String query =  "CREATE TABLE IF NOT EXISTS lista_eventos (" +
                            "id SERIAL PRIMARY KEY," +
                            "treino_titulo VARCHAR(50)," +
                            "nome VARCHAR(50)," +
                            "exercicio INT," +
                            "series INT," +
                            "repeticoes INT," +
                            "carga INT," +
                            "data_treino DATE" +
                            ");";

            statement = conexao.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("no método createLinkClienteTreino :" + e);
        }
    }

    public void insertEvento(Connection conexao, String treino_titulo, String nome, int exercicio,
                             int series, int repeticoes, int carga, String data_treino) {
        Statement statement = null;
        try {
            String query = String.format("INSERT INTO lista_eventos (treino_titulo, nome, exercicio," +
                                         " series, repeticoes, carga, data_treino) VALUES ('%s', '%s', %d, %d, %d, %d, '%s')",
                    treino_titulo, nome, exercicio, series, repeticoes, carga, data_treino);
            statement = conexao.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("no método createRowLink: " + e);
        }
    }

    public int checaExistencia(Connection conexao, String campo, String nome) {
        int stringQuantidade = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String query = String.format("SELECT COUNT(*) FROM clientes WHERE %s = '%s'", campo, nome);
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                stringQuantidade = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("no método checaExistencia: " + e);
        }
        return stringQuantidade;
    }

    public String[] resgataNomesDatabase(Connection conexao) {
        String[] lista = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT nome FROM clientes";
            statement = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery();
            resultSet.last();
            int linhaCount = resultSet.getRow();
            resultSet.beforeFirst();
            lista = new String[linhaCount];
            int index = 0;
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                lista[index++] = nome;
            }
        } catch (SQLException e) {
            System.out.println("no método resgataNomesDatabase: " + e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("no bloco finally do método resgataNomesDatabase: " + e);
            }
        }
        return lista;
    }

    public String[] retornaCpfPorNome(Connection conexao, String nome) {
        Statement statement = null;
        ResultSet resultSet = null;
        List<String> cpfs = new ArrayList<>();
        try {
            String query = String.format("SELECT cpf FROM clientes WHERE nome ILIKE '%s'", nome);
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                cpfs.add(cpf);
            }
        } catch (SQLException e) {
            System.out.println("no método retornaCpfPorNome: " + e);
        }
        return cpfs.toArray(new String[0]);
    }

    public void deletaCliente(Connection conexao, String nome) {
        Statement statement = null;
        try {
            String query = String.format("DELETE FROM clientes where cpf = '%s'", nome);
            statement = conexao.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("no método retornaCpfPorNome: " + e);
        }
    }

    public boolean checaTituloTreino(Connection conexao, String string) {
        ResultSet resultSet = null;
        Statement statement = null;
        boolean tabelaExiste = false;
        try {
            String query = String.format("SELECT EXISTS (" +
                    "SELECT 1 " +
                    "FROM information_schema.tables " +
                    "WHERE table_name = '%s')", string);
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                tabelaExiste = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("No método checaTituloTreino: " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("No bloco finally do método checaTituloTreino: " + e);
            }
        }
        return tabelaExiste;
    }

    public String[] resgataTreinosDatabase(Connection conexao) {
        String[] lista = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT nome_treino FROM lista_treinos";
            statement = conexao.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery();
            resultSet.last();
            int linhaCount = resultSet.getRow();
            resultSet.beforeFirst();
            lista = new String[linhaCount];
            int index = 0;
            while (resultSet.next()) {
                String nome = resultSet.getString("nome_treino");
                lista[index++] = nome;
            }
        } catch (SQLException e) {
            System.out.println("no método resgataTreinosDatabase: " + e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("no bloco finally do método resgataTreinosDatabase: " + e);
            }
        }
        return lista;
    }

    public int retornaNumeroExercicios(Connection conexao, String string) {
        int numeroColunas = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String query = String.format("SELECT COUNT(*) AS rowcount FROM %s", string);
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                numeroColunas = resultSet.getInt("rowcount");
            }
        } catch (SQLException e) {
            System.out.println("no método retornaNumeroExercicios " + e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("no bloco finally do método retornaNumeroExercicios: " + e);
            }
        }
        return numeroColunas;
    }

    public int[] retornaInfoExercicios(Connection conexao, String string) {
        Statement statement = null;
        ResultSet resultSet = null;
        int[] infoArray = {0};
        try {

            String query = String.format("SELECT * FROM %s", string);
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);

            int contadorLinha = 0;
            if (resultSet.last()) {
                contadorLinha = resultSet.getRow();
                resultSet.beforeFirst();
            }

            infoArray = new int[contadorLinha * 3];

            int index = 0;
            while (resultSet.next()) {
                infoArray[index++] = resultSet.getInt(1);
                infoArray[index++] = resultSet.getInt(2);
                infoArray[index++] = resultSet.getInt(3);
            }
        } catch (SQLException e) {
            System.out.println("No método retornaInfoExercicios: " + e);
        }
        return infoArray;
    }

    public boolean relatoriosSaoPossiveis(Connection connection) {
        ResultSet resultSet = null;
        try {
            DatabaseMetaData dadosDb = connection.getMetaData();
            resultSet = dadosDb.getTables(null, null, "lista_eventos", null);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("No método relatorioSaoPossiveis: " + e);
            return false;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("No bloco finally do método relatorioSaoPossiveis: " + e);
                }
            }
        }
    }
}

