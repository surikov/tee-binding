package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

public class Numerics extends These<Double> {

    private Numeric currentNum;

    public Numerics() {
	super();
	currentNum = new Numeric().bind(super.value());
    }

    @Override
    public Numeric value() {
	return currentNum;
    }

    public Numerics value(Numeric it) {
	super.value(it);
	return this;
    }

    public Numerics value(int it) {
	super.value((double) it);
	return this;
    }

    public Numerics select(int nn) {
	super.select(nn);
	return this;
    }


}
