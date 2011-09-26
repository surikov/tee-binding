package tee.binding;

import java.util.*;

public class It<Kind> {
    private Vector<It<Kind>> _binded = new Vector<It<Kind>>();
    protected Kind _value = null;
    private Task afterChange = null;
    public It() {
    }
    protected void adjust() {}
    public Kind value() {
	return _value;
    }
    public It<Kind> value(Kind newValue) {
	setForEachBindedItem(newValue, new Vector<It>());
	return this;
    }
    private void setForEachBindedItem(Kind newValue, Vector<It> cashe) {
	if (value() == null && newValue == null) {
	    return;
	}
	if (value() != null && value().equals(newValue)) {
	    return;
	}
	this._value = newValue;
	adjust();
	cashe.add(this);
	for (int i = 0; i < _binded.size(); i++) {
	    if (!cashe.contains(_binded.get(i))) {
		_binded.get(i).
			setForEachBindedItem(newValue, cashe);
	    }
	}
	cashe.remove(this);
	doAfterChange();
    }
    public It<Kind> afterChange(Task it) {
	this.afterChange = it;
	doAfterChange();
	return this;
    }
    private void doAfterChange() {
	if (this.afterChange != null) {
	    afterChange.start();
	}
    }
    public It<Kind> read() {
	final It<Kind> r = new It<Kind>().value(value());
	final It<Kind> watcher = new It<Kind>().bind(this);
	watcher.afterChange(new Task() {
	    @Override public void doTask() {
		r.value(watcher.value());
	    }
	});
	r.afterChange(new Task() {
	    @Override public void doTask() {
		r.value(watcher.value());
	    }
	});
	return r;
    }
    public It<Kind> bind(It<Kind> to) {
	if (to == null) {
	    return this;
	}
	if (!this._binded.contains(to)) {
	    this._binded.add(to);
	}
	if (!to._binded.contains(this)) {
	    to._binded.add(this);
	}
	this.value(to.value());
	return this;
    }
    public void unbind(It<Kind> to) {
	if (to == null) {
	    return;
	}
	this._binded.remove(to);
	to._binded.remove(this);
    }
    public void unbind() {
	for (int i = 0; i < _binded.size(); i++) {
	    _binded.get(i).
		    unbind(this);
	}
    }
    public static void main(String[] args) {

	System.out.println("\nIt\n");
	final It<String> a = new It<String>().value("A");
	final It<String> b = new It<String>().value("B");
	final It<String> c = new It<String>().value("C");
	a.afterChange(new Task() {
	    @Override public void doTask() {
		System.out.println("[a]: someone changed value to " + a.value());
	    }
	});
	b.afterChange(new Task() {
	    @Override public void doTask() {
		System.out.println("[b]: someone changed value to " + b.value());
	    }
	});
	a.afterChange(new Task() {
	    @Override public void doTask() {
		System.out.println("[c]: someone changed value to " + c.value());
	    }
	});
	System.out.println("a: " + a.value() + ", b: " + b.value() + ", c: " + c.value());
	System.out.println("#bind variables");
	a.bind(b);
	b.bind(c);
	c.bind(a);
	System.out.println("a: " + a.value() + ", b: " + b.value() + ", c: " + c.value());
	System.out.println("#let a=D");
	a.value("D");
	System.out.println("a: " + a.value() + ", b: " + b.value() + ", c: " + c.value());
	System.out.println("#a unbind b");
	a.unbind(b);
	System.out.println("#let a=E");
	a.value("E");
	System.out.println("a: " + a.value() + ", b: " + b.value() + ", c: " + c.value());
	System.out.println("#let b=F");
	b.value("F");
	System.out.println("a: " + a.value() + ", b: " + b.value() + ", c: " + c.value());
	System.out.println("#a unbind c");
	a.unbind(c);
	System.out.println("#let a=G");
	a.value("G");
	System.out.println("a: " + a.value() + ", b: " + b.value() + ", c: " + c.value());
	System.out.println("#let b=H");
	b.value("H");
	System.out.println("a: " + a.value() + ", b: " + b.value() + ", c: " + c.value());
    }
}
