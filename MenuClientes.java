package isso;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuClientes
{
    Gui gui;
    JFrame menuCliente = new JFrame();
    MenuClientes (Gui gui) 
    {
        this.gui = gui;

        this.gui = gui;
        menuCliente.setSize(500, 600);
        menuCliente.setTitle("Adicionar cliente");
        menuCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuCliente.setLayout(null);
        menuCliente.setResizable(false);
        menuCliente.setVisible(true);

        menuCliente.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
                gui.updateMenuClientesAbrir(0);
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
    
    private void setupActionListeners ()
    {
    
    }
}
