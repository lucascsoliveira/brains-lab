package so.coutinho.lucas.brainslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class App {

    private final static File FILE = new File(".//src//main//resources//files//labirinto001.txt");

    public static void main(String[] args) throws FileNotFoundException {
        Labirinto labirinto = new Labirinto(FILE);

        System.out.println("[Labirinto - PESO]");
        for (Integer[] linha : labirinto.getMatrizPeso()) {
            for (Integer peso : linha) {
                System.out.printf("%d\t", peso);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println(labirinto);
        while (!labirinto.acabou()) {
            labirinto.moverRato();
            labirinto.moverGato();
            System.out.println(labirinto);
        }
    }

}
