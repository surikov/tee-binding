package tee.binding;
import java.util.*;
public class Row {
    //private Vector<Column> columns;
    private Vector<Column> columns;
    public Row() {
	columns = new Vector<Column>();
	//columns = new Vector<Column>();
    }
    public int size() {
	return columns.size();
    }
    public void move(int nn) {
	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).move(nn);
	}
    }
    /*
     * public Column field(Column column) { for (int i = 0; i < columns.size();
     * i++) { if (columns.get(i).equals(column)) { return } } return
     * columns.get(n);
    }
     */
    /*
     * public Column column(int n) { return columns.get(n); }
     */
    public Row column(Column column) {
	//items.add(new Item(this,column,value));
	//columns.add(column);
	/*
	 * boolean found = false; for (int i = 0; i < columns.size(); i++) { if
	 * (columns.get(i).equals(column)) { found = true; break; } }
	if(found){}
	 */
	columns.add(column);
	return this;
    }
}
