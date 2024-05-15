package paradigmasTrabalhoUm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventoTreino implements ActionListener {
    MenuPrincipal menuPrincipal;
    MantemIntegridadeTreinos mantemIntegridadeTreinos = new MantemIntegridadeTreinos();
    ChecaConcordancia checaConcordancia;

    int inputValido;
    JFrame eventoTreino = new JFrame();
    String nomeSelecionado;
    String treinoSelecionado;
    String treinoSelecionadoUnderline;

    String inputFinal;
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

    JButton finalizarTreino;

    Boolean dataValidada;
    Boolean cargasValidadas;

    int numeroExercicios;

    /*retorna um row a cada 3 slots do array*/
    int[] exerciciosInfo;

    Dimension dimensaoFrame;


    EventoTreino(MenuPrincipal menuPrincipal) {
        databaseMetodos.createEventoTable(conexao);
        this.menuPrincipal = menuPrincipal;
        MenuClientes menuClientes = new MenuClientes(null, this, null, 1);


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
        dimensaoFrame = new Dimension();

        switch (numeroExercicios) {
            case 1:
                dimensaoFrame.setSize(450, 155);
                break;
            case 2:
                dimensaoFrame.setSize(450, 270);
                break;
            case 3:
                dimensaoFrame.setSize(450, 380);
                break;
            case 4:
                dimensaoFrame.setSize(450, 480);
                break;
            case 5:
                dimensaoFrame.setSize(450, 575);
                break;
            case 6:
                dimensaoFrame.setSize(450, 670);
                break;
        }

        eventoTreino.setSize(dimensaoFrame);
        eventoTreino.setLayout(null);
        eventoTreino.setResizable(false);

        dataLabel = new JLabel();
        dataLabel.setText("<html>Insira a data do treino<br>no formato dd/mm/aaaa</html>");
        dataLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        dataLabel.setBounds(300, 5, 120, 50);

        dataInserir = new JTextField();
        dataInserir.setEditable(true);
        dataInserir.setFont(new Font("Arial", Font.PLAIN, 15));
        dataInserir.setBounds(300, 45, 120, 30);


        eventoTreino.add(dataLabel);
        eventoTreino.add(dataInserir);

        inicializaExercicios();

        eventoTreino.setVisible(true);
    }


    private void inicializaExercicios() {
        exerciciosInfo = databaseMetodos.retornaInfoExercicios(conexao, treinoSelecionadoUnderline);

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
        if (numeroExercicios >= 3) {
            exercicioTres = new JLabel();
            exercicioTres.setText(tituloExercicioLabel(exerciciosInfo[6]));
            exercicioTres.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioTres.setBounds(10, 210, 300, 40);
            eventoTreino.add(exercicioTres);

            exercicioTresSeries = new JLabel();
            exercicioTresSeries.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioTresSeries.setText(exerciciosInfo[7] + " séries e");
            exercicioTresSeries.setBounds(10, 230, 100, 40);
            eventoTreino.add(exercicioTresSeries);

            exercicioTresReps = new JLabel();
            exercicioTresReps.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioTresReps.setText(exerciciosInfo[8] + " repetições");
            exercicioTresReps.setBounds(10, 245, 100, 40);
            eventoTreino.add(exercicioTresReps);

            exercicioTresCarga = new JTextField();
            exercicioTresCarga.setEditable(true);
            exercicioTresCarga.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioTresCarga.setBounds(10, 280, 50, 30);
            eventoTreino.add(exercicioTresCarga);

            tresQuilos = new JLabel();
            tresQuilos.setFont(new Font("Arial", Font.PLAIN, 12));
            tresQuilos.setText("Kilogramas");
            tresQuilos.setBounds(65, 280, 80, 30);
            eventoTreino.add(tresQuilos);
        }
        if (numeroExercicios >= 4) {
            exercicioQuarto = new JLabel();
            exercicioQuarto.setText(tituloExercicioLabel(exerciciosInfo[9]));
            exercicioQuarto.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioQuarto.setBounds(10, 310, 300, 40);
            eventoTreino.add(exercicioQuarto);

            exercicioQuatroSeries = new JLabel();
            exercicioQuatroSeries.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioQuatroSeries.setText(exerciciosInfo[10] + " séries e");
            exercicioQuatroSeries.setBounds(10, 330, 100, 40);
            eventoTreino.add(exercicioQuatroSeries);

            exercicioQuatroReps = new JLabel();
            exercicioQuatroReps.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioQuatroReps.setText(exerciciosInfo[11] + " repetições");
            exercicioQuatroReps.setBounds(10, 345, 100, 40);
            eventoTreino.add(exercicioQuatroReps);

            exercicioQuatroCarga = new JTextField();
            exercicioQuatroCarga.setEditable(true);
            exercicioQuatroCarga.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioQuatroCarga.setBounds(10, 380, 50, 30);
            eventoTreino.add(exercicioQuatroCarga);

            quatroQuilos = new JLabel();
            quatroQuilos.setFont(new Font("Arial", Font.PLAIN, 12));
            quatroQuilos.setText("Kilogramas");
            quatroQuilos.setBounds(65, 380, 80, 30);
            eventoTreino.add(quatroQuilos);
        }
        if (numeroExercicios >= 5) {
            exercicioCinco = new JLabel();
            exercicioCinco.setText(tituloExercicioLabel(exerciciosInfo[12]));
            exercicioCinco.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioCinco.setBounds(10, 410, 300, 40);
            eventoTreino.add(exercicioCinco);

            exercicioCincoSeries = new JLabel();
            exercicioCincoSeries.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioCincoSeries.setText(exerciciosInfo[13] + " séries e");
            exercicioCincoSeries.setBounds(10, 430, 100, 40);
            eventoTreino.add(exercicioCincoSeries);

            exercicioCincoReps = new JLabel();
            exercicioCincoReps.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioCincoReps.setText(exerciciosInfo[14] + " repetições");
            exercicioCincoReps.setBounds(10, 445, 100, 40);
            eventoTreino.add(exercicioCincoReps);

            exercicioCincoCarga = new JTextField();
            exercicioCincoCarga.setEditable(true);
            exercicioCincoCarga.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioCincoCarga.setBounds(10, 480, 50, 30);
            eventoTreino.add(exercicioCincoCarga);

            cincoQuilos = new JLabel();
            cincoQuilos.setFont(new Font("Arial", Font.PLAIN, 12));
            cincoQuilos.setText("Kilogramas");
            cincoQuilos.setBounds(65, 480, 80, 30);
            eventoTreino.add(cincoQuilos);
        }
        if (numeroExercicios >= 6) {
            exercicioSeis = new JLabel();
            exercicioSeis.setText(tituloExercicioLabel(exerciciosInfo[15]));
            exercicioSeis.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioSeis.setBounds(10, 510, 300, 40);
            eventoTreino.add(exercicioSeis);

            exercicioSeisSeries = new JLabel();
            exercicioSeisSeries.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioSeisSeries.setText(exerciciosInfo[16] + " séries e");
            exercicioSeisSeries.setBounds(10, 530, 100, 40);
            eventoTreino.add(exercicioSeisSeries);

            exercicioSeisReps = new JLabel();
            exercicioSeisReps.setFont(new Font("Arial", Font.PLAIN, 12));
            exercicioSeisReps.setText(exerciciosInfo[17] + " repetições");
            exercicioSeisReps.setBounds(10, 545, 100, 40);
            eventoTreino.add(exercicioSeisReps);

            exercicioSeisCarga = new JTextField();
            exercicioSeisCarga.setEditable(true);
            exercicioSeisCarga.setFont(new Font("Arial", Font.PLAIN, 15));
            exercicioSeisCarga.setBounds(10, 580, 50, 30);
            eventoTreino.add(exercicioSeisCarga);

            seisQuilos = new JLabel();
            seisQuilos.setFont(new Font("Arial", Font.PLAIN, 12));
            seisQuilos.setText("Kilogramas");
            seisQuilos.setBounds(65, 580, 80, 30);
            eventoTreino.add(seisQuilos);
        }

        finalizarTreino = new JButton();
        finalizarTreino.setBounds(300, 80, 120, 30);
        finalizarTreino.setBackground(new Color(0x93DC5C));
        finalizarTreino.setText("CRIAR TREINO");
        finalizarTreino.addActionListener(this);
        eventoTreino.add(finalizarTreino);

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
            case 26:
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == finalizarTreino) {
            mantemIntegridadeData();
            mantemIntegridadeCargas();
            if (dataValidada && cargasValidadas) {
                inputaDatabaseEventos();
            }
        }

    }

    public void mantemIntegridadeData() {
        if (mantemIntegridadeTreinos.mantemIntegridade(dataInserir.getText())) {
            dataValidada = checaFormatoDateTime(dataInserir.getText());
            if (!dataValidada) {
                JOptionPane.showMessageDialog(eventoTreino, "<html>Data inválida de acordo com o padrão<br> " +
                        "insira novamente</html>", "Erro", JOptionPane.ERROR_MESSAGE);
                dataInserir.setText("");
            } else {
                sqlConversor(dataInserir.getText());
            }
        }
    }

    public void mantemIntegridadeCargas() {
        inputValido = 0;
        if (numeroExercicios >= 1) {
            if (mantemIntegridadeTreinos.mantemIntegridade(exercicioUmCarga.getText())) {
                inputValido++;
            } else {
                exercicioUmCarga.setText("");
            }
        }
        if (numeroExercicios >= 2) {
            if (mantemIntegridadeTreinos.mantemIntegridade(exercicioDoisCarga.getText())) {
                inputValido++;
            } else {
                exercicioDoisCarga.setText("");
            }
        }
        if (numeroExercicios >= 3) {
            if (mantemIntegridadeTreinos.mantemIntegridade(exercicioTresCarga.getText())) {
                inputValido++;
            } else {
                exercicioTresCarga.setText("");
            }
        }
        if (numeroExercicios >= 4) {
            if (mantemIntegridadeTreinos.mantemIntegridade(exercicioQuatroCarga.getText())) {
                inputValido++;
            } else {
                exercicioQuatroCarga.setText("");
            }
        }
        if (numeroExercicios >= 5) {
            if (mantemIntegridadeTreinos.mantemIntegridade(exercicioCincoCarga.getText())) {
                inputValido++;
            } else {
                exercicioCincoCarga.setText("");
            }
        }
        if (numeroExercicios >= 6) {
            if (mantemIntegridadeTreinos.mantemIntegridade(exercicioSeisCarga.getText())) {
                inputValido++;
            } else {
                exercicioSeisCarga.setText("");
            }
        }
        if (numeroExercicios == inputValido) {
            cargasValidadas = true;
        } else {
            cargasValidadas = false;
        }
    }

    private boolean checaFormatoDateTime(String string) {
        String dateFormatPattern = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(string);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }



    private void sqlConversor(String inputData) {
        DateFormat guiFormato = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sqlFormato = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(guiFormato.parse(inputData));
        } catch (ParseException e) {
            dataValidada = false;
        }
        inputFinal = sqlFormato.format(calendar.getTime());
    }

    private void inputaDatabaseEventos() {

        if (numeroExercicios >= 1) {
            databaseMetodos.insertEvento(conexao, treinoSelecionado, nomeSelecionado, exerciciosInfo[0],
                    exerciciosInfo[1], exerciciosInfo[2], Integer.parseInt(exercicioUmCarga.getText()), inputFinal);
        }
        if (numeroExercicios >= 2) {
            databaseMetodos.insertEvento(conexao, treinoSelecionado, nomeSelecionado, exerciciosInfo[3],
                    exerciciosInfo[4], exerciciosInfo[5], Integer.parseInt(exercicioDoisCarga.getText()), inputFinal);
        }
        if (numeroExercicios >= 3) {
            databaseMetodos.insertEvento(conexao, treinoSelecionado, nomeSelecionado, exerciciosInfo[6],
                    exerciciosInfo[7], exerciciosInfo[8], Integer.parseInt(exercicioTresCarga.getText()), inputFinal);
        }
        if (numeroExercicios >= 4) {
            databaseMetodos.insertEvento(conexao, treinoSelecionado, nomeSelecionado, exerciciosInfo[9],
                    exerciciosInfo[10], exerciciosInfo[11], Integer.parseInt(exercicioQuatroCarga.getText()), inputFinal);
        }
        if (numeroExercicios >= 5) {
            databaseMetodos.insertEvento(conexao, treinoSelecionado, nomeSelecionado, exerciciosInfo[12],
                    exerciciosInfo[13], exerciciosInfo[14], Integer.parseInt(exercicioCincoCarga.getText()), inputFinal);
        }
        if (numeroExercicios >= 6) {
            databaseMetodos.insertEvento(conexao, treinoSelecionado, nomeSelecionado, exerciciosInfo[15],
                    exerciciosInfo[16], exerciciosInfo[17], Integer.parseInt(exercicioSeisCarga.getText()), inputFinal);
        }
        JOptionPane.showMessageDialog(null, "Cliente treinado com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        eventoTreino.dispose();
    }

}



