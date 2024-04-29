package isso;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class MenuClientes
{
    int insereClienteAbrir = 0;
    JFrame menuCliente = new JFrame();
    String nomeSelecionado;
    String[] nomesDatabase;
    String nomeValidado;
    JList listaNomes;
    JScrollPane listaNomesScroll;
    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    LimitadorAbas limitadorAbas;
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");
    MenuClientes (MenuPrincipal menuPrincipal)
    {
        JLabel selecionarCliente = new JLabel();
        selecionarCliente.setText("Selecione o cliente");
        selecionarCliente.setFont(new Font("Arial", Font.PLAIN, 15));
        selecionarCliente.setBounds(10, 10, 300, 20);

        nomesDatabase = databaseMetodos.resgataNomesDatabase(conexao);
        listaNomes = new JList(nomesDatabase);
        listaNomes.setFont(new Font("Arial", Font.PLAIN, 15));

        listaNomesScroll = new JScrollPane(listaNomes);
        listaNomesScroll.setBounds(10, 30, 400, 150);
        listaNomes.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                nomeSelecionado = (String) listaNomes.getSelectedValue();
                if (databaseMetodos.checaExistencia(conexao, "nome", nomeSelecionado) > 1) {
                    JOptionPane.showConfirmDialog(menuCliente, "Duas pessoas possuem esse nome, escolha o CPF",
                             "Atenção", JOptionPane.PLAIN_MESSAGE);
                    String[] listaCpfs = databaseMetodos.retornaCpfPorNome(conexao, nomeSelecionado);

                    JFrame listaCpfsFrame = new JFrame();
                    listaCpfsFrame.setSize(435, 300);
                    listaCpfsFrame.setTitle("Adicionar cliente");
                    listaCpfsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    listaCpfsFrame.setLayout(null);
                    listaCpfsFrame.setResizable(false);

                    JList cpfLista = new JList(listaCpfs);
                    cpfLista.setFont(new Font("Arial", Font.PLAIN, 15));

                    JScrollPane cpfListaScroll = new JScrollPane(cpfLista);
                    cpfListaScroll.setBounds(10, 30, 400, 150);

                    listaCpfsFrame.add(cpfListaScroll);
                    listaCpfsFrame.setVisible(true);
                    cpfLista.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            nomeValidado = (String) cpfLista.getSelectedValue();
                            String[] options = {"EDITAR", "DELETAR"};
                            if (nomeValidado != null) {
                                int escolhaEditarDeletar = JOptionPane.showOptionDialog(menuCliente, "Escolha",
                                        null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                if (escolhaEditarDeletar == 0) {
                                    limitadorAbas = new LimitadorAbas(null, MenuClientes.this, nomeValidado);
                                } else if (escolhaEditarDeletar == 1) {
                                    databaseMetodos.deletaCliente(conexao, nomeValidado);
                                    recarregaLista();
                                }
                            }
                        }
                    });
                } else
                {
                    String[] listaCpfIndividual = databaseMetodos.retornaCpfPorNome(conexao, nomeSelecionado);
                    if (listaCpfIndividual.length>0)
                    {
                        nomeValidado = listaCpfIndividual[0];
                    }
                    if (nomeValidado != null)
                    {
                        String[] options = {"EDITAR", "DELETAR"};
                        int escolhaEditarDeletar = JOptionPane.showOptionDialog(menuCliente, "Escolha",
                                null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (escolhaEditarDeletar == 0) {
                            limitadorAbas = new LimitadorAbas(null, MenuClientes.this, nomeValidado);
                        }
                        else if (escolhaEditarDeletar == 1)
                        {
                            databaseMetodos.deletaCliente(conexao, nomeValidado);
                            recarregaLista();
                        }
                    }
                }
            }
        });

        menuCliente.setSize(435, 300);
        menuCliente.setTitle("Adicionar cliente");
        menuCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuCliente.setLayout(null);
        menuCliente.setResizable(false);

        menuCliente.add(selecionarCliente);
        menuCliente.add(listaNomesScroll);
        menuCliente.setVisible(true);

        menuCliente.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                menuPrincipal.updateMenuClientesAbrir(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        setupActionListeners();
    }

    private void setupActionListeners() {
    }

    public void updateInsereClienteAbrir(int valor) {
        insereClienteAbrir = valor;
    }

    public void recarregaLista() {
        nomeValidado = null;
        nomesDatabase = databaseMetodos.resgataNomesDatabase(conexao);
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String nome : nomesDatabase) {
            model.addElement(nome);
        }
        listaNomes.setModel(model);
    }
}
