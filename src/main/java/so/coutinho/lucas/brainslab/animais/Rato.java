/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.brainslab.animais;

import so.coutinho.lucas.brainslab.Labirinto;
import so.coutinho.lucas.brainslab.Posicao;

/**
 *
 * @author Lucas
 */
public class Rato implements Animal {

    @Override
    public Posicao mover(Labirinto labirinto) {
        //TODO: Criar movimento do rato
        return labirinto.getRatoPosicao().clone();
    }

}
