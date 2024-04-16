package isso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener{


    JButton botaoBases;
    JFrame menuPrincipal = new JFrame();

    /*Serve para evitar a abertura de multiplas janelas de cadastro*/
    public int paginaDoisAbrir = 0;

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
            /*ImageIcon image = new ImageIcon(getClass().getResource("coisa.png"));
            JLabel jLabel = new JLabel(image);
            getContentPane().add(jLabel, BorderLayout.CENTER);*/
            menuPrincipal.setLayout(null);
            menuPrincipal.add(painelUm);
            menuPrincipal.add(painelDois);

            menuPrincipal.setVisible(true);
        }

        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource()==botaoBases && paginaDoisAbrir == 0)
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

        public void updatePaginaDoisAbrir (int valor)
        {
            this.paginaDoisAbrir = valor;
        }

}
