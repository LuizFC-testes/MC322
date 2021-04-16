import java.util.ArrayList;

public class AppRestaUm {
    public static ArrayList<String> executaJogo (String caminho) {
        CSVReader csv = new CSVReader();
	    csv.setDataSource(caminho);
        String commands[] = csv.requestCommands();
        Tabuleiro tab = new Tabuleiro();
        ArrayList<String> instancias = tab.jogar(commands);
        return instancias;
    }

    public static void main (String []args) {
        ArrayList<String> lista = executaJogo ("teste.csv");
        System.out.print(lista.get(1));
        
    }
}