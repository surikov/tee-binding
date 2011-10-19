package tee.binding;

import java.util.*;

public class Fit<Kind> {

    Hashtable<String, It<Kind>> currentSet = new Hashtable<String, It<Kind>>();
    Hashtable<String, Hashtable<String, It<Kind>>> sets = new Hashtable<String, Hashtable<String, It<Kind>>>();
    Fit<Kind> tie = null;
    //private Task afterTie = null;
    //private Task afterItem = null;
    Note _selector = new Note().afterChange(new Task() {

	@Override public void doTask() {
	    refreshSet();
	}
    }).value("");

    public Note selector() {
	return _selector;
    }
    /*
    public Fit<Kind> afterTie(Task it) {
	this.afterTie = it;
	doAfterTie();
	return this;
    }

    private void doAfterTie() {
	if (this.afterTie != null) {
	    afterTie.start();
	}
    }

    public Fit<Kind> afterItem(Task it) {
	this.afterItem = it;
	doAfterItem();
	return this;
    }

    private void doAfterItem() {
	if (this.afterItem != null) {
	    afterItem.start();
	}
    }
*/

    public Fit<Kind> tie(Fit<Kind> to) {
	if (tie != null) {
	    _selector.unbind(tie._selector);
	}
	tie = to;
	if (to != null) {
	    _selector.bind(tie._selector);
	}
	//refreshSet();

	return this;
    }

    public It<Kind> find(String key) {
	It<Kind> r;
	if (tie != null) {
	    r = tie.find(key);
	    //System.out.println("tied "+key+": "+r.value());
	} else {
	    It<Kind> v = currentSet.get(key);
	    if (v == null) {
		v = new It<Kind>();
		currentSet.put(key, v);
	    }
	    r = v;
	    //System.out.println("real "+key+": "+r.value());
	}
	return r;
    }

    private void refreshSet() {
	//System.out.println("refreshSet");
	if (_selector == null) {
	    //System.out.println("null");
	    return;
	}
	if (tie != null) {
	    //System.out.println("redirect");
	    //tie.refreshSet();
	    return;
	}
	/* Hashtable<String, It<Kind>> choosenSet = findSet(_selector.value());
	 for (Enumeration<String> e = choosenSet.keys(); e.hasMoreElements();) {
	 String k = e.nextElement();
	 find(k).value(choosenSet.get(k).value());
	 } */
	Hashtable<String, It<Kind>> choosenSet = findSet(_selector.value());
	for (Enumeration<String> e = choosenSet.keys(); e.hasMoreElements();) {
	    String k = e.nextElement();
	    find(k).value(choosenSet.get(k).value());
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

    /*public Hashtable<String, Hashtable<String, It<Kind>>> raw() {
	return sets;
    }*/
    /*public void clear() {

    }*/
    public Fit<Kind> item(String group, String key, Kind value) {
	if (tie != null) {
	    tie.item(group, key, value);
	} else {
	    Hashtable<String, It<Kind>> s = findSet(group);
	    It<Kind> v = s.get(key);
	    if (v == null) {
		v = new It< Kind>();
		s.put(key, v);
	    }
	    v.value(value);
	    refreshSet();
	}
	return this;
    }

    public static void main(String[] a) {
	System.out.println("\nFit\n");
	Fit<String> g = new Fit<String>();
	Fit<String> data = new Fit<String>();
	g.tie(data);
	Note s1 = new Note().bind(g.find("w1"));
	Note s2 = new Note().bind(g.find("w2"));
	Note s3 = new Note().bind(g.find("w3"));
	Note s4 = new Note().bind(g.find("w4"));
	data.item("English", "w1", "First").item("English", "w2", "Second").item("English", "w3", "Third").item("Spain", "w1", "Primero").item("Spain", "w2", "Segundo").item("Spain", "w3", "Tercera");

	System.out.println("/set English");
	g.selector().value("English");
	System.out.println(s1.value() + ", " + s2.value() + ", " + s3.value() + ", " + s4.value());
	System.out.println("/set Spain");
	g.selector().value("Spain");
	System.out.println(s1.value() + ", " + s2.value() + ", " + s3.value() + ", " + s4.value());
    }
}
