package so.coutinho.lucas.brainslab.model.animais;

import so.coutinho.lucas.brainslab.model.Labirinto;
import so.coutinho.lucas.brainslab.model.Posicao;

/**
 *
 * @author Lucas
 */
public class Rato implements Animal {

    private static final Integer PROXIMA_POSICAO = 1;

    @Override
    public Posicao mover(Labirinto labirinto) {
        return labirinto.buscarMenorRota(labirinto.getRatoPosicao()).get(PROXIMA_POSICAO);
    }

}
