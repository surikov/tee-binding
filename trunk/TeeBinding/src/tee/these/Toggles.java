package tee.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

public class Toggles extends These<Boolean> {

    private Toggle currentToggle;

    public Toggles() {
	super();
	currentToggle = new Toggle().bind(super.is());
    }

    @Override
    public Toggle is() {
	return currentToggle;
    }

    public Toggles is(Toggle it) {
	super.is(it);
	return this;
    }

    public Toggles select(int nn) {
	super.select(nn);
	return this;
    }

    public static void main(String[] a) {
	Toggle a1 = new Toggle().value(true);
	Toggle a2 = new Toggle().value(false);
	Toggle a3 = new Toggle().value(true);
	Toggles s = new Toggles().is(a1).is(a2).is(a3).select(0);
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
