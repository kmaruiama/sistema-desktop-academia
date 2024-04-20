package isso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener{


    JButton botaoBases;

    JButton botaoEditarCliente;
    JFrame menuPrincipal = new JFrame();

    /*Serve para evitar a abertura de multiplas janelas de cadastro*/
    public int paginaDoisAbrir = 0;
    public int menuClientesAbrir = 0;

        public Gui() {
            menuPrincipal.setTitle("Sistema de Academia");
            menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuPrincipal.setResizable(false);
            menuPrincipal.setSize(1000, 750);

            botaoBases = new JButton();
            botaoBases.addActionListener(this);
            JLabel labelTituloPrincipal = new JLabel();
            labelTituloPrincipal.setText("Sistema de Gerenciamento");
            labelTituloPrincipal.setBounds(0, 0, 300, 10);

            botaoEditarCliente = new JButton();
            botaoEditarCliente.addActionListener(this);
            botaoEditarCliente.setBounds(100, 100, 200, 50);
            botaoEditarCliente.setText("Editar cliente");
            menuPrincipal.add(botaoEditarCliente);

            JPanel painelUm = new JPanel();
            painelUm.setBackground(Color.BLUE);
            painelUm.setBounds(0,0, 100, 100);
            botaoBases.setBounds(0, 0, 50, 20);
            painelUm.add(botaoBases);

            JPanel painelDois = new JPanel();
            painelDois.setBackground(Color.RED);
            painelDois.setBounds(0,100, 500, 200);
            painelDois.setLayout(null);
            painelDois.add(labelTituloPrincipal);
            menuPrincipal.setLayout(null);
            menuPrincipal.add(painelUm);
            menuPrincipal.add(painelDois);

            menuPrincipal.setVisible(true);
        }

        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource()==botaoBases)
            {
                if (paginaDoisAbrir == 0)
                {
                    paginaDoisAbrir = 1;
                    PaginaDois pagina = new PaginaDois(this);
                }
                else
                {
                    JOptionPane.showMessageDialog(menuPrincipal, "Cadastro de clientes já está aberto",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource()==botaoEditarCliente)
            {
                if (menuClientesAbrir == 0)
                {
                    menuClientesAbrir = 1;
                    MenuClientes pagina = new MenuClientes(this);
                }
                else
                {
                    JOptionPane.showMessageDialog(menuPrincipal, "Menu de editar clientes já está aberto",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        public void updatePaginaDoisAbrir (int valor)
        {
            this.paginaDoisAbrir = valor;
        }
        public void updateMenuClientesAbrir (int valor) { this.menuClientesAbrir = valor; }


}
