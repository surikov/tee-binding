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
        valueNumeric = new Numeric().bind(super.value());
        isNumeric = new Numeric().bind(super.is());
    }

    @Override
    public Numeric is() {
        return isNumeric;
    }

    @Override
    public Numeric value() {
        return valueNumeric;
    }

    /**
     *
     * @param it
     * @return
     */
    public Numerics value(Numeric it) {
        super.value(it);
        return this;
    }

    /**
     *
     * @param it
     * @return
     */
    public Numerics value(int it) {
        super.value((double) it);
        return this;
    }

    @Override
    public Numerics select(int nn) {
        super.select(nn);
        return this;
    }
}
