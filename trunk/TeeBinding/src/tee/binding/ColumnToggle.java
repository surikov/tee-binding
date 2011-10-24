package tee.binding;

import java.util.*;

public class ColumnToggle extends Column {

    protected Toggle current;
    private Vector<Toggle> values;

    public ColumnToggle() {
	current = new Toggle();
	values = new Vector<Toggle>();
    }

    @Override public void move(int nn) {
	if (nn >= 0 && nn < values.size()) {
	    current.value(values.get(nn).value());
	} else {
	    current.value(false);
	}
    }

    public ColumnToggle is(boolean it) {
	Toggle v = new Toggle().value(it);
	values.add(v);
	current.value(it);
	return this;
    }

    public ColumnToggle is(Toggle it) {
	Toggle v = new Toggle().bind(it);
	values.add(v);
	current.value(it.value());
	return this;
    }

    public Toggle is() {
	return current;
    }

    public Toggle at(Numeric nn) {
	final Toggle columnValue = new Toggle();
	final Numeric index = new Numeric().bind(nn);
	index.afterChange(new Task() {

	    @Override public void doTask() {
		int nn = index.value().intValue();
		if (nn >= 0 && nn < values.size()) {
		    columnValue.value(values.get(nn).value());
		} else {
		    columnValue.value(false);
		}
	    }
	});
	return columnValue;
    }
}
