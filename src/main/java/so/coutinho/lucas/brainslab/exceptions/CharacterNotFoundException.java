/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
