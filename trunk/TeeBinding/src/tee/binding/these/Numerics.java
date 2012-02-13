package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

/**
 *
 * @author User
 */
public class Numerics extends These<Double> {

    private Numeric valueNumeric;
    private Numeric isNumeric;

    /**
     *
     */
    public Numerics() {
        super();
        valueNumeric = new Numeric().bind(super.current());
        isNumeric = new Numeric().bind(super.is());
    }

    @Override
    public Numeric is() {
        return isNumeric;
    }

    @Override
    public Numeric current() {
        return valueNumeric;
    }

    /**
     *
     * @param it
     * @return
     */
    public Numerics current(Numeric it) {
        super.current(it);
        return this;
    }

    /**
     *
     * @param it
     * @return
     */
    public Numerics current(int it) {
        super.current((double) it);
        return this;
    }

    @Override
    public Numerics select(int nn) {
        super.select(nn);
        return this;
    }
}
