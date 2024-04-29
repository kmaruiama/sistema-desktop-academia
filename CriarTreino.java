package isso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CriarTreino implements ActionListener {

    JFrame criarTreino = new JFrame();
    JButton legPress;
    JButton cadeiraAdutora;
    JButton supinoMaquina;
    JButton crucifixoMaquina;
    JButton abdominalMaquina;
    JButton desenvolvimentoMaquinaAberto;
    JButton reiniciarBotoes;
    CriarTreino()
    {
        criarTreino.setTitle("Criar treino");
        criarTreino.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        criarTreino.setResizable(true);
        criarTreino.setLayout(null);
        criarTreino.setSize(700,700);

        legPress = new JButton();
        legPress.addActionListener(this);
        legPress.setBounds(100, 50, 200, 50);
        legPress.setText("Leg Press");
        criarTreino.add(legPress);

        cadeiraAdutora = new JButton();
        cadeiraAdutora.addActionListener(this);
        cadeiraAdutora.setBounds(100,110,200,50);
        cadeiraAdutora.setText("Cadeira Adutora");
        criarTreino.add(cadeiraAdutora);

        supinoMaquina = new JButton();
        supinoMaquina.addActionListener(this);
        supinoMaquina.setBounds(100, 170, 200, 50);
        supinoMaquina.setText("Supino Máquina");
        criarTreino.add(supinoMaquina);

        crucifixoMaquina = new JButton();
        crucifixoMaquina.addActionListener(this);
        crucifixoMaquina.setBounds(100, 230, 200, 50);
        crucifixoMaquina.setText("Crucifixo Máquina");
        criarTreino.add(crucifixoMaquina);

        abdominalMaquina = new JButton();
        abdominalMaquina.addActionListener(this);
        abdominalMaquina.setBounds(100, 290, 200, 50);
        abdominalMaquina.setText("Abdominal Máquina");
        criarTreino.add(abdominalMaquina);

        desenvolvimentoMaquinaAberto = new JButton();
        desenvolvimentoMaquinaAberto.addActionListener(this);
        desenvolvimentoMaquinaAberto.setBounds(100, 350, 200, 50);
        desenvolvimentoMaquinaAberto.setText("Desenvolvimento Máquina");
        criarTreino.add(desenvolvimentoMaquinaAberto);

        criarTreino.setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {}

}
