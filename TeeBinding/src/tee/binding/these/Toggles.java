package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

/**
 *
 * @author User
 */
public class Toggles extends These<Boolean> {
    private Toggle valueToggle;
    private Toggle isToggle;
    /**
     *
     */
    public Toggles() {
	super();
	valueToggle = new Toggle().bind(super.current());
	isToggle = new Toggle().bind(super.is());
    }
    @Override
    public Toggle is() {
	return isToggle;
    }
    @Override
    public Toggle current() {
	return valueToggle;
    }
    /**
     *
     * @param it
     * @return
     */
    public Toggles is(Toggle it) {
	super.is(it);
	return this;
    }
    @Override
    public Toggles select(int nn) {
	super.select(nn);
	return this;
    }
    public Comparator<Series> ascending() {
	return new Comparator<Series>() {
	    @Override public int compare(Series o1, Series o2) {
		o1.probe();
		boolean s1 = is().value();
		o2.probe();
		boolean s2 = is().value();
		int n = 0;
		if (s1 && (!s2)) {
		    n = 1;
		}
		if ((!s1) && s2) {
		    n = -1;
		}
		return +n;
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
		boolean s1 = is().value();
		o2.probe();
		boolean s2 = is().value();
		int n = 0;
		if (s1 && (!s2)) {
		    n = 1;
		}
		if ((!s1) && s2) {
		    n = -1;
		}
		return -n;
	    }
	};
    }
}
