package tee.binding.view;
import tee.binding.view.Column;
import java.util.*;
public class Row {
    private Vector<Column> columns;
    protected int nn;
    public Row() {
	columns = new Vector<Column>();
	nn=0;
    }
    public int size() {
	return columns.size();
    }
    public void move(){//int nn) {
	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).move(nn);
	}
    }
    public Row field(Column column) {
	columns.add(column);
	return this;
    }
}
