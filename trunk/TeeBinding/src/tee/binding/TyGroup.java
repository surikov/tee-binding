package tee.binding;

import java.util.*;

public class TyGroup<Kind> {

    Hashtable<String, TyValue<Kind>> currentValues = new Hashtable<String, TyValue<Kind>>();
    Hashtable<String, Hashtable<String, TyValue<Kind>>> sets = new Hashtable<String, Hashtable<String, TyValue<Kind>>>();
    TyString _current = new TyString("") {

	@Override
	public void afterChange() {
	    refreshSet();
	}
    };

    public TyString current() {
	return _current;
    }

    public TyValue<Kind> get(String key) {
	TyValue<Kind> v = currentValues.get(key);
	if (v == null) {
	    v = new TyValue<Kind>();
	}
	currentValues.put(key, v);
	return v;
    }

    private void refreshSet() {
	if (_current == null) {
	    return;
	}
	//System.out.println(_current.get());
	Hashtable<String, TyValue<Kind>> choosenSet = findSet(_current.get());
	for (Enumeration<String> e = choosenSet.keys(); e.hasMoreElements();) {
	    String k = e.nextElement();
	    get(k).set(choosenSet.get(k).get());
	    //System.out.println(k);
	}
    }

    private Hashtable<String, TyValue<Kind>> findSet(String group) {
	Hashtable<String, TyValue<Kind>> s = sets.get(group);
	if (s == null) {
	    s = new Hashtable<String, TyValue<Kind>>();
	    sets.put(group, s);
	}
	return s;
    }

    public void add(String group, String key, Kind value) {
	Hashtable<String, TyValue<Kind>> s = findSet(group);
	TyValue<Kind> v = s.get(key);
	if (v == null) {
	    v = new TyValue< Kind>();
	    s.put(key, v);
	}
	v.set(value);
	refreshSet();
    }

    public static void main(String[] a) {
	TyGroup<String> g = new TyGroup<String>();
	g.add("lower", "first", "one");
	g.add("upper", "first", "ONE");
	g.add("lower", "second", "two");
	g.add("upper", "second", "TWO");
	g.add("lower", "third", "three");
	g.add("upper", "third", "THREE");
	TyValue<String> first = new TyValue<String>(g.get("first"));
	TyValue<String> second = new TyValue<String>(g.get("second"));
	TyValue<String> third = new TyValue<String>(g.get("third"));
	g.current().set("lower");
	System.out.println(first.get() + " / " + second.get() + " / " + third.get());
	g.current().set("upper");
	System.out.println(first.get() + " / " + second.get() + " / " + third.get());
	g.current().set("unknown");
	System.out.println(first.get() + " / " + second.get() + " / " + third.get());
    }
}
