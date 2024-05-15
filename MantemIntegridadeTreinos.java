package paradigmasTrabalhoUm;

import javax.swing.*;

public class MantemIntegridadeTreinos {
    ChecaConcordancia checaConcordancia = new ChecaConcordancia();
    public boolean mantemIntegridade(String string) {
        if (string.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Séries ou repetições estão vazias");
            return false;
        }
        return checaConcordancia.checaConcordancia(null, string, 1, 0, 1);

    }
}
