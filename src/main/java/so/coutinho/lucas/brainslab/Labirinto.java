package so.coutinho.lucas.brainslab;

import lombok.Getter;
import lombok.Setter;
import so.coutinho.lucas.brainslab.animais.Animal;
import so.coutinho.lucas.brainslab.exceptions.CharacterNotFoundException;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
public class Labirinto {

    public final static Character ENTRADA = 'S';
    public final static Character SAIDA = 'F';
    public final static Character PAREDE = '0';
    public final static Character PASSAGEM = '1';

    private final Integer[][] matrizPeso;
    private final Character[][] matrizCaminho;
    private final Posicao entrada;
    private final Posicao saida;
    private final Animal gato;
    private final Animal rato;
    private Posicao posicaoGato;
    private Posicao posicaoRato;

    public Labirinto() {
        //TODO: Inicializar matrizCaminho e matrizPeso
        matrizCaminho = null;
        matrizPeso = null;
        entrada = localizarEntrada();
        saida = localizarSaida();

        //TODO: Criar as classes Gato e Rato
        gato = null;
        rato = null;

        //TODO: posicaoRato = entrada.clone()
        //TODO:posicaoGato = (?)
        posicaoRato = null;
        posicaoGato = null;
    }

    private Posicao localizar(Character character) {
        int linha, coluna;
        int maxLinha = matrizCaminho.length;
        int maxColuna = matrizCaminho[0].length;

        for (linha = 0; linha < maxLinha; linha++) {
            for (coluna = 0; coluna < maxColuna; coluna++) {
                if (matrizCaminho[linha][coluna].equals(character)) {
                    return new Posicao(linha, coluna);
                }
            }
        }

        throw new CharacterNotFoundException(character);
    }

    public Posicao localizarEntrada() {
        return localizar(ENTRADA);
    }

    public Posicao localizarSaida() {
        return localizar(SAIDA);
    }

    public boolean acabou() {
        return posicaoGato.equals(posicaoRato) || posicaoRato.equals(saida);
    }

    public Integer getMaximoColunas() {
        return matrizCaminho[0].length;
    }

    public Integer getMaximoLinhas() {
        return matrizCaminho.length;
    }

    public Character verificaCaminho(Posicao posicao) {
        return matrizCaminho[posicao.getX()][posicao.getY()];
    }

    public Double verificaPeso(Posicao posicao) {
        if (posicao.equals(posicaoGato)) {
            return Double.POSITIVE_INFINITY;
        }

        return matrizPeso[posicao.getX()][posicao.getY()].doubleValue();
    }

}
