package so.coutinho.lucas.brainslab.model.exceptions;

/**
 *
 * @author Lucas
 */
public class CharacterNotFoundException extends RuntimeException {

    public CharacterNotFoundException() {
        super();
    }

    public CharacterNotFoundException(Character character) {
        super("Character '" + character + "' not found!");
    }

}
