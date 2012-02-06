package tee.these;

import java.util.*;
import tee.binding.view.*;

public class Series {

    private Vector<These> columns;

    public Series() {
	columns = new Vector<These>();
    }

    public void select(int nn) {

	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).select(nn);
	}
    }

    public void drop(int nn) {
	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).drop(nn);
	}
    }

    public Series field(These column) {
	columns.add(column);
	return this;
    }
}
