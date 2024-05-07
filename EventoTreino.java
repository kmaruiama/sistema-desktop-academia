package isso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EventoTreino {
    MenuPrincipal menuPrincipal;
    JFrame eventoTreino = new JFrame();
    EventoTreino (MenuPrincipal menuPrincipal)
    {
        this.menuPrincipal = menuPrincipal;
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