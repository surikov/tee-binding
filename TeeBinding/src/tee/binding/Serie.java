package tee.binding;

import java.util.*;

public class Serie<Kind> {
    private Vector<Kind> serie = new Vector<Kind>();
    private Task afterAppend = null;
    private Task afterClear = null;
    public Serie<Kind> append(Kind it) {
	serie.add(it);
	if (afterAppend != null) {
	    afterAppend.job();
	}
	return this;
    }
    public Serie<Kind> afterAppend(Task it) {
	afterAppend = it;
	return this;
    }
    public Serie<Kind> afterClear(Task it) {
	afterClear = it;
	return this;
    }
    public Serie<Kind> clear() {
	serie.clear();
	if (afterClear != null) {
	    afterClear.job();
	}
	return this;
    }
    public Kind item(int nn) {
	return serie.get(nn);
    }
    public int size() {
	return serie.size();
    }
    public static void main(String[] args) {
	System.out.println("\nSerie\n");
    }
}
