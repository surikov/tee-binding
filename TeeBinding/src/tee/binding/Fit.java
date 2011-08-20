package tee.binding;

import java.util.*;

public class Fit<Kind> {
    Hashtable<String, It<Kind>> currentValues = new Hashtable<String, It<Kind>>();
    Hashtable<String, Hashtable<String, It<Kind>>> sets = new Hashtable<String, Hashtable<String, It<Kind>>>();
    Characters _selector = new Characters().afterChange(new Task() {
        @Override
        public void job() {
            refreshSet();
        }
    }).value("");
    public Characters selector() {
        return _selector;
    }
    public It<Kind> find(String key) {
        It<Kind> v = currentValues.get(key);
        if (v == null) {
            v = new It<Kind>();
        }
        currentValues.put(key, v);
        return v;
    }
    private void refreshSet() {
        if (_selector == null) {
            return;
        }
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
    public Fit<Kind> item(String group, String key, Kind value) {
        Hashtable<String, It<Kind>> s = findSet(group);
        It<Kind> v = s.get(key);
        if (v == null) {
            v = new It< Kind>();
            s.put(key, v);
        }
        v.value(value);
        refreshSet();
        return this;
    }
    public static void main(String[] a) {
        System.out.println("\nFit\n");
        Fit<String> g = new Fit<String>().item("English", "w1", "First").item("English", "w2", "Second").item("English", "w3", "Third").item("Spain", "w1", "Primero").item("Spain", "w2", "Segundo").item("Spain", "w3", "Tercera");
        Characters s1 = new Characters().bind(g.find("w1"));
        Characters s2 = new Characters().bind(g.find("w2"));
        Characters s3 = new Characters().bind(g.find("w3"));
        Characters s4 = new Characters().bind(g.find("w4"));
        System.out.println("/set English");
        g.selector().value("English");
        System.out.println(s1.value() + ", " + s2.value() + ", " + s3.value() + ", " + s4.value());
        System.out.println("/set Spain");
        g.selector().value("Spain");
        System.out.println(s1.value() + ", " + s2.value() + ", " + s3.value() + ", " + s4.value());
    }
}
