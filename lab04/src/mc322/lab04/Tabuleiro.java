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

    int extrairCoordenada (String coord) {
        // coord contém uma letra seguida de um número
        return ('7' - coord.charAt(1)) * 7 + (coord.charAt(0) - 'a');
    }

    boolean casaExiste(String casa) {
        char letra = casa.charAt(0),
             numero = casa.charAt(1);
        if (letra - 'a' >= 0 && letra - 'a' < 7) {
            if (numero - '1' >= 0 && numero - '1' < 7) {
                return true;
            }
        }
        return false;
    }

    boolean passoEhValido (String movimento) {
        String source = movimento.substring(0, 2),
               target = movimento.substring(3);
        int ini = extrairCoordenada(source),
            fim = extrairCoordenada(target),
            distancia = Math.abs(fim - ini);
        if (casaExiste(source) && casaExiste(target)) {
            if (distancia == 14 || distancia == 2) {
                if (setup[fim] == null && setup[(fim + ini)/2].tipoC == TipoCasa.PECA && setup[ini].tipoC == TipoCasa.PECA) {
                    return true;
                }
            }
        }
        return false;
    }

    void comerPeca(String iniCoord, String fimCoord) {
        int ini = extrairCoordenada(iniCoord),
            fim = extrairCoordenada(fimCoord);
        setup[ini].atribuirCoord(fimCoord.charAt(1), fimCoord.charAt(0));
        setup[fim] = setup[ini];
        setup[ini] = null;
        setup[(ini + fim)/2] = null;
    }

    String converterSetupParaString() {
        String setStr = "";
        char novoC;
        int pos = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (setup[pos] == null) {
                    novoC = '-';
                } else if (setup[pos].tipoC == TipoCasa.BRANCO) {
                    novoC = ' ';
                } else {
                    novoC = 'P';
                }
                pos += 1;
                setStr = setStr + novoC;
            }
            setStr = setStr + '\n';
        }
        return setStr;
    }

    public ArrayList<String> jogar(String[] movimentos) {
        System.out.println("Tabuleiro inicial:");
        imprimirTabuleiro();
        int qtdJogadas = movimentos.length;
        ArrayList<String> instancias = new ArrayList<String>();
        String instAtual, iniCoord, fimCoord;
        instAtual = converterSetupParaString();
        instancias.add(instAtual);
        for (int i = 0; i < qtdJogadas; i++) {
            iniCoord = movimentos[i].substring(0,2);
            fimCoord = movimentos[i].substring(3);
            System.out.println("\nSource: " + iniCoord);
            System.out.println("Target: " + fimCoord);
            if (passoEhValido(movimentos[i])) {
                comerPeca(iniCoord, fimCoord);
            }
            imprimirTabuleiro();
            instAtual = converterSetupParaString();
            instancias.add(instAtual);
        }
        return instancias;
    }
}