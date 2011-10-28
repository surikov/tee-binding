package tee.binding.view;
import tee.binding.view.Column;
import tee.binding.it.Numeric;
import tee.binding.it.Note;
import java.util.*;
import tee.binding.task.Task;
public class ColumnNote extends Column {
    protected Note current;
    private Vector<Note> values;
    public ColumnNote() {
	current = new Note();
	values = new Vector<Note>();
    }
    @Override public void move(int nn) {
	if (nn >= 0 && nn < values.size()) {
	    current.value(values.get(nn).value());
	} else {
	    current.value("");
	}
    }
    public ColumnNote is(String it) {
	Note v = new Note().value(it);
	values.add(v);
	current.value(it);
	return this;
    }
    public ColumnNote is(Note it) {
	Note v = new Note().bind(it);
	values.add(v);
	current.value(it.value());
	return this;
    }
    public Note is() {
	return current;
    }
    public Note at(Numeric nn) {
	final Note columnValue = new Note();
	final Numeric index = new Numeric().bind(nn);
	index.afterChange(new Task() {
	    @Override public void doTask() {
		if (index != null) {
		    if (index.value() != null) {
			int nn = index.value().intValue();
			if (nn >= 0 && nn < values.size()) {
			    columnValue.value(values.get(nn).value());
			} else {
			    columnValue.value("");
			}
		    }
		}
	    }
	});
	return columnValue;
    }
    public Comparator<Row> ascending() {
	return new Comparator<Row>() {

	    @Override public int compare(Row o1, Row o2) {
		String s1=values.get(o1.nn).value();
		String s2=values.get(o2.nn).value();
		double n = s1.compareTo(s2);
		return (int) (+n);
	    }
	};
    }

    public Comparator<Row> descending() {
	return new Comparator<Row>() {

	    @Override public int compare(Row o1, Row o2) {
		String s1=values.get(o1.nn).value();
		String s2=values.get(o2.nn).value();
		double n = s1.compareTo(s2);
		return (int) (-n);
	    }
	};
    }
}
