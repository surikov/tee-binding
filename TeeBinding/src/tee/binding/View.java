package tee.binding;

import java.util.*;

public class View {

    private Vector<Row> rows;
    private int size;

    public View() {
	rows = new Vector<Row>();
    }

    public int columns() {
	return size;
    }

    public View row(Row row) {
	rows.add(row);
	return this;
    }

    public View where() {
	View nv = new View();
	for (int r = 0; r < this.rows.size(); r++) {
	    Row row = this.rows.get(r);
	    Row nr=new Row();
	    for (int f = 0; f < row.size(); f++) {
		Item itm=row.item(f);
		nr.item(itm.column,itm.value);
	    }
	    nv.row(nr);
	}
	return nv;
    }

    public static void dump(View v) {
	for (int i = 0; i < v.rows.size(); i++) {
	    Row r = v.rows.get(i);
	    for (int n = 0; n < r.size(); n++) {
		System.out.print(" /" + r.item(n).column.title().value() + ": " + r.item(n).value);
	    }
	    System.out.println();
	}
    }

    public static void main(String[] args) {
	System.out.println("\nView\n");
	//Relation table = new Relation();
	Column nm = new Column().title("name");
	Column mail = new Column().title("mail");
	View addrBook = new View()//
		//.column(nm)//
		//.column(mail)//
		.row(new Row().item(nm, "Vasya").item(mail, "vpupkin@mail.ru"))//
		.row(new Row().item(nm, "Petya").item(mail, "petrpetrov@gmail.com"))//
		.row(new Row().item(nm, "Sasha").item(mail, "alxndr@aol.com"))//
		;
	//System.out.println(addrBook.columns());
	dump(addrBook.where());
    }
}
