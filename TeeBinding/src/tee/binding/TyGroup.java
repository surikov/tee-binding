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
	g.add("English", "w1", "First");	
	g.add("English", "w2", "Second");
	g.add("English", "w3", "Third");
	g.add("Spain", "w1", "Primero");
	g.add("Spain", "w2", "Segundo");	
	g.add("Spain", "w3", "Tercera");
	TyValue<String> s1 = new TyValue<String>(g.get("w1"));
	TyValue<String> s2 = new TyValue<String>(g.get("w2"));
	TyValue<String> s3 = new TyValue<String>(g.get("w3"));
	System.out.println("set English");
	g.current().set("English");
	System.out.println(s1.get() + " / " + s2.get() + " / " + s3.get());
	System.out.println("set Spain");
	g.current().set("Spain");
	System.out.println(s1.get() + " / " + s2.get() + " / " + s3.get());
    }
}
