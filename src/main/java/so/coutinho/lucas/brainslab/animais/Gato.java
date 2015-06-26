package so.coutinho.lucas.brainslab.animais;

import java.util.Random;
import so.coutinho.lucas.brainslab.Labirinto;
import so.coutinho.lucas.brainslab.Posicao;

/**
 *
 * @author lucas.oliveira
 */
class Gato implements Animal {

    private final Random random;

    public Gato() {
        random = new Random();
    }

    @Override
    public Posicao mover(Labirinto labirinto) {
        int linha, coluna;
        Posicao novaPosicao;

        linha = random.nextInt(labirinto.getMaximoLinhas());
        coluna = random.nextInt(labirinto.getMaximoColunas());

        novaPosicao = new Posicao(linha, coluna);

        //TODO: Verificar se o GATO só pode aparecer em passagens (ENTRADA e SAÍDA?)
        if (labirinto.verificaCaminho(novaPosicao).equals(Labirinto.PASSAGEM)) {
            return novaPosicao;
        }

        return mover(labirinto);
    }

}
