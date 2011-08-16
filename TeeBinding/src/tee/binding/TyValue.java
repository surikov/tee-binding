package tee.binding;

import java.util.*;

public class TyValue<Kind> {

    private Vector<TyValue> _binded = new Vector<TyValue>();
    private Kind _value = null;

    public TyValue() {
    }

    public TyValue(Kind initVal) {
	set(initVal);
    }

    public TyValue(TyValue<Kind> bindVal) {
	this.tie(bindVal);
    }

    public Kind get() {
	return _value;
    }

    public void set(Kind newValue) {
	setForEachBindedItem(newValue, new Vector<TyValue>());
    }

    private void setForEachBindedItem(Kind newValue, Vector<TyValue> cashe) {
	if (get() == null && newValue == null) {
	    return;
	}
	if (get() != null && get().equals(newValue)) {
	    return;
	}
	this._value = newValue;
	cashe.add(this);
	for (int i = 0; i < _binded.size(); i++) {
	    if (!cashe.contains(_binded.get(i))) {
		_binded.get(i).setForEachBindedItem(newValue, cashe);
	    }
	}
	cashe.remove(this);
	afterChange();
    }

    public void afterChange() {
    }

    public void tie(TyValue<Kind> to) {
	if (to == null) {
	    return;
	}
	if (!this._binded.contains(to)) {
	    this._binded.add(to);
	}
	if (!to._binded.contains(this)) {
	    to._binded.add(this);
	}
	this.set(to.get());
    }

    public void untie(TyValue<Kind> to) {
	if (to == null) {
	    return;
	}
	this._binded.remove(to);
	to._binded.remove(this);
    }

    public void untie() {
	for (int i = 0; i < _binded.size(); i++) {
	    _binded.get(i).untie(this);
	}
    }

    public static void main(String[] args) {
	System.out.println("Simple tying example\n-----");
	TyValue<String> a = new TyValue<String>("A") {

	    @Override
	    public void afterChange() {
		System.out.println("[a]: someone changed value to " + get());
	    }
	};
	TyValue<String> b = new TyValue<String>("B") {

	    @Override
	    public void afterChange() {
		System.out.println("[b]: someone changed value to " + get());
	    }
	};
	TyValue<String> c = new TyValue<String>("C") {

	    @Override
	    public void afterChange() {
		System.out.println("[c]: someone changed value to " + get());
	    }
	};
	System.out.println("a: " + a.get() + ", b: " + b.get() + ", c: " + c.get());
	System.out.println("#tie variables");
	a.tie(b);
	b.tie(c);
	c.tie(a);
	System.out.println("a: " + a.get() + ", b: " + b.get() + ", c: " + c.get());
	System.out.println("#let a=D");
	a.set("D");
	System.out.println("a: " + a.get() + ", b: " + b.get() + ", c: " + c.get());
	System.out.println("#a untie b");
	a.untie(b);
	System.out.println("#let a=E");
	a.set("E");
	System.out.println("a: " + a.get() + ", b: " + b.get() + ", c: " + c.get());
	System.out.println("#let b=F");
	b.set("F");
	System.out.println("a: " + a.get() + ", b: " + b.get() + ", c: " + c.get());
	System.out.println("#a untie c");
	a.untie(c);
	System.out.println("#let a=G");
	a.set("G");
	System.out.println("a: " + a.get() + ", b: " + b.get() + ", c: " + c.get());
	System.out.println("#let b=H");
	b.set("H");
	System.out.println("a: " + a.get() + ", b: " + b.get() + ", c: " + c.get());
	System.out.println("-----");
    }
}
