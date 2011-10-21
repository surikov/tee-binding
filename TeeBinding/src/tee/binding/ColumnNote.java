package tee.binding;
import java.util.*;
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
    public Note current() {
	return current;
    }
}
