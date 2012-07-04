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

    public static void dump(Bough b, String pad) {
        System.out.print(pad);
        System.out.print(b.name.property.value());
        System.out.print(": ");
        if (b.attribute.property.value()) {
            System.out.print("{");
        } else {
            System.out.print("[");
        }
        
        System.out.print(b.value.property.value());
        if (b.attribute.property.value()) {
            System.out.println("}");
        } else {
            System.out.println("]");
        }
        for (int i = 0; i < b.children.size(); i++) {
            dump(b.children.get(i),"."+pad);
        }
    }

    public static void main(String[] a) {
        Bough b = new Bough().name.is("Test").value.is("Ops");
        b.child("test").child("test2").child("test3").value.is("Ya!");
        System.out.println(b.child("test").child("test2").child("test3").value.property.value());
        dump(b, "");
    }
}
