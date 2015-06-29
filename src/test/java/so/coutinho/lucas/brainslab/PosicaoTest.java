/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.brainslab;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class PosicaoTest {

    @Test
    public void testClone() {
        Posicao posicao = new Posicao();
        Posicao posicaoClone = posicao.clone();

        assertNotSame(posicao, posicaoClone);
        assertEquals(posicao, posicaoClone);
    }

}
