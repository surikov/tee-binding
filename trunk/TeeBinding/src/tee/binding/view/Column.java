package tee.binding.view;

import java.util.*;
import tee.binding.it.*;
import tee.binding.*;

public class Column<Kind> {
    protected It<Kind> current;
    protected Vector<It<Kind>> values;
    public Column() {
	current = new It<Kind>();
	values = new Vector<It<Kind>>();
    }
    public It<Kind> is() {
	return current;
    }
    public void move(int nn) {
	if (nn >= 0 && nn < values.size()) {
	    current.value(values.get(nn).value());
	}
	else {
	    current.value(null);
	}
    }
    public It<Kind> at(int nn) {
	move(nn);
	if (nn >= 0 && nn < values.size()) {
	    return values.get(nn);
	}
	else {
	    return null;
	}
    }
}
