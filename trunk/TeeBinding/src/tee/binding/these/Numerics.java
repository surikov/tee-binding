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
    public Numerics is(Numeric it) {
        super.is(it);
        return this;
    }

    /**
     *
     * @param it
     * @return
     */
    public Numerics is(int it) {
        super.is((double) it);
        return this;
    }

    @Override
    public Numerics select(int nn) {
        super.select(nn);
        return this;
    }
    public Comparator<Series> ascending() {
	return new Comparator<Series>() {

	    @Override public int compare(Series o1, Series o2) {
		o1.probe();
		double s1 = is().value();
		o2.probe();
		double s2 = is().value();
		double n = s1 - s2;
		return (int) (+n);
	    }
	};
    }

    /**
     * 
     * @return
     */
    public Comparator<Series> descending() {
	return new Comparator<Series>() {

	    @Override public int compare(Series o1, Series o2) {
		o1.probe();
		double s1 = is().value();
		o2.probe();
		double s2 = is().value();
		double n = s1 - s2;
		return (int) (-n);
	    }
	};
    }
}
