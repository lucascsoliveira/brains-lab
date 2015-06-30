package so.coutinho.lucas.brainslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class LabirintoTest {

    private final static File FOLDER_TEST = new File(".//src//test//resources//files//");
    private final List<Labirinto> labirintos = new ArrayList<>();

    @Before
    public void setUp() {
        // Limpando a lista de labirintos
        labirintos.clear();

        // Criando os labirintos para o teste
        for (File file : FOLDER_TEST.listFiles()) {
            try {
                labirintos.add(new Labirinto(file));
            } catch (FileNotFoundException ex) {
                System.out.printf("O arquivo %s não foi aberto.", file.getName());
            }
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of localizarEntrada method, of class Labirinto.
     */
    @Test
    public void testLocalizarEntrada() {
        List<Posicao> entradas = new ArrayList<>();

        //Entrada - test001.txt
        entradas.add(new Posicao(0, 0));
        //Entrada - test002.txt
        entradas.add(new Posicao(1, 1));

        int index = 0;
        for (Labirinto labirinto : labirintos) {
            assertEquals(entradas.get(index++), labirinto.getEntrada());
        }
    }

    /**
     * Test of localizarSaida method, of class Labirinto.
     */
    @Test
    public void testLocalizarSaida() {
        List<Posicao> saidas = new ArrayList<>();

        //Saida - test001.txt
        saidas.add(new Posicao(4, 4));
        //Saida - test002.txt
        saidas.add(new Posicao(3, 3));

        int index = 0;
        for (Labirinto labirinto : labirintos) {
            assertEquals(saidas.get(index++), labirinto.getSaida());
        }
    }

    /**
     * Test of buscarRotas method, of class Labirinto.
     */
    @Test
    public void testBuscarRotas() {
        List<List<List<Posicao>>> rotasPorArquivo = new ArrayList<>();

        //Saida - test001.txt
        List<List<Posicao>> rotasTest001 = new ArrayList<>();
        rotasTest001.add(new ArrayList<>(Arrays.asList(new Posicao(0, 0), new Posicao(1, 0), new Posicao(2, 0), new Posicao(3, 0), new Posicao(4, 0), new Posicao(4, 1), new Posicao(4, 2), new Posicao(4, 3), new Posicao(4, 4))));
        rotasPorArquivo.add(rotasTest001);
        //Saida - test002.txt
        List<List<Posicao>> rotasTest002 = new ArrayList<>();
        rotasTest002.add(new ArrayList<>(Arrays.asList(new Posicao(1, 1), new Posicao(1, 2), new Posicao(1, 3), new Posicao(2, 3), new Posicao(3, 3))));
        rotasTest002.add(new ArrayList<>(Arrays.asList(new Posicao(1, 1), new Posicao(2, 1), new Posicao(3, 1), new Posicao(3, 2), new Posicao(3, 3))));
        rotasPorArquivo.add(rotasTest002);

        int indexArquivo = 0;
        for (Labirinto labirinto : labirintos) {
            int indexRota = 0;
            for (List<Posicao> labRotas : labirinto.buscarRotas(labirinto.getSaida())) {
                assertArrayEquals(labRotas.toArray(), rotasPorArquivo.get(indexArquivo).get(indexRota++).toArray());
            }

            indexArquivo++;
        }
    }

    /**
     * Test of buscarMenorRota method, of class Labirinto.
     */
    @Test
    public void testBuscarMenorRota_SemGato() {
        List<List<Posicao>> menoresRotas = new ArrayList<>();

        //Menor rota - test001.txt
        menoresRotas.add(new ArrayList<>(Arrays.asList(new Posicao(0, 0), new Posicao(1, 0), new Posicao(2, 0), new Posicao(3, 0), new Posicao(4, 0), new Posicao(4, 1), new Posicao(4, 2), new Posicao(4, 3), new Posicao(4, 4))));
        //Menor rota - test002.txt
        menoresRotas.add(new ArrayList<>(Arrays.asList(new Posicao(1, 1), new Posicao(2, 1), new Posicao(3, 1), new Posicao(3, 2), new Posicao(3, 3))));

        int indexArquivo = 0;
        for (List<Posicao> menorRota : menoresRotas) {
            Labirinto labirinto = labirintos.get(indexArquivo++);
            assertArrayEquals(menorRota.toArray(), labirinto.buscarMenorRota(labirinto.getSaida()).toArray());
        }
    }

    /**
     * Test of buscarMenorRota method, of class Labirinto.
     */
    @Test
    public void testBuscarMenorRota_ComGato() {
        List<List<Posicao>> menoresRotas = new ArrayList<>();

        //Menor rota - test001.txt
        menoresRotas.add(new ArrayList<>(Arrays.asList(new Posicao(0, 0), new Posicao(1, 0), new Posicao(2, 0), new Posicao(3, 0), new Posicao(4, 0), new Posicao(4, 1), new Posicao(4, 2), new Posicao(4, 3), new Posicao(4, 4))));
        labirintos.get(0).setGatoPosicao(new Posicao(4, 0));

        //Menor rota - test002.txt
        menoresRotas.add(new ArrayList<>(Arrays.asList(new Posicao(1, 1), new Posicao(1, 2), new Posicao(1, 3), new Posicao(2, 3), new Posicao(3, 3))));
        labirintos.get(1).setGatoPosicao(new Posicao(2, 1));

        int indexArquivo = 0;
        for (List<Posicao> menorRota : menoresRotas) {
            Labirinto labirinto = labirintos.get(indexArquivo++);
            assertArrayEquals(menorRota.toArray(), labirinto.buscarMenorRota(labirinto.getSaida()).toArray());
        }
    }

}
