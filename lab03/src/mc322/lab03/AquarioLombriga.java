public class AquarioLombriga {
    int tamAquario;
    int posLomb;
    int tLomb;
    String setup;
    String orientacao;

    AquarioLombriga (int tamAquario, int tLomb, int posLomb) {
        this.tamAquario = tamAquario;
        this.posLomb = (posLomb - 1) + (tLomb - 1);
        this.tLomb = tLomb;
        // Formar a lombriga
        setup = "0";
        for (int i = 0; i < tLomb - 1; i++) {
            setup = "@" + setup;
        }
        // Posicioná-la no aquário
        if (tamAquario > tLomb) {
            if (this.posLomb < tamAquario) {
                for (int i = 0; i < posLomb - 1; i++) {
                    setup = "#" + setup;
                }
                for (int i = this.posLomb + 1; i < tamAquario; i++) {
                    setup = setup + "#";
                }
            } else {
                this.posLomb = tLomb - 1;
                for (int i = this.tLomb; i < tamAquario; i++) {
                    setup = setup + "#";
                }
            }
        } else {
            this.posLomb = tLomb - 1;
        }
        orientacao = "dir";
    }

    void crescer() {
        int gluteo = posLomb;
        if (orientacao == "dir") {
            gluteo -= (tLomb - 1);
            if (gluteo != 0) {
                setup = setup.substring(0, gluteo - 1) + "@" + setup.substring(gluteo);
                tLomb += 1;
            }
        } else {
            gluteo += (tLomb - 1);
            if (gluteo != tamAquario - 1) {
                setup = setup.substring(0, gluteo + 1) + "@" + setup.substring(gluteo + 2);
                tLomb += 1;
                //funciona para caso o glúteo encoste na borda do aquário após o crescimento
            }
        }
    }

    void virar() {
        int gluteo = posLomb;
        if (orientacao == "dir") {
            gluteo -= (tLomb - 1);
            setup = setup.substring(0, gluteo) + "0" + setup.substring(gluteo + 1, posLomb) + "@" + setup.substring(posLomb + 1);
            orientacao = "esq";
        } else {
            gluteo += (tLomb - 1);
            setup = setup.substring(0, posLomb) + "@" + setup.substring(posLomb + 1, gluteo) + "0" + setup.substring(gluteo + 1);
            orientacao = "dir";
        }
        posLomb = gluteo;
    }

    void mover() {
        if (orientacao == "dir") {
            if (posLomb != tamAquario - 1) {
                setup = "#" + setup.substring(0, tamAquario - 1);
                posLomb += 1;
            } else {
                virar();
            }
        } else {
            if (posLomb != 0) {
                setup = setup.substring(1) + "#";
                posLomb -= 1;
            } else {
                virar();
            }
        }
    }

    String apresenta() {
        return setup; // É para imprimir ou para só retornar mesmo?
    }

}