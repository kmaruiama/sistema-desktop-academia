package isso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.Calendar;

public class EventoTreino {
    MenuPrincipal menuPrincipal;
    JFrame eventoTreino = new JFrame();
    String nomeSelecionado;
    String treinoSelecionado;
    String treinoSelecionadoUnderline;
    DatabaseMetodos databaseMetodos = new DatabaseMetodos();
    Connection conexao = databaseMetodos.conectaDb("paradigmas_database", "postgres", "admin");
    JLabel dataLabel;
    JTextField dataInserir;
    JLabel exercicioUm;
    JLabel exercicioDois;
    JLabel exercicioTres;
    JLabel exercicioQuarto;
    JLabel exercicioCinco;
    JLabel exercicioSeis;
    JLabel exercicioUmReps;
    JLabel exercicioDoisReps;
    JLabel exercicioTresReps;
    JLabel exercicioQuatroReps;
    JLabel exercicioCincoReps;
    JLabel exercicioSeisReps;
    JLabel exercicioUmSeries;
    JLabel exercicioDoisSeries;
    JLabel exercicioTresSeries;
    JLabel exercicioQuatroSeries;
    JLabel exercicioCincoSeries;
    JLabel exercicioSeisSeries;
    JLabel umQuilos;
    JLabel doisQuilos;
    JLabel tresQuilos;
    JLabel quatroQuilos;
    JLabel cincoQuilos;
    JLabel seisQuilos;
    JTextField exercicioUmCarga;
    JTextField exercicioDoisCarga;
    JTextField exercicioTresCarga;
    JTextField exercicioQuatroCarga;
    JTextField exercicioCincoCarga;
    JTextField exercicioSeisCarga;

    int numeroExercicios;

    /*retorna um row a cada 3 slots do array*/
    int[] exerciciosInfo;


    EventoTreino(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        MenuClientes menuClientes = new MenuClientes(null, this, 1);


        eventoTreino.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                menuPrincipal.updateEventoTreino(0);
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

    public void abreMenuTreinos() {
        MenuTreinos menuTreinos = new MenuTreinos(this);
    }

    public void abrirEventoTreino() {
        ConverteBackspaceClasse c = new ConverteBackspaceClasse();
        treinoSelecionadoUnderline = c.converteBackspace(treinoSelecionado);
        numeroExercicios = databaseMetodos.retornaNumeroExercicios(conexao, treinoSelecionadoUnderline);
        eventoTreino.setSize(new Dimension(500, 620));
        eventoTreino.setLayout(null);
        eventoTreino.setResizable(false);

        dataLabel = new JLabel();
        dataLabel.setText("<html>Insira a data do treino<br>no formato dd/mm/aaaa</html>");
        dataLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        dataLabel.setBounds(350, 10, 120, 50);

        dataInserir = new JTextField();
        dataInserir.setEditable(true);
        dataInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        dataInserir.setBounds(350, 50, 120, 30);


        eventoTreino.add(dataLabel);
        eventoTreino.add(dataInserir);

        inicializaExercicios();


        eventoTreino.setVisible(true);
    }


    private void inicializaExercicios() {
        exerciciosInfo = databaseMetodos.retornaInfoExercicios(conexao, treinoSelecionadoUnderline);
        for (int i : exerciciosInfo) {
            System.out.println(i);
        }

        if (numeroExercicios >= 1) {
            exercicioUm = new JLabel();
            exercicioUm.setText(tituloExercicioLabel(exerciciosInfo[0]));
            exercicioUm.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioUm.setBounds(10, 10, 300, 40);
            eventoTreino.add(exercicioUm);

            exercicioUmSeries = new JLabel();
            exercicioUmSeries.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioUmSeries.setText(exerciciosInfo[1] + " séries e");
            exercicioUmSeries.setBounds(10, 30, 100, 40);
            eventoTreino.add(exercicioUmSeries);

            exercicioUmReps = new JLabel();
            exercicioUmReps.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioUmReps.setText(exerciciosInfo[2] + " repetições");
            exercicioUmReps.setBounds(10, 45, 100, 40);
            eventoTreino.add(exercicioUmReps);

            exercicioUmCarga = new JTextField();
            exercicioUmCarga.setEditable(true);
            exercicioUmCarga.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioUmCarga.setBounds(10, 80, 50, 30);
            eventoTreino.add(exercicioUmCarga);

            umQuilos = new JLabel();
            umQuilos.setFont(new Font("Arial", Font.PLAIN, 12));
            umQuilos.setText("Kilogramas");
            umQuilos.setBounds(65, 80, 80, 30);
            eventoTreino.add(umQuilos);
        }

        if (numeroExercicios >= 2) {
            exercicioDois = new JLabel();
            exercicioDois.setText(tituloExercicioLabel(exerciciosInfo[3]));
            exercicioDois.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioDois.setBounds(10, 110, 300, 40);
            eventoTreino.add(exercicioDois);

            exercicioDoisSeries = new JLabel();
            exercicioDoisSeries.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioDoisSeries.setText(exerciciosInfo[4] + " séries e");
            exercicioDoisSeries.setBounds(10, 130, 100, 40);
            eventoTreino.add(exercicioDoisSeries);

            exercicioDoisReps = new JLabel();
            exercicioDoisReps.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioDoisReps.setText(exerciciosInfo[5] + " repetições");
            exercicioDoisReps.setBounds(10, 145, 100, 40);
            eventoTreino.add(exercicioDoisReps);

            exercicioDoisCarga = new JTextField();
            exercicioDoisCarga.setEditable(true);
            exercicioDoisCarga.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioDoisCarga.setBounds(10, 180, 50, 30);
            eventoTreino.add(exercicioDoisCarga);

            doisQuilos = new JLabel();
            doisQuilos.setFont(new Font("Arial", Font.PLAIN, 12));
            doisQuilos.setText("Kilogramas");
            doisQuilos.setBounds(65, 180, 80, 30);
            eventoTreino.add(doisQuilos);
        }
        if (numeroExercicios >=3)
        {

        }
    }


    private String tituloExercicioLabel(int i) {
        String nome = null;
        switch (i) {
            case 1:
                nome = "Leg Press";
                break;
            case 5:
                nome = "Cadeira Adutora";
                break;
            case 20:
                nome = "Supino Máquina";
                break;
            case 25:
                nome = "Crucifixo Máquina";
                break;
            case 40:
                nome = "Abdominal Máquina";
                break;
            case 50:
                nome = "Desenvolvimento Máquina A.";
                break;
        }
        return nome;
    }
}



