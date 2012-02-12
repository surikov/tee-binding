package tee.binding.these;

import java.util.*;
import tee.binding.view.*;

/**
 * 
 * @author User
 */
public class Series {

    private Vector<These> columns;

    /**
     * 
     */
    public Series() {
	columns = new Vector<These>();
    }

    /**
     * 
     * @param nn
     */
    public void select(int nn) {

	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).select(nn);
	}
    }
    public void probe(int nn) {

	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).probe(nn);
	}
    }

    /**
     * 
     * @param nn
     */
    public void drop(int nn) {
	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).drop(nn);
	}
    }

    /**
     * 
     * @param column
     * @return
     */
    public Series field(These column) {
	columns.add(column);
	return this;
    }
}
