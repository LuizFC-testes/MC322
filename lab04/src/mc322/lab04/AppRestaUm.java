public class AppRestaUm {
    public static void executaJogo (String caminho) {
        CSVReader csv = new CSVReader();
	    csv.setDataSource(caminho);
        String commands[] = csv.requestCommands();
        Tabuleiro tab = new Tabuleiro();
        tab.jogar(commands);
    }

    public static void main (String []args) {
        executaJogo("teste.csv");
    }
}