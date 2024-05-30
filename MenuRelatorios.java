package paradigmasTrabalhoUm.GUI;


import paradigmasTrabalhoUm.Database.DatabaseMetodos;
import paradigmasTrabalhoUm.Relatorios.DatasComparecimento;
import paradigmasTrabalhoUm.Relatorios.RelatorioCargas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class MenuRelatorios implements ActionListener {
    String nomeSelecionado;
    String nomeProprio;
    String dataNascimento;
    int plano;
    JButton datasComparecimento;
    JButton progressaoCargas;
    JFrame menuRelatorios;
    JLabel nomeProprioLabel;
    JLabel cpfLabel;
    JLabel dataNascimentoLabel;
    Connection conexao = DatabaseMetodos.conectaDb();

    MenuRelatorios() {
        MenuClientes menuClientes = new MenuClientes(null, null, this, 2);
    }

    public void recuperaInformacoes() {
        List<Object[]> infoResgatada = DatabaseMetodos.retornaInfoClientes(conexao, nomeSelecionado);
        for (Object[] resultado : infoResgatada) {
            nomeProprio = (String) resultado[1];
            dataNascimento = (String) resultado[2];
            plano = (int) resultado[8];
        }
        abreMenuRelatorios();
    }

    private void abreMenuRelatorios() {
        String tipoPlano = null;
        menuRelatorios = new JFrame();
        menuRelatorios.setSize(350, 230);
        menuRelatorios.setTitle(nomeProprio);
        menuRelatorios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuRelatorios.setLayout(null);
        menuRelatorios.setResizable(false);

        JLabel cabecalhoRelatorios = new JLabel();
        cabecalhoRelatorios.setOpaque(true);
        cabecalhoRelatorios.setBounds(0, 0, 350, 80);
        menuRelatorios.add(cabecalhoRelatorios);

        nomeProprioLabel = new JLabel();
        nomeProprioLabel.setBackground(new Color(0xffffff));
        nomeProprioLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nomeProprioLabel.setForeground(Color.WHITE);
        nomeProprioLabel.setBounds(10, 0, 200, 40);
        nomeProprioLabel.setText(nomeProprio);
        cabecalhoRelatorios.add(nomeProprioLabel);

        cpfLabel = new JLabel();
        cpfLabel.setBackground(new Color(0xffffff));
        cpfLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        cpfLabel.setForeground(Color.WHITE);
        cpfLabel.setBounds(10, 18, 200, 40);
        cpfLabel.setText(nomeSelecionado);
        cabecalhoRelatorios.add(cpfLabel);

        JLabel cabecalhoTierMembro = new JLabel();
        cabecalhoTierMembro.setOpaque(true);
        cabecalhoTierMembro.setBounds(265, 10, 60, 20);
        cabecalhoRelatorios.add(cabecalhoTierMembro);

        dataNascimentoLabel = new JLabel();
        dataNascimentoLabel.setBackground(new Color(0xffffff));
        dataNascimentoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        dataNascimentoLabel.setForeground(Color.WHITE);
        dataNascimentoLabel.setBounds(10, 36, 200, 40);
        dataNascimentoLabel.setText(dataNascimento);
        cabecalhoRelatorios.add(dataNascimentoLabel);

        JLabel planoTier = new JLabel();
        planoTier.setBounds(5, 0, 60, 20);
        cabecalhoTierMembro.add(planoTier);

        switch (plano) {
            case 1:
                tipoPlano = "Simples";
                planoTier.setFont(new Font("Arial", Font.PLAIN, 14));
                planoTier.setForeground(Color.white);
                cabecalhoTierMembro.setBackground(Color.darkGray);
                cabecalhoRelatorios.setBackground(new Color(0x0c5759));
                break;
            case 2:
                tipoPlano = "Gold";
                planoTier.setFont(new Font("Italic", Font.PLAIN, 14));
                planoTier.setForeground(new Color(0xc79306));
                cabecalhoTierMembro.setBackground(Color.WHITE);
                cabecalhoRelatorios.setBackground(new Color(0xc79306));
                break;
            case 3:
                tipoPlano = "Premium";
                planoTier.setFont(new Font("Serif", Font.PLAIN, 14));
                planoTier.setForeground(Color.BLACK);
                cabecalhoTierMembro.setBackground(Color.WHITE);
                cabecalhoRelatorios.setBackground(new Color(0xfab111e));
                break;

        }

        planoTier.setText(tipoPlano);

        datasComparecimento = new JButton();
        datasComparecimento.setBounds(60, 100, 200, 30);
        datasComparecimento.setText("Datas comparecidas");
        datasComparecimento.addActionListener(this);
        menuRelatorios.add(datasComparecimento);
        menuRelatorios.setVisible(true);

        progressaoCargas = new JButton();
        progressaoCargas.setBounds(60, 135, 200, 30);
        progressaoCargas.setText("Progressão de carga");
        progressaoCargas.addActionListener(this);
        menuRelatorios.add(progressaoCargas);
        menuRelatorios.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == progressaoCargas) {
            RelatorioCargas relatorioCargas = new RelatorioCargas(nomeSelecionado);
        }
        if (e.getSource() == datasComparecimento) {
            DatasComparecimento datasComparecimento = new DatasComparecimento(nomeSelecionado);
        }
    }
}
