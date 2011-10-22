package tee.binding;
import java.util.*;
public class View {
    private Vector<Row> rows;
    public View() {
	rows = new Vector<Row>();
    }
    public View row(Row row) {
	int nn = rows.size();
	row.nn = nn;
	rows.add(row);
	return this;
    }
    public void move(int nn) {
	if (rows.size() > 0) {
	    int k = nn;
	    if (k < 0) {
		k = 0;
	    }
	    if (k > rows.size() - 1) {
		k = 0;
	    }
	    rows.get(nn).move();
	}
    }
    public View where(Toggle toggle) {
	View filtered = new View();
	for (int r = 0; r < this.rows.size(); r++) {
	    this.move(r);
	    if (toggle.value()) {
		filtered.rows.add(rows.get(r));
	    }
	}
	return filtered;
    }
    public static void main(String[] args) {
	System.out.println("\nView\n");
	ColumnNote nm = new ColumnNote();
	ColumnNumeric age = new ColumnNumeric();
	ColumnNote mail = new ColumnNote();
	ColumnToggle man = new ColumnToggle();
	View addrBook = new View()//
		.row(new Row().field(nm.is("Vasya")).field(man.is(true)).field(age.is(19)).field(mail.is("vpupkin@mail.ru")))//
		.row(new Row().field(nm.is("Petya")).field(man.is(true)).field(age.is(22)).field(mail.is("petrpetrov@gmail.com")))//
		.row(new Row().field(nm.is("Sasha")).field(man.is(true)).field(age.is(20)).field(mail.is("alxndr@aol.com")))//
		.row(new Row().field(nm.is("Masha")).field(man.is(false)).field(age.is(18)).field(mail.is("masha@mail.ru")))//
		.row(new Row().field(nm.is("Kolya")).field(man.is(true)).field(age.is(21)).field(mail.is("nikolay@gmail.com")))//
		.row(new Row().field(nm.is("Vanya")).field(man.is(true)).field(age.is(22)).field(mail.is("ivan@mail.ru")))//
		.row(new Row().field(nm.is("Olya")).field(man.is(false)).field(age.is(17)).field(mail.is("olga@aol.com")))//
		.row(new Row().field(nm.is("Vika")).field(man.is(false)).field(age.is(21)).field(mail.is("avictorya@gmail.com")))//
		.row(new Row().field(nm.is("Misha")).field(man.is(true)).field(age.is(23)).field(mail.is("mike@mail.ru")))//
		.row(new Row().field(nm.is("Glasha")).field(man.is(false)).field(age.is(20)).field(mail.is("glasha@gmail.com")))//
		;
	Toggle t = man.is().not();
		//age.is().moreOrEquals(20);
	View dump = addrBook.where(t);
	for (int r = 0; r < dump.rows.size(); r++) {
	    dump.move(r);
	    System.out.print(t.value() + ": ");
	    System.out.print(""
		    + ": name[" + nm.is().value() + "]"
		    + ": age[" + age.is().value() + "]"
		    + ": email[" + mail.is().value() + "]");
	    System.out.println();
	}
    }
}
