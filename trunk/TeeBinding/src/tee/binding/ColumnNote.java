package tee.binding;
import java.util.*;
public class ColumnNote extends Column {
    protected Note value;
    private Vector<Note> values;
    public ColumnNote() {
	value = new Note();
	values = new Vector<Note>();
    }
    @Override public void move(int nn) {
	if (nn >= 0 && nn < values.size()) {
	    value.value(values.get(nn).value());
	} else {
	    value.value("");
	}
    }
    public ColumnNote value(String it) {
	Note v = new Note().value(it);
	values.add(v);
	return this;
    }

    public ColumnNote value(Note it) {
	Note v = new Note().bind(it);
	values.add(v);
	return this;
    }
    public Note value() {
	return value;
    }
}
