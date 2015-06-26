package so.coutinho.lucas.brainslab;

import lombok.Data;

/**
 *
 * @author Lucas
 * @param <X>
 * @param <Y>
 */
@Data
public class Par<X, Y> {

    private final X x;
    private final Y y;

}
