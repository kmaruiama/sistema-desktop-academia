package paradigmasTrabalhoUm;

public class ConverteBackspaceClasse {
    public String converteBackspace(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char espaco : string.toCharArray()) {
            if (espaco == ' ') {
                stringBuilder.append('_');
            } else {
                stringBuilder.append(espaco);
            }
        }
        return stringBuilder.toString();
    }
}
