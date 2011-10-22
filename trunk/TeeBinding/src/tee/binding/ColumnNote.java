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
}
