package isso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EventoTreino {
    MenuPrincipal menuPrincipal;
    JFrame eventoTreino = new JFrame();
    JList listaTreinos;
    String nomeSelecionado;
    String treinoSelecionado;
    String[] arrayTreinos;



    EventoTreino (MenuPrincipal menuPrincipal)
    {
        this.menuPrincipal = menuPrincipal;
        MenuClientes menuClientes = new MenuClientes(null, this, 1);
        MenuTreinos menuTreinos = new MenuTreinos(this);
        eventoTreino.setTitle("Criar treino");
        eventoTreino.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eventoTreino.setResizable(true);
        eventoTreino.setLayout(null);
        eventoTreino.setSize(530, 600);





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

}