package tee.binding.these;

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

   
}
