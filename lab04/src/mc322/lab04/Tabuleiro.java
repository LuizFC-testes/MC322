public class Tabuleiro {
    char[] setup;

    public char[] transcreverStringParaVetor (char[] vetor, String s, int baseInicial, int vezes) {
        // Tamanho das strings padrão como 8
        int base = baseInicial;
        for (int i = 0; i < vezes; i++) {
            for (int j = 0; j < 8; j++) {
                vetor[j + base] = s.charAt(j);
            }
            base += 8;
        }
        return vetor;
    }

    Tabuleiro () {
        String a = "  PPP  \n",
               b = "PPPPPPP\n",
               c = "PPP-PPP\n";
        char[] set = new char[64];
        int base = 0;
        set = transcreverStringParaVetor (set, a, base, 2);
        base += 2 * 8;
        set = transcreverStringParaVetor (set, b, base, 1);
        base += 8;
        set = transcreverStringParaVetor (set, c, base, 1);
        base += 8;
        set = transcreverStringParaVetor (set, b, base, 1);
        base += 8;
        set = transcreverStringParaVetor (set, a, base, 2);
        setup = set;
    }

    void imprimirTabuleiro () {
        int base = 0;
        for (int i = 0; i < 7; i++) {
            System.out.print(7 - i);
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + setup[j + base]);
            }
            base += 8;
        }
        System.out.println("  a b c d e f g");
    }
}