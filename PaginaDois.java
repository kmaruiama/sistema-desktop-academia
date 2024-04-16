package isso;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class PaginaDois implements ActionListener {

    JButton planoSimples;
    JButton planoGold;
    JButton planoPremium;

    JButton inserirTudo;

    JFrame frameCliente = new JFrame();
    Gui gui;

    PaginaDois(Gui gui)
    {
        this.gui = gui;
        frameCliente.setSize(500, 600);
        frameCliente.setTitle("Adicionar cliente");
        frameCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCliente.setLayout(null);
        frameCliente.setResizable(false);

        JLabel nomeTitulo = new JLabel();
        nomeTitulo.setText("Digite o nome do cliente");
        nomeTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        nomeTitulo.setBounds(20, 20, 300, 20);

        JTextField nomeInserir = new JTextField();
        nomeInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        nomeInserir.setBounds(20, 40, 450, 40);
        nomeInserir.setEditable(true);

        JLabel cpfTitulo = new JLabel();
        cpfTitulo.setText("Digite o CPF do cliente");
        cpfTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        cpfTitulo.setBounds(20, 80, 300, 20);

        JTextField cpfInserir = new JTextField();
        cpfInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        cpfInserir.setBounds(20, 100, 166, 40);
        cpfInserir.setEditable(true);

        JLabel dataTitulo = new JLabel();
        dataTitulo.setText("Digite a data de nascimento do cliente");
        dataTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        dataTitulo.setBounds(20, 140, 300, 20);

        JTextField dataInserir = new JTextField();
        dataInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        dataInserir.setBounds(20, 160, 120, 40);
        dataInserir.setEditable(true);



        JLabel dataTreinoTitulo = new JLabel();
        dataTreinoTitulo.setText("Digite a data de inicio do plano do cliente");
        dataTreinoTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        dataTreinoTitulo.setBounds(20, 250, 300, 20);

        JTextField dataTreinoInserir = new JTextField();
        dataTreinoInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        dataTreinoInserir.setBounds(20, 270, 120, 40);
        dataTreinoInserir.setEditable(true);

        JLabel cartaoTitulo = new JLabel();
        cartaoTitulo.setText("Digite o número do cartão do cliente");
        cartaoTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        cartaoTitulo.setBounds(20, 310, 300, 20);

        JTextField cartaoInserir = new JTextField();
        cartaoInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        cartaoInserir.setBounds(20, 330, 166, 40);
        cartaoInserir.setEditable(true);

        JLabel dataCartaoTitulo = new JLabel();
        dataCartaoTitulo.setText("Data de vencimento");
        dataCartaoTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        dataCartaoTitulo.setBounds(20, 370, 300, 20);

        JTextField dataCartaoInserir = new JTextField();
        dataCartaoInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        dataCartaoInserir.setBounds(20, 390, 60, 40);
        dataCartaoInserir.setEditable(true);

        JLabel cvvTitulo = new JLabel();
        cvvTitulo.setText("CVV");
        cvvTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        cvvTitulo.setBounds(20, 430, 300, 20);

        JTextField cvvInserir = new JTextField();
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
        frameCliente.add(dataTreinoTitulo);
        frameCliente.add(dataTreinoInserir);
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
        frameCliente.setVisible(true);


        frameCliente.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
                gui.updatePaginaDoisAbrir(0);
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
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==planoSimples)
        {
            planoSimples.setEnabled(false);
            planoGold.setEnabled(true);
            planoPremium.setEnabled(true);
        }
        if (e.getSource() == planoGold) {
            planoGold.setEnabled(false);
            planoSimples.setEnabled(true);
            planoPremium.setEnabled(true);
        }
        if (e.getSource() == planoPremium) {
            planoPremium.setEnabled(false);
            planoSimples.setEnabled(true);
            planoGold.setEnabled(true);
        }
    }
}
