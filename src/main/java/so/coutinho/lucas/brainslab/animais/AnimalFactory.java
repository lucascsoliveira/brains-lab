package so.coutinho.lucas.brainslab.animais;

import so.coutinho.lucas.brainslab.exceptions.AnimalDoesNotExist;

/**
 *
 * @author lucas.oliveira
 */
public abstract class AnimalFactory {

    public final static String GATO = "GATO";
    public final static String RATO = "RATO";

    public static Animal getAnimal(String animal) {
        switch (animal.toUpperCase().trim()) {
            case GATO:
                return new Gato();
            case RATO:
                return new Rato();
            default:
                throw new AnimalDoesNotExist(animal);
        }
    }

}
