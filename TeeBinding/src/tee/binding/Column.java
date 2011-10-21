package tee.binding;

import java.util.*;

public class Column {

    private Vector<String> items;
    private int size;
    private Note title;

    public Column() {
	items = new Vector<String>();
	title=new Note();
	size = 0;
    }
public Column title(String t){
    this.title.value(t);
    return this;
}
public Note title(){

    return title;
}
    public Column item(String it) {
	items.add(it);
	size=items.size();
	return this;
    }

    public Column clear() {
	items.clear();
	size=0;
	return this;
    }

    public String item(int nn) {
	return items.get(nn);
    }

    public int size() {
	return size;
    }

    public static void main(String[] args) {
	System.out.println("\nColumn\n");
	//Attribute<Note> notes = new Attribute<Note>();
	//System.out.println(notes.size().value());
    }
}
