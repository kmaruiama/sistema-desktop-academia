package isso;
import javax.swing.*;

/* Essa classe serve simplesmente para checar a concordancia entre o input
oferecido pelo usuário e os campos do servidor, impede o usuário criar/
atualizar um campo para nulo, escrever letras em informações puramente
númericas e vice-versa
*/
public class ChecaConcordancia {

    public boolean checaConcordancia(JFrame frame, String stringVerificada, int campoErro, int operacao, int sourceConsulta)
    {
        String campoErroString = null;
        switch (campoErro)
        {
            case 1:
                campoErroString = "Nome";
                break;

            case 2:
                campoErroString = "CPF";
                break;

            case 3:
                campoErroString = "Data de nascimento";
                break;

            case 4:
                campoErroString = "Data de matrícula";
                break;

            case 5:
                campoErroString = "Número do cartao";
                break;

            case 6:
                campoErroString = "CVV";
                break;

            case 7:
                campoErroString = "Data de vencimento do cartão";
                break;
        }
        if (stringVerificada.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "Erro: no campo " + campoErroString + " input vazio");
            return false;
        }
        if (!stringVerificada.matches("[a-zA-Z\\s]+") && operacao == 1)
        {
            JOptionPane.showMessageDialog(frame, "Erro: o campo " + campoErroString + " caractere invalido (numero/simbolo)");
            return false; //erro, nao contem só letras no nome
        }
        if (stringVerificada.matches(".*[a-zA-Z].*") && operacao == 0)
        {
            if (sourceConsulta == 1 )
            {
                JOptionPane.showMessageDialog(frame, "Erro: o input possui letras");
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Erro: o campo " + campoErroString + "  possui letras");
            }
            return false; // erro, contem algum tipo de letra no numero
        }
        if (stringVerificada.matches(".*\\s.*") && sourceConsulta == 1)
        {
            JOptionPane.showMessageDialog(frame, "Erro: o input possui backspace");
            return false;
        }
        return true; // sucesso
    }
}
