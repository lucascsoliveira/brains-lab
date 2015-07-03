package so.coutinho.lucas.brainslab.model.animais;

import so.coutinho.lucas.brainslab.model.Labirinto;
import so.coutinho.lucas.brainslab.model.Posicao;

/**
 *
 * @author Lucas
 */
public interface Animal {

    public Posicao mover(Labirinto labirinto);

}
