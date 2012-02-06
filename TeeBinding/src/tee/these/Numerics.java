package tee.these;

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

    public Numerics select(int nn) {
	super.select(nn);
	return this;
    }

    public static void main(String[] a) {
	Numeric a1 = new Numeric().value(1.1);
	Numeric a2 = new Numeric().value(2.2);
	Numeric a3 = new Numeric().value(3.3);
	Numerics s = new Numerics().is(a1).is(a2).is(a3).select(0);
	System.out.println("--");
	System.out.println(s.is().value());
	s.drop();
	System.out.println(s.is().value());
	s.drop();
	System.out.println(s.is().value());
	s.drop();
	System.out.println(s.is().value());
    }
}
