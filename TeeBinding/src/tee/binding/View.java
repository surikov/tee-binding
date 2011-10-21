package tee.binding;
import java.util.*;
public class View {
    private Vector<Row> rows;
    public View() {
	rows = new Vector<Row>();
    }
    public View row(Row row) {
	rows.add(row);
	return this;
    }
    public void move(int nn) {
	rows.get(nn).move(nn);
    }
    public static void main(String[] args) {
	System.out.println("\nView\n");
	ColumnNote nm = new ColumnNote();
	ColumnNumeric age = new ColumnNumeric();
	ColumnNote mail = new ColumnNote();
	View addrBook = new View()//
		.row(new Row().field(nm.value("Vasya")).field(age.value(19)).field(mail.value("vpupkin@mail.ru")))//
		.row(new Row().field(nm.value("Petya")).field(age.value(22)).field(mail.value("petrpetrov@gmail.com")))//
		.row(new Row().field(nm.value("Sasha")).field(age.value(20)).field(mail.value("alxndr@aol.com")))//
		;
	View dump = addrBook;
	for (int r = 0; r < dump.rows.size(); r++) {
	    dump.move(r);
	    System.out.print("" + r
		    + ": name[" + nm.current().value() + "]"
		    + ": age[" + age.current().value() + "]"
		    + ": email[" + mail.current().value() + "]");
	    System.out.println();
	}
    }
}
