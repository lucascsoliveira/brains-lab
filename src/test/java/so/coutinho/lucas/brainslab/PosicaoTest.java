package so.coutinho.lucas.brainslab;

import so.coutinho.lucas.brainslab.model.Posicao;
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
