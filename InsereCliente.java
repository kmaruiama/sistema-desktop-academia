package paradigmasTrabalhoUm.GUI;

import paradigmasTrabalhoUm.Estrutural.ChecaConcordancia;
import paradigmasTrabalhoUm.Estrutural.ChecaConcordanciaBanco;
import paradigmasTrabalhoUm.Database.DatabaseMetodos;
import paradigmasTrabalhoUm.Estrutural.LimitadorAbas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.List;

public class InsereCliente implements ActionListener {

    int plano = 0;
    JButton planoSimples;
    JButton planoGold;
    JButton planoPremium;
    JButton inserirTudo;
    JTextField nomeInserir;
    JTextField cpfInserir;
    JTextField dataInserir;
    JTextField dataMatriculaInserir;
    JTextField cartaoInserir;
    JTextField cvvInserir;
    JTextField dataCartaoInserir;
    String alvoEdicao;
    String nomeEditar;
    JFrame frameCliente = new JFrame();
    LimitadorAbas limitadorAbas;
    Connection conexao;

    public InsereCliente(LimitadorAbas limitadorAbas, String nomeEditar) {
        this.limitadorAbas = limitadorAbas;
        this.nomeEditar = nomeEditar;

        conexao = DatabaseMetodos.conectaDb();

        frameCliente.setSize(500, 600);
        frameCliente.setTitle("Adicionar cliente");
        frameCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCliente.setLayout(null);
        frameCliente.setResizable(false);

        JLabel nomeTitulo = new JLabel();
        nomeTitulo.setText("Digite o nome do cliente");
        nomeTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        nomeTitulo.setBounds(20, 20, 300, 20);

        nomeInserir = new JTextField();
        nomeInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        nomeInserir.setBounds(20, 40, 450, 40);
        nomeInserir.setEditable(true);

        JLabel cpfTitulo = new JLabel();
        cpfTitulo.setText("Digite o CPF do cliente");
        cpfTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        cpfTitulo.setBounds(20, 80, 300, 20);

        cpfInserir = new JTextField();
        cpfInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        cpfInserir.setBounds(20, 100, 166, 40);
        cpfInserir.setEditable(true);

        JLabel dataTitulo = new JLabel();
        dataTitulo.setText("Digite a data de nascimento do cliente");
        dataTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        dataTitulo.setBounds(20, 140, 300, 20);

        dataInserir = new JTextField();
        dataInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        dataInserir.setBounds(20, 160, 120, 40);
        dataInserir.setEditable(true);


        JLabel dataMatriculaTitulo = new JLabel();
        dataMatriculaTitulo.setText("Digite a data de inicio do plano do cliente");
        dataMatriculaTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        dataMatriculaTitulo.setBounds(20, 250, 300, 20);

        dataMatriculaInserir = new JTextField();
        dataMatriculaInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        dataMatriculaInserir.setBounds(20, 270, 120, 40);
        dataMatriculaInserir.setEditable(true);

        JLabel cartaoTitulo = new JLabel();
        cartaoTitulo.setText("Digite o número do cartão do cliente");
        cartaoTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        cartaoTitulo.setBounds(20, 310, 300, 20);

        cartaoInserir = new JTextField();
        cartaoInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        cartaoInserir.setBounds(20, 330, 166, 40);
        cartaoInserir.setEditable(true);

        JLabel dataCartaoTitulo = new JLabel();
        dataCartaoTitulo.setText("Data de vencimento");
        dataCartaoTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        dataCartaoTitulo.setBounds(20, 370, 300, 20);

        dataCartaoInserir = new JTextField();
        dataCartaoInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        dataCartaoInserir.setBounds(20, 390, 60, 40);
        dataCartaoInserir.setEditable(true);

        JLabel cvvTitulo = new JLabel();
        cvvTitulo.setText("CVV");
        cvvTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        cvvTitulo.setBounds(20, 430, 300, 20);

        cvvInserir = new JTextField();
        cvvInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        cvvInserir.setBounds(20, 450, 60, 40);
        cvvInserir.setEditable(true);

        planoSimples = new JButton();
        planoSimples.setBounds(325, 100, 120, 30);
        planoSimples.setText("Simples");

        planoGold = new JButton();
        planoGold.setBounds(325, 130, 120, 30);
        planoGold.setText("Gold");

        planoPremium = new JButton();
        planoPremium.setBounds(325, 160, 120, 30);
        planoPremium.setText("Premium");

        inserirTudo = new JButton();
        inserirTudo.setBounds(280, 495, 200, 60);
        inserirTudo.setBackground(new Color(0x93DC5C));
        inserirTudo.setText("INSERIR CLIENTE");

        frameCliente.add(nomeTitulo);
        frameCliente.add(nomeInserir);
        frameCliente.add(cpfTitulo);
        frameCliente.add(cpfInserir);
        frameCliente.add(dataTitulo);
        frameCliente.add(dataInserir);
        frameCliente.add(dataMatriculaTitulo);
        frameCliente.add(dataMatriculaInserir);
        frameCliente.add(cartaoTitulo);
        frameCliente.add(cartaoInserir);
        frameCliente.add(dataCartaoTitulo);
        frameCliente.add(dataCartaoInserir);
        frameCliente.add(cvvTitulo);
        frameCliente.add(cvvInserir);
        frameCliente.add(planoSimples);
        frameCliente.add(planoGold);
        frameCliente.add(planoPremium);
        frameCliente.add(inserirTudo);

        // modificação no caso de editar cliente
        if (nomeEditar != null)
        {
            mostraEditarCliente();
        }

        if (nomeEditar == null)
        {
            frameCliente.setVisible(true);
        }

        frameCliente.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                limitadorAbas.setLimitador(0);
                if (nomeEditar != null)
                {
                    limitadorAbas.atualizaLista();
                }
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

    private void setupActionListeners()
    {
        planoSimples.addActionListener(this);
        planoGold.addActionListener(this);
        planoPremium.addActionListener(this);
        inserirTudo.addActionListener(this);
        nomeInserir.addActionListener(this);
        cpfInserir.addActionListener(this);
        dataInserir.addActionListener(this);
        dataMatriculaInserir.addActionListener(this);
        cartaoInserir.addActionListener(this);
        cvvInserir.addActionListener(this);
        dataCartaoInserir.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == planoSimples)
        {
            plano = 1;
            planoSimples.setEnabled(false);
            planoGold.setEnabled(true);
            planoPremium.setEnabled(true);
        }
        if (e.getSource() == planoGold)
        {
            plano = 2;
            planoGold.setEnabled(false);
            planoSimples.setEnabled(true);
            planoPremium.setEnabled(true);
        }
        if (e.getSource() == planoPremium)
        {
            plano = 3;
            planoPremium.setEnabled(false);
            planoSimples.setEnabled(true);
            planoGold.setEnabled(true);
        }

        if (e.getSource() == inserirTudo) {
            // prep = preprocessado
            boolean testaTudo;
            int fechaDez = 0;
            String nomePrep = nomeInserir.getText();
            String cpfPrep = cpfInserir.getText();
            String dataPrep = dataInserir.getText();
            String dataMatriculaPrep = dataMatriculaInserir.getText();
            String numeroCartaoPrep = cartaoInserir.getText();
            String cvvPrep = cvvInserir.getText();
            String dataCartaoPrep = dataCartaoInserir.getText();
            ChecaConcordancia verificadorInput = new ChecaConcordancia();
            ChecaConcordanciaBanco verificadorInputBanco = new ChecaConcordanciaBanco();

            //solução chinelona mas eu nao sabia como automatizar essa checagem visto que sao multiplas strings
            testaTudo = verificadorInput.checaConcordancia(frameCliente, nomePrep, 1, 1, 0);
            if (testaTudo)
            {
                fechaDez++;
            }
            testaTudo = verificadorInput.checaConcordancia(frameCliente, cpfPrep, 2, 0, 0);
            if (testaTudo)
            {
                fechaDez++;
            }
            testaTudo = verificadorInput.checaConcordancia(frameCliente, dataPrep, 3, 0, 0);
            if (testaTudo)
            {
                fechaDez++;
            }
            testaTudo = verificadorInput.checaConcordancia(frameCliente, dataMatriculaPrep, 4, 0, 0);
            if (testaTudo)
            {
                fechaDez++;
            }
            testaTudo = verificadorInput.checaConcordancia(frameCliente, numeroCartaoPrep, 5, 0, 0);
            if (testaTudo)
            {
                fechaDez++;
            }
            testaTudo = verificadorInput.checaConcordancia(frameCliente, cvvPrep, 6, 0, 0);
            if (testaTudo)
            {
                fechaDez++;
            }
            testaTudo = verificadorInput.checaConcordancia(frameCliente, cvvPrep, 7, 0, 0);
            if (testaTudo)
            {
                fechaDez++;
            }
            if (plano == 0)
            {
                JOptionPane.showMessageDialog(frameCliente, "Não esqueça de selecionar qual plano você quer");
            }
            else
            {
                fechaDez++;
            }
            if (verificadorInputBanco.checaBanco(nomePrep, 0) && nomeEditar == null)
            {
                int escolha = JOptionPane.showConfirmDialog(frameCliente, "Esse nome já consta no banco de dados, deseja continuar mesmo assim?",
                                                      "Atenção", JOptionPane.YES_NO_OPTION);
                if (escolha == JOptionPane.YES_OPTION)
                {
                    fechaDez++;
                }
            }
            else
            {
                fechaDez++;
            }
            if (verificadorInputBanco.checaBanco(cpfPrep, 1) && nomeEditar == null) {
                JOptionPane.showMessageDialog(frameCliente, "Esse CPF já consta no banco de dados",
                                         "Erro", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                fechaDez++;
            }
            if (fechaDez == 10)
            {
                if (nomeEditar == null)
                {
                    DatabaseMetodos.createRowClientes(conexao, nomePrep, cpfPrep, dataPrep, dataMatriculaPrep, numeroCartaoPrep, cvvPrep,
                                                      dataCartaoPrep, plano, 0, null);
                    JOptionPane.showMessageDialog(frameCliente, "Cliente inserido com sucesso!",
                                             "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    DatabaseMetodos.createRowClientes(conexao, nomePrep, cpfPrep, dataPrep, dataMatriculaPrep, numeroCartaoPrep, cvvPrep,
                                                      dataCartaoPrep, plano, 1, alvoEdicao);
                    JOptionPane.showMessageDialog(frameCliente, "Cliente editado com sucesso!",
                                             "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void mostraEditarCliente()
    {
        List<Object[]> infoResgatada = DatabaseMetodos.retornaInfoClientes(conexao, nomeEditar);
        for (Object[] resultado : infoResgatada)
        {
            nomeInserir.setText((String) resultado[1]);
            dataInserir.setText((String) resultado[2]);
            cpfInserir.setText((String) resultado[3]);
            alvoEdicao = (String) resultado[3];
            cartaoInserir.setText((String) resultado[4]);
            cvvInserir.setText((String) resultado[5]);
            dataCartaoInserir.setText((String) resultado[6]);
            dataMatriculaInserir.setText((String) resultado[7]);
            int planoLocal = (int) resultado[8];
            plano = planoLocal;
            if (planoLocal == 1)
            {
                planoSimples.setEnabled(false);
                planoGold.setEnabled(true);
                planoPremium.setEnabled(true);
            }
            if (planoLocal == 2)
            {
                planoGold.setEnabled(false);
                planoSimples.setEnabled(true);
                planoPremium.setEnabled(true);
            }
            if (planoLocal == 3)
            {
                planoPremium.setEnabled(false);
                planoSimples.setEnabled(true);
                planoGold.setEnabled(true);
            }
        }
        frameCliente.setVisible(true);
    }
}
