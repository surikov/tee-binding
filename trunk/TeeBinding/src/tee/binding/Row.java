package tee.binding;
import java.util.*;
public class Row {
    private Vector<Column> columns;
    public Row() {
	columns = new Vector<Column>();
    }
    public int size() {
	return columns.size();
    }
    public void move(int nn) {
	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).move(nn);
	}
    }
    public Row field(Column column) {
	columns.add(column);
	return this;
    }
}
