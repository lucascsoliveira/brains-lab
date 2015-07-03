package so.coutinho.lucas.brainslab.model.exceptions;

/**
 *
 * @author lucas.oliveira
 */
public class AnimalDoesNotExist extends RuntimeException {

    public AnimalDoesNotExist() {
    }

    public AnimalDoesNotExist(String animal) {
        super("Animal \"" + animal + "\" doesn't exist!");
    }

}
