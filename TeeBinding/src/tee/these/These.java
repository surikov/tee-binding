package tee.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.*;
import tee.binding.task.*;

public class These<Kind> {
    protected It<Kind> current;
    protected Vector<It<Kind>> values;
    protected Numeric select;
    protected int oldSel = -1;
    These<Kind> me;
    public These() {
	me = this;
	current = new It<Kind>();
	values = new Vector<It<Kind>>();
	select = new Numeric().value(-1).afterChange(new Task() {
	    @Override public void doTask() {
		if (oldSel >= 0 && oldSel < values.size()) {
		    current.unbind(values.get(oldSel));
		}
		if (select != null) {
		    if (select.value() != null) {
			if (select.value() >= 0 && select.value() < values.size()) {
			    current.bind(values.get(select.value().intValue()));
			    oldSel = select.value().intValue();
			}
		    }
		}
	    }
	});
    }
    public It<Kind> is() {
	return current;
    }
    public These<Kind> is(It<Kind> it) {
	It<Kind> v = new It<Kind>().bind(it);
	values.add(v);
	return this;
    }
    public These<Kind> is(Kind it) {
	It<Kind> v = new It<Kind>().value(it);
	values.add(v);
	return this;
    }
    public Numeric select() {
	return select;
    }
    public These<Kind> select(int nn) {
	select.value(nn);
	return this;
    }
    public static void main(String[] a) {

	These<String> s = new These<String>().is("1").is("2").is("3").select(0);
	System.out.println("test");
	System.out.println(s.is().value());
    }
}
