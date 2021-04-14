public class Animacao {
    
    AquarioLombriga al;
    String comandos;
    
    int converterNum(String s) {
        int num = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
        return num;
    }
    
    Animacao (String comandos) {
        int tamAquario = converterNum(comandos.substring(0, 2)),
            tLomb = converterNum(comandos.substring(2, 4)),
            posLomb = converterNum(comandos.substring(4, 6));
        this.comandos = comandos.substring(6);
        al = new AquarioLombriga (tamAquario, tLomb, posLomb);
    }
    
    String apresenta() {
        return al.setup; // Não fica redundante?
    }
    
    void passo() {
        if (comandos != "") {
            char com = comandos.charAt(0);
            if (comandos.length() != 1) {
                comandos = comandos.substring(1);
            } else {
                comandos = "";
            }
            if (com == 'M') {
                al.mover();
            } else if (com == 'V') {
                al.virar();
            } else {
                al.crescer();
            }
        }
    }
    
    void iniciarAnimacao() {
        System.out.println(apresenta());
        while (comandos != "") {
            passo();
            System.out.println(apresenta());
        }
    }
}