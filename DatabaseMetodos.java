package isso;

import java.sql.*;

public class DatabaseMetodos {
    public Connection conectaDb(String nomeDatabase, String user, String password) {
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + nomeDatabase, user, password);
            if (conexao != null) {
                System.out.println("Tudo certo");
            }
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
            System.out.println("Tabela criada ou encontrada");
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
            System.out.println("Tabela criada ou encontrada");
        } catch (SQLException e) {
            System.out.println("no método createTableTreino:" + e);
        }
    }

    public void createListaTreinos(Connection conexao) {
        Statement statement;
        try {
            String query =
                    "CREATE TABLE if NOT EXISTS lista_treinos (" +
                            "treinoid SERIAL, " +
                            "nome_treino VARCHAR(50), " +
                            "PRIMARY KEY (treinoid));";

            statement = conexao.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tabela criada ou encontrada");
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
            System.out.println("treino " + nomeTreino + " inserido na lista de treinos");

        } catch (SQLException e) {
            System.out.println("No método createRowTreinoLista" + e);
        }
    }

    public void createRowClientes(Connection conexao, String nome, String cpf, String dataNascimento,
                                  String dataMatricula, String numeroCartao, String cvvCartao, String
                                          dataCartao, int opcaoPlano) {
        Statement statement = null;
        try {
            String query = String.format(
                    "insert into " +
                            "clientes (nome, data_nascimento, cpf, data_matricula, numero_cartao, cvv, data_cartao, plano) " +
                            "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', %d);", nome, dataNascimento, cpf, dataMatricula,
                    numeroCartao, cvvCartao, dataCartao, opcaoPlano);
            statement = conexao.createStatement();
            statement.executeUpdate(query);
            System.out.println("cliente " + nome + " inserido");

        } catch (SQLException e) {
            System.out.println("no método createRowClientes: " + e);
        }
    }

    public void printaClientes(Connection conexao, String cpfNumero) {
        ResultSet resultSet = null;
        Statement statement = null;
        String nome = null;
        String cpf = null;
        String dataNascimento = null;
        try {
            String query = String.format(
                    "SELECT * " +
                            "FROM clientes " +
                            "WHERE nome ILIKE '%s';", cpfNumero);
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Achei seu cliente");
            if (resultSet.next()) {
                nome = resultSet.getString("nome");
                cpf = resultSet.getString("cpf");
                dataNascimento = resultSet.getString("data_nascimento");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("no método printaClientes: " + e);
        }
        System.out.println(nome + " " + dataNascimento + " " + cpf);

    }

    public void createRowTreino(Connection conexao, String query) {
        Statement statement = null;
        try {
            statement = conexao.createStatement();
            statement.executeUpdate(query);
            System.out.println("treino novo inserido na db de treinos");
        } catch (SQLException e) {
            System.out.println("no método createRowTreino:" + e);
        }
    }

    public void createLinkClienteTreino(Connection conexao) {
        Statement statement;
        try {
            String query =
                    "CREATE TABLE if NOT EXISTS lista_link (" +
                            "linkid SERIAL PRIMARY KEY, " +
                            "treinoid INT, " +
                            "clientid INT, " +
                            "dia_treinado DATE, " +
                            "FOREIGN KEY (treinoid) REFERENCES lista_treinos(treinoid), " +
                            "FOREIGN KEY (clientid) REFERENCES clientes(clientid));";
            statement = conexao.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tabela criada ou encontrada");
        } catch (SQLException e) {
            System.out.println("no método createLinkClienteTreino :" + e);
        }
    }

    public void createRowLink(Connection conexao, String nome, String treino, String data) {
        Statement statement = null;
        try {
            String query = String.format(
                    "insert into lista_link" +
                            " (treinoid, clientid, dia_treinado) " +
                            "values ('%s', '%s', '%s');", treino, nome, data);
            statement = conexao.createStatement();
            statement.executeUpdate(query);
            System.out.println(nome + " com treino " + treino + " linkado");
        } catch (SQLException e) {
            System.out.println("no método createRowLink: " + e);
        }
    }

    public String buscaIDPorNome(Connection conexao, String nome, String tableNome) {
        Statement statement = null;
        ResultSet resultSet = null;
        String id = tableNome.equals("clientes") ? "clientid" : "treinoid";
        String coluna = tableNome.equals("clientes") ? "nome" : "nome_treino";
        String idReturn = null;
        try {
            String query = String.format(
                    "SELECT %s " +
                            "FROM %s " +
                            "WHERE %s ILIKE '%s';", id, tableNome, coluna, nome);
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("deu bom na conversao de nome pra id");
            if (resultSet.next()) {
                idReturn = resultSet.getString(id);
            }
        } catch (SQLException e) {
            System.out.println("no método buscaIDPorNome: " + e);
        }
        return idReturn;
    }

    public boolean checaExistencia(Connection conexao, String campo, String nome) {
        boolean existe = false;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String query = String.format("SELECT EXISTS (SELECT 1 FROM clientes " +
                    "WHERE %s = '%s') " +
                    "AS row_exists", campo, nome);
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                existe = resultSet.getBoolean("row_exists");
            }
        } catch (SQLException e) {
            System.out.println("no método checaExistencia: " + e);
        }
        return existe;
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
}