package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

/**
 * 
 * @author User
 */
public class Toggles extends These<Boolean> {

    private Toggle currentToggle;

    /**
     * 
     */
    public Toggles() {
	super();
	currentToggle = new Toggle().bind(super.value());
    }

    @Override
    public Toggle value() {
	return currentToggle;
    }

    /**
     * 
     * @param it
     * @return
     */
    public Toggles value(Toggle it) {
	super.value(it);
	return this;
    }

    public Toggles select(int nn) {
	super.select(nn);
	return this;
    }


}
