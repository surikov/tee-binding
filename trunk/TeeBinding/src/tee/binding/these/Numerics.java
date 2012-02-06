package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

public class Numerics extends These<Double> {

    private Numeric currentNum;

    public Numerics() {
	super();
	currentNum = new Numeric().bind(super.is());
    }

    @Override
    public Numeric is() {
	return currentNum;
    }

    public Numerics is(Numeric it) {
	super.is(it);
	return this;
    }

    public Numerics is(int it) {
	super.is((double) it);
	return this;
    }

    public Numerics select(int nn) {
	super.select(nn);
	return this;
    }


}
