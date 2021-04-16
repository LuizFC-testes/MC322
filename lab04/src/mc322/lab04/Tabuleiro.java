import java.lang.Math;

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

    int extrairCoordenada (String coord) {
        // coord contém uma letra seguida de um número
        return ('7' - coord.charAt(1)) * 8 + (coord.charAt(0) - 'a');
    }
    
    boolean passoEhValido (String movimento) {
        int ini = extrairCoordenada(movimento.substring(0, 2)),
            fim = extrairCoordenada(movimento.substring(3)),
            distancia = Math.abs(fim - ini);
            /*System.out.print("ini: " + movimento.substring(0,2) + " ");
            System.out.print("fim: " + movimento.substring(3) + " ");
            System.out.print("Inicio: " + ini + " Fim: " + fim + " ");*/
        if (ini < 64 && fim < 64 && ini >= 0 && fim >= 0 && ini%8 != 7 && fim%8 != 7) {
            //System.out.print(" 1 ");
            if (distancia == 16 || distancia == 2) {
                //System.out.print(" 2 ");
                if (setup[fim] == '-' && setup[(fim + ini)/2] == 'P' && setup[ini] == 'P') {
                    //System.out.print(" 3 ");
                    return true;
                }
            }
        }
        return false;
    }

    void inverterConfig(int coord) {
        // Pressupõe que a jogada é válida
        if (setup[coord] == 'P') {
            setup[coord] = '-';
        } else {
            setup[coord] = 'P';
        }
    }

    void comerPeca(int ini, int fim) {
        inverterConfig(ini);
        inverterConfig((ini + fim)/2);
        inverterConfig(fim);
    }

    void jogar(String[] movimentos) {
        System.out.println("Tabuleiro inicial:");
        imprimirTabuleiro();
        int qtdJogadas = movimentos.length, ini, fim;
        for (int i = 0; i < qtdJogadas; i++) {
            System.out.println("\nSource: " + movimentos[i].substring(0,2));
            System.out.println("Target: " + movimentos[i].substring(3));
            if (passoEhValido(movimentos[i])) {
                ini = extrairCoordenada(movimentos[i].substring(0,2));
                fim = extrairCoordenada(movimentos[i].substring(3));
                comerPeca(ini, fim);
            }
            imprimirTabuleiro();
        }
    }
}