package so.coutinho.lucas.brainslab.model;

/**
 *
 * @author Lucas
 */
public class Posicao extends Par<Integer, Integer> implements Cloneable {

    public Posicao() {
        super(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public Posicao(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    @SuppressWarnings("CloneDeclaresCloneNotSupported")
    public Posicao clone() {
        try {
            return (Posicao) super.clone();
        } catch (CloneNotSupportedException ex) {
            /**
             * A exceção CloneNotSupportedException nunca será lançada devido a
             * implementação da interface Cloneable.
             */
            throw new RuntimeException("Clone fail...");
        }
    }

    @Override
    public String toString() {
        return String.format("[%3d,%3d]", super.getX(), super.getY());
    }

}
