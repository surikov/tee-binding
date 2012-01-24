package tee.binding.view;

import tee.binding.view.Column;
import tee.binding.it.Numeric;
import java.util.*;
import tee.binding.it.*;
import tee.binding.task.Task;

public class ColumnNumeric extends Column {

    private Numeric current;
    private Vector<Numeric> values;

    public ColumnNumeric() {
	current = new Numeric();
	values = new Vector<Numeric>();
    }

    @Override public void move(int nn) {
	if (nn >= 0 && nn < values.size()) {
	    current.value(values.get(nn).value());
	} else {
	    current.value(0);
	}
    }

    public ColumnNumeric is(double it) {
	Numeric v = new Numeric().value(it);
	values.add(v);
	current.value(it);
	return this;
    }

    public ColumnNumeric is(int it) {
	Numeric v = new Numeric().value(it);
	values.add(v);
	current.value(it);
	return this;
    }

    public ColumnNumeric is(Numeric it) {
	Numeric v = new Numeric().bind(it);
	values.add(v);
	current.value(it.value());
	return this;
    }

    public Numeric is() {
	return current;
    }
 public Numeric at(int nn) {
	move(nn);
	if (nn >= 0 && nn < values.size()) {
	    return values.get(nn);
	}
	else {
	    return null;
	}
    }
  public Numeric at(double nn) {
	move((int)nn);
	if (nn >= 0 && nn < values.size()) {
	    return values.get((int)nn);
	}
	else {
	    return null;
	}
    }
    public Numeric at(Numeric nn) {
	final Numeric columnValue = new Numeric();
	final Numeric index = new Numeric().bind(nn);
	index.afterChange(new Task() {

	    @Override public void doTask() {
		int nn = index.value().intValue();
		if (nn >= 0 && nn < values.size()) {
		    columnValue.value(values.get(nn).value());
		} else {
		    columnValue.value(-1);
		}
	    }
	});
	return columnValue;
    }

    public Comparator<Row> ascending() {
	return new Comparator<Row>() {

	    @Override public int compare(Row o1, Row o2) {
		double n = values.get(o1.nn).value() - values.get(o2.nn).value();
		return (int) (+n);
	    }
	};
    }

    public Comparator<Row> descending() {
	return new Comparator<Row>() {

	    @Override public int compare(Row o1, Row o2) {
		double n = values.get(o1.nn).value() - values.get(o2.nn).value();
		return (int) (-n);
	    }
	};
    }

}
