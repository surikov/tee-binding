package tee.binding;

import java.util.*;

public class Row {

    //private Vector<Column> columns;
    private Vector<Item> items;

    public Row() {
	items = new Vector<Item>();
	//columns = new Vector<Column>();
    }

    public int size() {
	return items.size();
    }

    public Item item(int n) {
	return items.get(n);
    }
/*
    public Column column(int n) {
	return columns.get(n);
    }*/

    public Row item(Column column, String value) {
	items.add(new Item(this,column,value));
	//columns.add(column);
	return this;
    }
}
