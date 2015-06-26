package so.coutinho.lucas.brainslab.exceptions;

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
