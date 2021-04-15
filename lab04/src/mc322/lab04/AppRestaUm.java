public class AppRestaUm {
    public static void executaJogo (String caminho) {
        CSVReader csv = new CSVReader();
	    csv.setDataSource(caminho);
        String commands[] = csv.requestCommands();
    }

    public static void main (String []args) {
        Tabuleiro tab = new Tabuleiro();
        tab.imprimirTabuleiro();
        System.out.println(tab.extrairCoordenada("b3"));
    }
}