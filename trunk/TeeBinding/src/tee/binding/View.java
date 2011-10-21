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

    public View where(Approver<Row> approver) {
	View newView = new View();
	for (int r = 0; r < this.rows.size(); r++) {
	    Row row = this.rows.get(r);
	    if (approver.approve(row)) {
		Row nr = new Row();
		for (int f = 0; f < row.size(); f++) {
		    Item itm = row.item(f);
		    nr.item(itm.column, itm.value);
		}
		newView.row(nr);
	    }
	}
	return newView;
    }

    public static void dump(View v) {
	for (int i = 0; i < v.rows.size(); i++) {
	    Row r = v.rows.get(i);
	    for (int n = 0; n < r.size(); n++) {
		//System.out.print(" /" + r.item(n).column.title().value() + ": " + r.item(n).value);
	    }
	    System.out.println();
	}
    }

    public static void main(String[] args) {
	System.out.println("\nView\n");
	//Relation table = new Relation();
	Column nm = new Column();
	ColumnNumeric age = new ColumnNumeric();
	ColumnNote mail = new ColumnNote();
//nm.value.value("df");
//System.out.println(nm.value.value());
Column s=age;
s.value.value(52);
System.out.println(s.value);
System.out.println(s.value.value());

	View addrBook = new View()//
		//.column(nm)//
		//.column(mail)//
		.row(new Row().item(nm, "Vasya").item(age, "19").item(mail, "vpupkin@mail.ru"))//
		.row(new Row().item(nm, "Petya").item(age, "22").item(mail, "petrpetrov@gmail.com"))//
		.row(new Row().item(nm, "Sasha").item(age, "20").item(mail, "alxndr@aol.com"))//
		;
	//System.out.println(addrBook.columns());
	//age.value().moreThen(20)
	dump(addrBook.where(new Approver<Row>() {

	    @Override public boolean approve(Row it) {
		if (it.item(0).value.startsWith("V")) {
		    return false;
		} else {
		    return true;
		}
	    }
	}));
    }
}
