package so.coutinho.lucas.brainslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import so.coutinho.lucas.brainslab.animais.Animal;
import so.coutinho.lucas.brainslab.animais.AnimalFactory;
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
    private Posicao gatoPosicao;
    private Posicao ratoPosicao;

    public Labirinto(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int maxLinha = scanner.nextInt();
        int maxColuna = scanner.nextInt();

        matrizCaminho = new Character[maxLinha][maxColuna];
        matrizPeso = new Integer[maxLinha][maxColuna];

        for (int linha = 0; linha < maxLinha; linha++) {
            for (int coluna = 0; coluna < maxColuna; coluna++) {
                matrizCaminho[linha][coluna] = scanner.next().charAt(0);
            }
        }

        for (int linha = 0; linha < maxLinha; linha++) {
            for (int coluna = 0; coluna < maxColuna; coluna++) {
                matrizPeso[linha][coluna] = scanner.nextInt();
            }
        }

        entrada = localizarEntrada();
        saida = localizarSaida();

        gato = AnimalFactory.getAnimal(AnimalFactory.GATO);
        rato = AnimalFactory.getAnimal(AnimalFactory.RATO);

        gatoPosicao = new Posicao();
        ratoPosicao = entrada.clone();
    }

    public Labirinto(Character[][] labirinto, Integer[][] custo) {
        matrizCaminho = labirinto;
        matrizPeso = custo;
        entrada = localizarEntrada();
        saida = localizarSaida();

        gato = AnimalFactory.getAnimal(AnimalFactory.GATO);
        rato = AnimalFactory.getAnimal(AnimalFactory.RATO);

        gatoPosicao = new Posicao();
        ratoPosicao = entrada.clone();
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

    public final Posicao localizarEntrada() {
        return localizar(ENTRADA);
    }

    public final Posicao localizarSaida() {
        return localizar(SAIDA);
    }

    public boolean acabou() {
        return gatoPosicao.equals(ratoPosicao) || ratoPosicao.equals(saida);
    }

    public void moverGato() {
        gatoPosicao = gato.mover(this);
    }

    public void moverRato() {
        ratoPosicao = rato.mover(this);
    }

    public Integer getMaximoColunas() {
        return matrizCaminho[0].length;
    }

    public Integer getMaximoLinhas() {
        return matrizCaminho.length;
    }

    public Character getCaminho(Posicao posicao) {
        return matrizCaminho[posicao.getX()][posicao.getY()];
    }

    public Double getPeso(Posicao posicao) {
        if (posicao.equals(gatoPosicao)) {
            return Double.POSITIVE_INFINITY;
        }

        return matrizPeso[posicao.getX()][posicao.getY()].doubleValue();
    }

    public List<List<Posicao>> buscarRotas(Posicao posicao) {
        List<List<Posicao>> rotas = new ArrayList<>();
        List<Posicao> rota = new ArrayList<>();

        fazVarredura(rotas, rota, posicao);

        return rotas;
    }

    public List<Posicao> buscarMenorRota(Posicao posicao) {
        return buscarMenorRota(buscarRotas(posicao));
    }

    private List<Posicao> buscarMenorRota(List<List<Posicao>> rotas) {
        Double menorPeso = Double.POSITIVE_INFINITY;
        List<Posicao> menorRota = rotas.get(0);

        for (List<Posicao> rota : rotas) {
            Double peso = 0.0;
            for (Posicao posicao : rota) {
                peso += getPeso(posicao);
            }

            if (peso < menorPeso) {
                menorPeso = peso;
                menorRota = rota;
            }
        }

        return menorRota;
    }

    private void fazVarredura(List<List<Posicao>> rotas, List<Posicao> rota, Posicao posicao) {
        int linha = posicao.getX(), coluna = posicao.getY();
        List<Posicao> novaRota = new ArrayList<>(rota);

        // Verifica se a posição passada está dentro dos limites da matriz
        if ((linha >= 0 && linha < getMaximoLinhas()) && (coluna >= 0 && coluna < getMaximoColunas())
                // e se a posição não é uma parede
                && (!matrizCaminho[linha][coluna].equals(PAREDE))) {

            novaRota.add(posicao);

            if (posicao.equals(ratoPosicao)) {
                Collections.reverse(novaRota);
                rotas.add(novaRota);
            } else {
                // CIMA
                fazVarredura(rotas, novaRota, new Posicao(linha - 1, coluna));
                // DIREITA
                //fazVarredura(rotas, novaRota, new Posicao(linha, coluna + 1));
                // BAIXO
                //fazVarredura(rotas, novaRota, new Posicao(linha + 1, coluna));
                // ESQUERDA
                fazVarredura(rotas, novaRota, new Posicao(linha, coluna - 1));
            }
        }
    }

    @Override
    public String toString() {
        String string = "[Labirinto]\n";
        int lin, col;

        for (lin = 0; lin < getMaximoLinhas(); lin++) {
            for (col = 0; col < getMaximoColunas(); col++) {
                Posicao posicao = new Posicao(lin, col);

                if ((posicao.equals(entrada) || posicao.equals(saida)) && posicao.equals(gatoPosicao)) {
                    string += getCaminho(posicao) + "G";
                } else if ((posicao.equals(entrada) || posicao.equals(saida)) && posicao.equals(ratoPosicao)) {
                    string += getCaminho(posicao) + "R";
                } else if (posicao.equals(gatoPosicao)) {
                    string += "G";
                } else if (posicao.equals(ratoPosicao)) {
                    string += "R";
                } else {
                    string += getCaminho(posicao);
                }
                string += "\t";
            }
            string += "\n";
        }

        return string;
    }

}
