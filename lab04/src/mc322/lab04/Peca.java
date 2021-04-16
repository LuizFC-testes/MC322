public class Peca {
    TipoCasa tipoC;
    char linha;
    char coluna;

    Peca (TipoCasa tipoC) {
        this.tipoC = tipoC;
    }

    void atribuirCoord (char linha, char coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
}