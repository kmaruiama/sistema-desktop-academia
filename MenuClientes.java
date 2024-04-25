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
    JFrame menuCliente = new JFrame();
    String nomeSelecionado;
    String[] nomesDatabase;
    JList listaNomes;

    int insereClienteAbrir = 0;

    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");
    JScrollPane listaNomesScroll;
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

        listaNomes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                nomeSelecionado = (String) listaNomes.getSelectedValue();
                if (insereClienteAbrir == 0)
                {
                    insereClienteAbrir = 1;
                    LimitadorAbas pagina = new LimitadorAbas(null, MenuClientes.this, nomeSelecionado);
                }
                else
                {
                    JOptionPane.showMessageDialog(menuCliente, "Cadastro de clientes já está aberto",
                            "Erro", JOptionPane.ERROR_MESSAGE);
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

        menuCliente.addWindowListener(new WindowListener()
        {
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

    private void setupActionListeners ()
    {
    }
    public void updateInsereClienteAbrir (int valor)
    {
        this.insereClienteAbrir = valor;
    }

}
