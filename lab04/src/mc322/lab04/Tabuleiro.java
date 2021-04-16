import java.lang.Math;
import java.util.ArrayList;

public class Tabuleiro {
    Peca[] setup;

    int inserirLinhaTab(int brEsq, int pEsq, int buracos, int pDir, int brDir, int posInic) {
        Peca nova;
        int pos = posInic;
        for (int i = 0; i < brEsq; i++) {
            nova = new Peca(TipoCasa.BRANCO);
            setup[pos] = nova;
            pos += 1;
        }
        for (int i = 0; i < pEsq; i++) {
            nova = new Peca(TipoCasa.PECA);
            setup[pos] = nova;
            pos += 1;
        }
        for (int i = 0; i < buracos; i++) {
            setup[pos] = null;
            pos += 1;
        }
        for (int i = 0; i < pDir; i++) {
            nova = new Peca(TipoCasa.PECA);
            setup[pos] = nova;
            pos += 1;
        }
        for (int i = 0; i < brDir; i++) {
            nova = new Peca(TipoCasa.BRANCO);
            setup[pos] = nova;
            pos += 1;
        }
        return pos;
    }

    void preencherCoordPecas () {
        char linhaPeca,
             colunaPeca;
        for (int i = 0; i < 49; i++) {
            linhaPeca = (char)('7' - i/7);
            colunaPeca = (char)('a' + i%7);
            if (setup[i] != null) {
                setup[i].linha = linhaPeca;
                setup[i].coluna = colunaPeca;
            }
        }
    }

    Tabuleiro () {
        setup = new Peca[49];
        int ref = 0;
        ref = inserirLinhaTab(2, 3, 0, 0, 2, ref);
        ref = inserirLinhaTab(2, 3, 0, 0, 2, ref);
        ref = inserirLinhaTab(0, 7, 0, 0, 0, ref);
        ref = inserirLinhaTab(0, 3, 1, 3, 0, ref);
        ref = inserirLinhaTab(0, 7, 0, 0, 0, ref);
        ref = inserirLinhaTab(2, 3, 0, 0, 2, ref);
        inserirLinhaTab(2, 3, 0, 0, 2, ref);
        preencherCoordPecas();
    }

    void imprimirTabuleiro() {
        int pos = 0;
        Peca casa;
        for (int i = 0; i < 7; i++) {
            System.out.print(7 - i);
            for (int j = 0; j < 7; j++) {
                casa = setup[pos];
                System.out.print(" ");
                if (casa == null) {
                    System.out.print("-");
                } else {
                    if (casa.tipoC == TipoCasa.BRANCO) {
                        System.out.print(" ");
                    } else {
                        System.out.print("P");
                    }
                }
                pos += 1;
            }
            System.out.print("\n");
        }
        System.out.println("  a b c d e f g");
    }

    void imprimirPosicoes() {
        int pos = 0;
        Peca casa;
        String coord;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                casa = setup[pos];
                if (casa == null) {
                    System.out.print("--");
                } else {
                    coord = "" + casa.coluna + casa.linha;
                    System.out.print(coord);
                }
                System.out.print(" ");
                pos += 1;
            }
            System.out.print("\n");
        }
    }
}