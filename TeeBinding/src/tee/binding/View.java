package tee.binding;
import java.util.*;
public class View {
    private Vector<Row> rows;
    //private int size;
    public View() {
	rows = new Vector<Row>();
    }
    /*
     * public int columns() { return size; }
     */
    public View row(Row row) {
	rows.add(row);
	return this;
    }
    /*
     * public View where(Approver<Row> approver) { View newView = new View();
     * for (int r = 0; r < this.rows.size(); r++) { Row row = this.rows.get(r);
     * if (approver.approve(row)) { Row nr = new Row(); for (int f = 0; f <
     * row.size(); f++) { Item itm = row.item(f); nr.item(itm.column, itm.value);
     * } newView.row(nr); } } return newView; }
     */
    /*
     * public static void dump(View v) { for (int i = 0; i < v.rows.size(); i++)
     * { Row r = v.rows.get(i); for (int n = 0; n < r.size(); n++) {
     * //System.out.print(" /" + r.item(n).column.title().value() + ": " +
     * r.item(n).value); } System.out.println(); } }
     */
    public static void main(String[] args) {
	System.out.println("\nView\n");
	//Relation table = new Relation();
	ColumnNote nm = new ColumnNote();
	ColumnNumeric age = new ColumnNumeric();
	ColumnNote mail = new ColumnNote();
//nm.value.value("df");
//System.out.println(nm.value.value());
/*
	 * Column s=age; s.value.value(52); System.out.println(s.value);
	 * System.out.println(s.value.value());
	 */
	View addrBook = new View()//
		//.column(nm)//
		//.column(mail)//
		.row(new Row().column(nm.value("Vasya")).column(age.value(19)).column(mail.value("vpupkin@mail.ru")))//
		.row(new Row().column(nm.value("Petya")).column(age.value(22)).column(mail.value("petrpetrov@gmail.com")))//
		.row(new Row().column(nm.value("Sasha")).column(age.value(20)).column(mail.value("alxndr@aol.com")))//
		;
	View dump = addrBook;
	for (int r = 0; r < dump.rows.size(); r++) {
	    Row row = dump.rows.get(r);
	    row.move(r);
	    System.out.print("" + r + ": name[" + nm.value().value() + "]"
		    + ": age[" + age.value().value() + "]"
		    + ": email[" + mail.value().value() + "]"
		    );
	    //for(int f=0;f<row.size();f++){
	    //System.out.print("/");
	    //}
	    System.out.println();
	}
	//System.out.println(addrBook.columns());
	//age.value().moreThen(20)
	/*
	 * dump(addrBook.where(new Approver<Row>() {
	 *
	 * @Override public boolean approve(Row it) { if
	 * (it.item(0).value.startsWith("V")) { return false; } else { return
	 * true; } } }));
	 */
    }
}
