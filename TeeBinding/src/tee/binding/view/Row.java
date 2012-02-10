package tee.binding.view;
import tee.binding.view.Column;
import java.util.*;
/**
 * 
 * @author User
 */
public class Row {
    private Vector<Column> columns;
    /**
     * 
     */
    protected int nn;
    /**
     * 
     */
    public Row() {
	columns = new Vector<Column>();
	nn=0;
    }
    /**
     * 
     * @return
     */
    public int size() {
	return columns.size();
    }
    /**
     * 
     */
    public void move(){//int nn) {
	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).move(nn);
	}
    }
    /**
     * 
     * @param column
     * @return
     */
    public Row field(Column column) {
	columns.add(column);
	return this;
    }
    
}
