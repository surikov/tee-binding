package tee.binding;
import java.util.*;
public class ColumnNumeric extends Column {
    private Numeric value;
    private Vector<Numeric> values;
    public ColumnNumeric() {
	value = new Numeric();
	values = new Vector<Numeric>();
    }
    @Override public void move(int nn) {
	if (nn >= 0 && nn < values.size()) {
	    value.value(values.get(nn).value());
	} else {
	    value.value(0);
	}
    }
    public ColumnNumeric value(double it) {
	Numeric v = new Numeric().value(it);
	values.add(v);
	return this;
    }
    public ColumnNumeric value(int it) {
	Numeric v = new Numeric().value(it);
	values.add(v);
	return this;
    }
    public ColumnNumeric value(Numeric it) {
	Numeric v = new Numeric().bind(it);
	values.add(v);
	return this;
    }
    public Numeric value() {
	return value;
    }
}
