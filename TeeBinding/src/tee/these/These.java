package tee.these;

import tee.binding.it.*;
import java.util.*;
import tee.binding.it.*;
import tee.binding.*;
import tee.binding.task.*;

public class These<Kind> {
    protected It<Kind> current;
    protected Vector<It<Kind>> values;
    protected Numeric select;
    protected int oldSel = 0;
    public These() {
	current = new It<Kind>();
	values = new Vector<It<Kind>>();
	select = new Numeric().value(0).afterChange(new Task() {
	    @Override public void doTask() {
		if (select != null) {
		    current.unbind(values.get(oldSel));
		    current.bind(values.get(select.value().intValue()));
		    oldSel = select.value().intValue();
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
    /*public It<Kind> select(int num) {
    current.unbind(this.values.get(select.value().intValue()));
    current.bind(this.values.get(num));
    select.value(num);
    return current;
    }*/
    public static void main(String[] a) {
	System.out.println("test");
	These<String>s=new These<String>().is("1").is("2").is("3");
	System.out.println(s.is().value());
    }
}
