package tee.binding.view;

import java.util.*;
import tee.binding.it.*;
import tee.binding.*;
import tee.binding.task.*;

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
    public Column<Kind> is(Kind it) {
	It<Kind> v = new It().value(it);
	values.add(v);
	current.value(it);
	return this;
    }
    public Column<Kind>  is(It<Kind> it) {
	It<Kind> v = new It<Kind>().bind(it);
	values.add(v);
	current.value(it.value());
	return this;
    }
    /*public void remove(int nn){
	values.remove(nn);
    }*/
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
     public It<Kind> at(double nn) {
	move((int)nn);
	if (nn >= 0 && nn < values.size()) {
	    return values.get((int)nn);
	}
	else {
	    return null;
	}
    }
     public It<Kind> at(Numeric nn) {
	final It<Kind> columnValue = new It<Kind>();
	final Numeric index = new Numeric().bind(nn);
	index.afterChange(new Task() {
	    @Override public void doTask() {
		if (index != null) {
		    if (index.value() != null) {
			int nn = index.value().intValue();
			if (nn >= 0 && nn < values.size()) {
			    columnValue.value(values.get(nn).value());
			}
			else {
			    columnValue.value(null);
			}
		    }
		}
	    }
	});
	return columnValue;
    }
}
