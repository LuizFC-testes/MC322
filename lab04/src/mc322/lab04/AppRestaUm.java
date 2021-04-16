import java.util.ArrayList;

public class AppRestaUm {
    /*public static ArrayList executaJogo (String caminho) {
        CSVReader csv = new CSVReader();
	    csv.setDataSource(caminho);
        String commands[] = csv.requestCommands();
        Tabuleiro tab = new Tabuleiro();
        ArrayList instancias = tab.jogar(commands);
        return instancias;
    }*/

    public static void main (String []args) {
        Tabuleiro tab = new Tabuleiro();
        tab.imprimirTabuleiro();
        tab.imprimirPosicoes();
        tab.comerPeca("d6", "d4");
        tab.imprimirTabuleiro();
        tab.imprimirPosicoes();
    }
}