package isso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;


public class CriarTreino implements ActionListener {

    ConverteBackspaceClasse converteBackspaceClasse = new ConverteBackspaceClasse();
    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");
    StringBuilder queryTreino = new StringBuilder(200);
    JLabel exercicioTitulo;
    JLabel seriesTitulo;
    JLabel repsTitulo;
    JLabel tituloTreino;
    JFrame criarTreino = new JFrame();
    JButton legPress;
    JButton cadeiraAdutora;
    JButton supinoMaquina;
    JButton crucifixoMaquina;
    JButton abdominalMaquina;
    JButton desenvolvimentoMaquinaAberto;
    JButton reiniciarBotoes;
    JButton criarTudo;
    JButton avisoInsercao;
    JTextField legSeries;
    JTextField adutoraSeries;
    JTextField supinoSeries;
    JTextField crucifixoSeries;
    JTextField abdominalSeries;
    JTextField desenvolvimentoSeries;
    JTextField legReps;
    JTextField adutoraReps;
    JTextField supinoReps;
    JTextField crucifixoReps;
    JTextField abdominalReps;
    JTextField desenvolvimentoReps;
    JTextField inserirTitulo;
    boolean legPressionado;
    boolean adutoraPressionado;
    boolean supinoPressionado;
    boolean crucifixoPressionado;
    boolean abdominalPressionado;
    boolean desenvolvimentoPressionado;
    boolean editarTreino = false;
    MenuPrincipal menuPrincipal;
    CriarTreino(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        criarTreino.setTitle("Criar treino");
        criarTreino.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        criarTreino.setResizable(true);
        criarTreino.setLayout(null);
        criarTreino.setSize(530, 600);

        exercicioTitulo = new JLabel();
        exercicioTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        exercicioTitulo.setBounds(85, 20, 200, 20);
        exercicioTitulo.setText("Exercícios");
        criarTreino.add(exercicioTitulo);

        legPress = new JButton();
        legPress.addActionListener(this);
        legPress.setBounds(25, 50, 200, 50);
        legPress.setText("Leg Press");
        criarTreino.add(legPress);

        cadeiraAdutora = new JButton();
        cadeiraAdutora.addActionListener(this);
        cadeiraAdutora.setBounds(25, 110, 200, 50);
        cadeiraAdutora.setText("Cadeira Adutora");
        criarTreino.add(cadeiraAdutora);

        supinoMaquina = new JButton();
        supinoMaquina.addActionListener(this);
        supinoMaquina.setBounds(25, 170, 200, 50);
        supinoMaquina.setText("Supino Máquina");
        criarTreino.add(supinoMaquina);

        crucifixoMaquina = new JButton();
        crucifixoMaquina.addActionListener(this);
        crucifixoMaquina.setBounds(25, 230, 200, 50);
        crucifixoMaquina.setText("Crucifixo Máquina");
        criarTreino.add(crucifixoMaquina);

        abdominalMaquina = new JButton();
        abdominalMaquina.addActionListener(this);
        abdominalMaquina.setBounds(25, 290, 200, 50);
        abdominalMaquina.setText("Abdominal Máquina");
        criarTreino.add(abdominalMaquina);

        desenvolvimentoMaquinaAberto = new JButton();
        desenvolvimentoMaquinaAberto.addActionListener(this);
        desenvolvimentoMaquinaAberto.setBounds(25, 350, 200, 50);
        desenvolvimentoMaquinaAberto.setText("Desenvolvimento Máquina");
        criarTreino.add(desenvolvimentoMaquinaAberto);

        reiniciarBotoes = new JButton();
        reiniciarBotoes.addActionListener(this);
        reiniciarBotoes.setBounds(25, 420, 200, 50);
        reiniciarBotoes.setText("REINICIAR SELEÇÃO");
        criarTreino.add(reiniciarBotoes);

        avisoInsercao = new JButton();
        avisoInsercao.setBounds(295, 490, 100, 50);
        avisoInsercao.addActionListener(this);
        avisoInsercao.setText("Aviso");
        avisoInsercao.setBackground(new Color(0xffff37));
        criarTreino.add(avisoInsercao);

        seriesTitulo = new JLabel();
        seriesTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        seriesTitulo.setBounds(272, 20, 200, 20);
        seriesTitulo.setText("Séries");
        criarTreino.add(seriesTitulo);

        legSeries = new JTextField();
        legSeries.setFont(new Font("Arial", Font.PLAIN, 15));
        legSeries.setBounds(250, 50, 50, 50);
        legSeries.setEditable(true);
        criarTreino.add(legSeries);

        adutoraSeries = new JTextField();
        adutoraSeries.setFont(new Font("Arial", Font.PLAIN, 15));
        adutoraSeries.setBounds(250, 110, 50, 50);
        adutoraSeries.setEditable(true);
        criarTreino.add(adutoraSeries);

        supinoSeries = new JTextField();
        supinoSeries.setFont(new Font("Arial", Font.PLAIN, 15));
        supinoSeries.setBounds(250, 170, 50, 50);
        supinoSeries.setEditable(true);
        criarTreino.add(supinoSeries);

        crucifixoSeries = new JTextField();
        crucifixoSeries.setFont(new Font("Arial", Font.PLAIN, 15));
        crucifixoSeries.setBounds(250, 230, 50, 50);
        crucifixoSeries.setEditable(true);
        criarTreino.add(crucifixoSeries);

        abdominalSeries = new JTextField();
        abdominalSeries.setFont(new Font("Arial", Font.PLAIN, 15));
        abdominalSeries.setBounds(250, 290, 50, 50);
        abdominalSeries.setEditable(true);
        criarTreino.add(abdominalSeries);

        desenvolvimentoSeries = new JTextField();
        desenvolvimentoSeries.setFont(new Font("Arial", Font.PLAIN, 15));
        desenvolvimentoSeries.setBounds(250, 350, 50, 50);
        desenvolvimentoSeries.setEditable(true);
        criarTreino.add(desenvolvimentoSeries);

        repsTitulo = new JLabel();
        repsTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        repsTitulo.setBounds(340, 20, 200, 20);
        repsTitulo.setText("Repetições");
        criarTreino.add(repsTitulo);

        legReps = new JTextField();
        legReps.setFont(new Font("Arial", Font.PLAIN, 15));
        legReps.setBounds(340, 50, 50, 50);
        legReps.setEditable(true);
        criarTreino.add(legReps);

        adutoraReps = new JTextField();
        adutoraReps.setFont(new Font("Arial", Font.PLAIN, 15));
        adutoraReps.setBounds(340, 110, 50, 50);
        adutoraReps.setEditable(true);
        criarTreino.add(adutoraReps);

        supinoReps = new JTextField();
        supinoReps.setFont(new Font("Arial", Font.PLAIN, 15));
        supinoReps.setBounds(340, 170, 50, 50);
        supinoReps.setEditable(true);
        criarTreino.add(supinoReps);

        crucifixoReps = new JTextField();
        crucifixoReps.setFont(new Font("Arial", Font.PLAIN, 15));
        crucifixoReps.setBounds(340, 230, 50, 50);
        crucifixoReps.setEditable(true);
        criarTreino.add(crucifixoReps);

        abdominalReps = new JTextField();
        abdominalReps.setFont(new Font("Arial", Font.PLAIN, 15));
        abdominalReps.setBounds(340, 290, 50, 50);
        abdominalReps.setEditable(true);
        criarTreino.add(abdominalReps);

        desenvolvimentoReps = new JTextField();
        desenvolvimentoReps.setFont(new Font("Arial", Font.PLAIN, 15));
        desenvolvimentoReps.setBounds(340, 350, 50, 50);
        desenvolvimentoReps.setEditable(true);
        criarTreino.add(desenvolvimentoReps);

        inserirTitulo = new JTextField();
        inserirTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        inserirTitulo.setBounds(250, 445, 200, 30);
        inserirTitulo.setEditable(true);
        criarTreino.add(inserirTitulo);

        tituloTreino = new JLabel();
        tituloTreino.setFont(new Font("Arial", Font.PLAIN, 15));
        tituloTreino.setBounds(250, 410, 200, 30);
        tituloTreino.setText("Digite o titulo do treino");
        criarTreino.add(tituloTreino);

        criarTudo = new JButton();
        criarTudo.setBounds(25, 480, 200, 50);
        criarTudo.setBackground(new Color(0x93DC5C));
        criarTudo.setText("CRIAR TREINO");
        criarTudo.addActionListener(this);
        criarTreino.add(criarTudo);

        criarTreino.setVisible(true);

        criarTreino.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
                menuPrincipal.updateCriarTreinoAbrir(0);
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
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == legPress) {
            legPressionado = true;
            legPress.setEnabled(false);
        }
        if (e.getSource() == cadeiraAdutora) {
            adutoraPressionado = true;
            cadeiraAdutora.setEnabled(false);
        }
        if (e.getSource() == supinoMaquina) {
            supinoPressionado = true;
            supinoMaquina.setEnabled(false);
        }
        if (e.getSource() == crucifixoMaquina) {
            crucifixoPressionado = true;
            crucifixoMaquina.setEnabled(false);
        }
        if (e.getSource() == abdominalMaquina) {
            abdominalPressionado = true;
            abdominalMaquina.setEnabled(false);
        }
        if (e.getSource() == desenvolvimentoMaquinaAberto) {
            desenvolvimentoPressionado = true;
            desenvolvimentoMaquinaAberto.setEnabled(false);
        }
        if (e.getSource() == avisoInsercao) {
            JOptionPane.showMessageDialog(criarTreino, "Se você esqueceu de inserir valores/clicar no botao em seu treino apenas\n" + "preencha as informacoes e clique novamente no botao de criar, reiniciar nao é necessario");
        }
        if (e.getSource() == reiniciarBotoes) {
            limpaGUI();
        }
        if (e.getSource() == criarTudo) {
            if (!databaseMetodos.checaTituloTreino(conexao, converteBackspaceClasse.converteBackspace(inserirTitulo.getText())) || editarTreino) {
                if (!editarTreino) {
                    databaseMetodos.createTableTreino(conexao, converteBackspaceClasse.converteBackspace(inserirTitulo.getText()));
                }
                if (legPressionado) {
                    if (!(legSeries.getText().isEmpty() || legReps.getText().isEmpty())) {
                        queryExercicios(1);
                    } else {
                        JOptionPane.showMessageDialog(criarTreino, "No Leg Press: séries ou repetições estão vazias");
                    }
                }
                if (adutoraPressionado) {
                    if (!(adutoraSeries.getText().isEmpty() || adutoraReps.getText().isEmpty())) {
                        queryExercicios(5);
                    } else {
                        JOptionPane.showMessageDialog(criarTreino, "Na Cadeira Adutora: séries ou repetições estão vazias");
                    }
                }
                if (supinoPressionado) {
                    if (!(supinoSeries.getText().isEmpty() || supinoReps.getText().isEmpty())) {
                        queryExercicios(20);
                    } else {
                        JOptionPane.showMessageDialog(criarTreino, "No Supino Máquina: séries ou repetições estão vazias");
                    }
                }
                if (crucifixoPressionado) {
                    if (!(crucifixoSeries.getText().isEmpty() || crucifixoReps.getText().isEmpty())) {
                        queryExercicios(26);
                    } else {
                        JOptionPane.showMessageDialog(criarTreino, "No Crucifixo Máquina: séries ou repetições estão vazias");
                    }
                }
                if (abdominalPressionado) {
                    if (!(abdominalSeries.getText().isEmpty() || abdominalReps.getText().isEmpty())) {
                        queryExercicios(40);
                    } else {
                        JOptionPane.showMessageDialog(criarTreino, "No Abdominal Máquina: séries ou repetições estão vazias");
                    }
                }
                int opcao = JOptionPane.showConfirmDialog(null, "Treino criado, você quer editar algo?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    editarTreino = true;
                }
                if (opcao == 1) {
                    databaseMetodos.createRowTreinoLista(conexao, converteBackspaceClasse.converteBackspace(inserirTitulo.getText()));
                    limpaGUI();
                    JOptionPane.showMessageDialog(criarTreino, "Treino criado com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(criarTreino, "Um treino com esse nome já existe, por favor substitua-o", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void queryExercicios(int i) {
        switch (i) {
            case 1:
                queryTreino.append("insert into " + converteBackspaceClasse.converteBackspace(inserirTitulo.getText()) + " ");
                queryTreino.append("(exercicioid, series, repeticoes) values ('01', ");
                querySeriesReps(1);
                legPressionado = false;
                break;

            case 5:
                queryTreino.append("insert into " + converteBackspaceClasse.converteBackspace(inserirTitulo.getText()) + " ");
                queryTreino.append("(exercicioid, series, repeticoes) values ('05', ");
                querySeriesReps(5);
                adutoraPressionado = false;
                break;

            case 20:
                queryTreino.append("insert into " + converteBackspaceClasse.converteBackspace(inserirTitulo.getText()) + " ");
                queryTreino.append("(exercicioid, series, repeticoes) values ('20', ");
                querySeriesReps(20);
                supinoPressionado = false;
                break;

            case 26:
                queryTreino.append("insert into " + converteBackspaceClasse.converteBackspace(inserirTitulo.getText()) + " ");
                queryTreino.append("(exercicioid, series, repeticoes) values ('26', ");
                querySeriesReps(26);
                crucifixoPressionado = false;
                break;

            case 40:
                queryTreino.append("insert into " + converteBackspaceClasse.converteBackspace(inserirTitulo.getText()) + " ");
                queryTreino.append("(exercicioid, series, repeticoes) values ('40', ");
                querySeriesReps(40);
                abdominalPressionado = false;
                break;

            case 50:
                queryTreino.append("insert into " + converteBackspaceClasse.converteBackspace(inserirTitulo.getText()) + " ");
                queryTreino.append("(exercicioid, series, repeticoes) values ('50', ");
                querySeriesReps(50);
                desenvolvimentoPressionado = false;
                break;
        }
    }

    private void querySeriesReps(int i) {
        String local;
        String queryFinal;
        switch (i) {
            case 1:
                local = "'" + legSeries.getText() + "', '" + legReps.getText() + "');";
                queryTreino.append(local);
                queryFinal = queryTreino.toString();
                databaseMetodos.createRowTreino(conexao, queryFinal);
                queryTreino.setLength(0);
                break;
            case 5:
                local = "'" + adutoraSeries.getText() + "', '" + adutoraReps.getText() + "');";
                queryTreino.append(local);
                queryFinal = queryTreino.toString();
                databaseMetodos.createRowTreino(conexao, queryFinal);
                queryTreino.setLength(0);
                break;
            case 20:
                local = "'" + supinoSeries.getText() + "', '" + supinoReps.getText() + "');";
                queryTreino.append(local);
                queryFinal = queryTreino.toString();
                databaseMetodos.createRowTreino(conexao, queryFinal);
                queryTreino.setLength(0);
                break;
            case 26:
                local = "'" + crucifixoSeries.getText() + "', '" + crucifixoReps.getText() + "');";
                queryTreino.append(local);
                queryFinal = queryTreino.toString();
                databaseMetodos.createRowTreino(conexao, queryFinal);
                queryTreino.setLength(0);
                break;
            case 40:
                local = "'" + abdominalSeries.getText() + "', '" + abdominalReps.getText() + "');";
                queryTreino.append(local);
                queryFinal = queryTreino.toString();
                databaseMetodos.createRowTreino(conexao, queryFinal);
                queryTreino.setLength(0);
                break;
            case 50:
                local = "'" + desenvolvimentoSeries.getText() + "', '" + desenvolvimentoReps.getText() + "');";
                queryTreino.append(local);
                queryFinal = queryTreino.toString();
                databaseMetodos.createRowTreino(conexao, queryFinal);
                queryTreino.setLength(0);
                break;
        }
    }

    private void limpaGUI() {
        legPressionado = false;
        adutoraPressionado = false;
        supinoPressionado = false;
        crucifixoPressionado = false;
        abdominalPressionado = false;
        desenvolvimentoPressionado = false;
        legPress.setEnabled(true);
        cadeiraAdutora.setEnabled(true);
        supinoMaquina.setEnabled(true);
        crucifixoMaquina.setEnabled(true);
        abdominalMaquina.setEnabled(true);
        desenvolvimentoMaquinaAberto.setEnabled(true);
        legSeries.setText("");
        legReps.setText("");
        adutoraSeries.setText("");
        adutoraReps.setText("");
        supinoSeries.setText("");
        supinoReps.setText("");
        crucifixoSeries.setText("");
        crucifixoReps.setText("");
        abdominalSeries.setText("");
        abdominalReps.setText("");
        desenvolvimentoSeries.setText("");
        desenvolvimentoReps.setText("");
        inserirTitulo.setText("");
        editarTreino = false;
    }
}

