package tee.binding;

import tee.binding.properties.*;
import java.util.*;

/**
 * 
 * @author User
 */
public class Bough {

    public NoteProperty<Bough> name = new NoteProperty(this);
    public NoteProperty<Bough> value = new NoteProperty(this);
    public Vector<Bough> children = new Vector<Bough>();
    public ToggleProperty<Bough> attribute = new ToggleProperty<Bough>(this);

    public Bough child(Bough b) {
        children.add(b);
        return this;
    }

    public Bough child(String n) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).name.property.value().equals(n)) {
                return children.get(i);
            }
        }
        Bough b = new Bough().name.is(n);
        children.add(b);
        return b;
    }

    public Vector<Bough> children(String n) {
        Vector<Bough> c = new Vector<Bough>();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).name.property.value().equals(n)) {
                c.add(children.get(i));
            }
        }
        return c;
    }

    public static void dump(StringBuilder sb, Bough b, String pad) {
        sb.append("\n" + pad + "<" + b.name.property.value());
        for (int i = 0; i < b.children.size(); i++) {
            if (b.children.get(i).attribute.property.value()) {
                sb.append(" " + b.children.get(i).name.property.value() //
                        + "=\"" + b.children.get(i).value.property.value() + "\"");
            }
        }
        sb.append(">" + b.value.property.value());
        boolean hasChildren = false;
        for (int i = 0; i < b.children.size(); i++) {
            if (!b.children.get(i).attribute.property.value()) {
                hasChildren = true;
                dump(sb, b.children.get(i), "\t" + pad);
            }
        }
        if (hasChildren) {
            sb.append("\n"+pad + "</" + b.name.property.value()+">");
        } else {
            sb.append( "</" + b.name.property.value()+">");
        }
    }

    public static void main(String[] a) {
        Bough b = new Bough().name.is("Test").value.is("Ops");
        b.child("test").child("test2").child("test3").attribute.is(true).value.is("Ya!");
        b.child("test").child("test4").value.is("Nope!");
        b.child("test").child(new Bough().name.is("test5").value.is("Yet!"));
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        dump(sb, b, "");        
        System.out.println(sb.toString());
    }
}
