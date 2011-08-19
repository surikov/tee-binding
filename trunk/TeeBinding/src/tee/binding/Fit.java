package tee.binding;

import java.util.*;

public class Fit<Kind> {

    Hashtable<String, It<Kind>> currentValues = new Hashtable<String, It<Kind>>();
    Hashtable<String, Hashtable<String, It<Kind>>> sets = new Hashtable<String, Hashtable<String, It<Kind>>>();
    Characters _current = new Characters() {

	@Override
	public void afterChange() {
	    refreshSet();
	}
    }.value("");

    public Characters current() {
	return _current;
    }

    public It<Kind> get(String key) {
	It<Kind> v = currentValues.get(key);
	if (v == null) {
	    v = new It<Kind>();
	}
	currentValues.put(key, v);
	return v;
    }

    private void refreshSet() {
	if (_current == null) {
	    return;
	}
	//System.out.println(_current.get());
	Hashtable<String, It<Kind>> choosenSet = findSet(_current.value());
	for (Enumeration<String> e = choosenSet.keys(); e.hasMoreElements();) {
	    String k = e.nextElement();
	    get(k).value(choosenSet.get(k).value());
	    //System.out.println(k);
	}
    }

    private Hashtable<String, It<Kind>> findSet(String group) {
	Hashtable<String, It<Kind>> s = sets.get(group);
	if (s == null) {
	    s = new Hashtable<String, It<Kind>>();
	    sets.put(group, s);
	}
	return s;
    }

    public void add(String group, String key, Kind value) {
	Hashtable<String, It<Kind>> s = findSet(group);
	It<Kind> v = s.get(key);
	if (v == null) {
	    v = new It< Kind>();
	    s.put(key, v);
	}
	v.value(value);
	refreshSet();
    }

    public static void main(String[] a) {
	Fit<String> g = new Fit<String>();
	g.add("English", "w1", "First");	
	g.add("English", "w2", "Second");
	g.add("English", "w3", "Third");
	g.add("Spain", "w1", "Primero");
	g.add("Spain", "w2", "Segundo");	
	g.add("Spain", "w3", "Tercera");
	It<String> s1 = new It<String>(g.get("w1"));
	It<String> s2 = new It<String>(g.get("w2"));
	It<String> s3 = new It<String>(g.get("w3"));
	System.out.println("set English");
	g.current().value("English");
	System.out.println(s1.value() + " / " + s2.value() + " / " + s3.value());
	System.out.println("set Spain");
	g.current().value("Spain");
	System.out.println(s1.value() + " / " + s2.value() + " / " + s3.value());
    }
}
