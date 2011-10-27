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

    public Comparator<Row> ascending() {
	return new Comparator<Row>() {

	    @Override public int compare(Row o1, Row o2) {
		boolean s1 = values.get(o1.nn).value();
		boolean s2 = values.get(o2.nn).value();
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

    public Comparator<Row> descending() {
	return new Comparator<Row>() {

	    @Override public int compare(Row o1, Row o2) {
		boolean s1 = values.get(o1.nn).value();
		boolean s2 = values.get(o2.nn).value();
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
