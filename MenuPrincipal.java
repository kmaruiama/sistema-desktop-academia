package isso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal implements ActionListener
{
    JButton botaoInserirCliente;
    JButton botaoEditarCliente;
    JButton botaoCriarTreino;

    JButton botaoEventoTreino;
    JFrame menuPrincipal = new JFrame();

    /*Serve para evitar a abertura de multiplas janelas*/
    private int insereClienteAbrir = 0;
    private int menuClientesAbrir = 0;
    private int criarTreinoAbrir = 0;
    private int eventoTreinoAbrir = 0;

        public MenuPrincipal() {

            menuPrincipal.setTitle("Sistema de Academia");
            menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuPrincipal.setResizable(false);
            menuPrincipal.setSize(1000, 750);

            botaoInserirCliente = new JButton();
            botaoInserirCliente.addActionListener(this);
            botaoInserirCliente.setBounds(100, 50, 200, 50);
            botaoInserirCliente.setText("Inserir cliente");
            menuPrincipal.add(botaoInserirCliente);

            botaoEditarCliente = new JButton();
            botaoEditarCliente.addActionListener(this);
            botaoEditarCliente.setBounds(100, 120, 200, 50);
            botaoEditarCliente.setText("Editar cliente");
            menuPrincipal.add(botaoEditarCliente);

            botaoCriarTreino = new JButton();
            botaoCriarTreino.addActionListener(this);
            botaoCriarTreino.setBounds(310, 50, 200, 50);
            botaoCriarTreino.setText("Criar treino");
            menuPrincipal.add(botaoCriarTreino);

            botaoEventoTreino = new JButton();
            botaoEventoTreino.addActionListener(this);
            botaoEventoTreino.setBounds(310, 120, 200, 50);
            botaoEventoTreino.setText("Treinar cliente");
            menuPrincipal.add(botaoEventoTreino);

            menuPrincipal.setLayout(null);
            menuPrincipal.setVisible(true);
        }

        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource()==botaoCriarTreino)
            {
                if (criarTreinoAbrir == 0) {
                    CriarTreino criarTreino = new CriarTreino(this);
                    criarTreinoAbrir = 1;
                }
                else
                {
                    JOptionPane.showMessageDialog(menuPrincipal, "Criador de treinos já está aberto");
                }

            }
            if (e.getSource()==botaoInserirCliente)
            {
                if (insereClienteAbrir == 0)
                {
                    LimitadorAbas pagina = new LimitadorAbas (this, null, null);
                    insereClienteAbrir = 1;
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
                    MenuClientes menuClientes = new MenuClientes(this, null,0);
                    menuClientesAbrir = 1;

                }
                else
                {
                    JOptionPane.showMessageDialog(menuPrincipal, "Menu de editar clientes já está aberto",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource()==botaoEventoTreino)
            {
                if (eventoTreinoAbrir == 0)
                {
                    EventoTreino eventoTreino = new EventoTreino(this);
                    eventoTreinoAbrir = 1;
                }
                else
                {
                    JOptionPane.showMessageDialog(menuPrincipal, "Treino de clientes já está aberto",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        public void updateInsereClienteAbrir (int valor)
        {
            this.insereClienteAbrir = valor;
        }
        public void updateMenuClientesAbrir (int valor)
        {
            this.menuClientesAbrir = valor;
        }

        public void updateCriarTreinoAbrir (int valor)
        {
            this.criarTreinoAbrir = valor;
        }

        public void updateEventoTreino (int valor)
        {
            this.eventoTreinoAbrir = valor;
        }




}
