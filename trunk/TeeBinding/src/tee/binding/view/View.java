package tee.binding.view;

import tee.binding.view.ColumnToggle;
import tee.binding.view.ColumnNumeric;
import tee.binding.view.ColumnNote;
import tee.binding.it.Numeric;
import tee.binding.it.Toggle;
import tee.binding.it.Note;
import java.util.*;
import tee.binding.task.Task;

/**
 * 
 * @author User
 */
public class View {

    private Vector<Row> rows;
    private Vector<View> children;
    private Task requery;
    private Task afterChange;
    final View me;

    /**
     * 
     */
    public View() {
	me = this;
	rows = new Vector<Row>();
	requery = null;
	children = new Vector<View>();
    }

    /**
     * 
     * @param it
     * @return
     */
    public View afterChange(Task it) {
	afterChange = it;
	return this;
    }

    private void refreshChildren() {
	for (int i = 0; i < children.size(); i++) {
	    children.get(i).clear();
	    if (children.get(i).requery != null) {
		children.get(i).requery.start();
	    }
	    children.get(i).refreshChildren();
	}
	if (afterChange != null) {
	    afterChange.start();
	}
    }

    /**
     * 
     * @param row
     * @return
     */
    public View row(Row row) {
	int nn = rows.size();
	row.nn = nn;
	rows.add(row);
	refreshChildren();
	return this;
    }

    /**
     * 
     * @param row
     */
    public void drop(Row row) {
	//row.clear();
	this.rows.remove(row);
	refreshChildren();
    }

    /**
     * 
     */
    public void cleanUp() {
	this.rows.removeAllElements();
	refreshChildren();
    }

    /**
     * 
     * @param nn
     * @return
     */
    public Row move(int nn) {
	if (rows.size() > 0) {
	    int k = nn;
	    if (k < 0) {
		k = 0;
	    }
	    if (k > rows.size() - 1) {
		k = 0;
	    }
	    Row r = rows.get(nn);
	    r.move();
	    return r;
	} else {
	    return null;
	}
    }

    /**
     * 
     * @param comparator
     * @return
     */
    public View sort(Comparator comparator) {
	final View sorted = new View();
	final Comparator c = comparator;
	sorted.requery = new Task() {

	    @Override public void doTask() {
		sorted.clear();
		for (int r = 0; r < me.rows.size(); r++) {
		    me.move(r);
		    sorted.rows.add(rows.get(r));
		}
		Collections.sort(sorted.rows, c);
	    }
	};
	this.children.add(sorted);
	sorted.requery.start();
	return sorted;
    }

    /**
     * 
     * @param toggle
     * @return
     */
    public View select(Toggle toggle) {
	final View filtered = new View();
	final Toggle condition = toggle;
	filtered.requery = new Task() {

	    @Override public void doTask() {
		filtered.clear();
		for (int r = 0; r < me.rows.size(); r++) {
		    me.move(r);
		    if (condition.value()) {
			filtered.rows.add(rows.get(r));
		    }
		}
	    }
	};
	this.children.add(filtered);
	filtered.requery.start();
	return filtered;
    }
    /*public View select() {
	final View cpy = new View();
	final View me = this;
	cpy.requery = new Task() {
	    @Override public void doTask() {
		for (int r = 0; r < me.rows.size(); r++) {
		    me.move(r);
		    cpy.rows.add(rows.get(r));
		}
	    }
	};
	this.children.add(cpy);
	cpy.requery.start();
	return cpy;
    }*/

    private void clear() {
	this.rows.removeAllElements();
    }

    /**
     * 
     * @param nn
     * @return
     */
    public Numeric row(Numeric nn) {
	final Numeric rowNumber = new Numeric();
	final Numeric index = new Numeric().bind(nn);
	index.afterChange(new Task() {

	    @Override public void doTask() {
		if (index != null) {
		    //System.out.println("index-----------------"+index);
		    if (index.value() != null) {
			int nn = index.value().intValue();
			if (nn >= 0 && nn < rows.size()) {
			    rowNumber.value(rows.get(nn).nn);
			} else {
			    rowNumber.value(-1);
			}
		    }
		}
	    }
	});
	return rowNumber;
    }

    /**
     * 
     * @return
     */
    public int size() {
	return rows.size();
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("\nView\n");
	ColumnNote name = new ColumnNote();
	ColumnNumeric age = new ColumnNumeric();
	ColumnNote mail = new ColumnNote();
	ColumnToggle man = new ColumnToggle();
	Note descr = new Note().bind(name.is().append(", ").append(mail.is()));
	View addrBook = new View()//
		.row(new Row().field(name.is("Vasya")).field(man.is(true)).field(age.is(19)).field(mail.is("vpupkin@mail.ru")))//
		.row(new Row().field(name.is("Petya")).field(man.is(true)).field(age.is(22)).field(mail.is("petrpetrov@gmail.com")))//
		.row(new Row().field(name.is("Sasha")).field(man.is(true)).field(age.is(20)).field(mail.is("alxndr@aol.com")))//
		.row(new Row().field(name.is("Masha")).field(man.is(false)).field(age.is(21)).field(mail.is("masha@mail.ru")))//
		.row(new Row().field(name.is("Kolya")).field(man.is(true)).field(age.is(21)).field(mail.is("nikolay@gmail.com")))//
		.row(new Row().field(name.is("Vanya")).field(man.is(true)).field(age.is(22)).field(mail.is("ivan@mail.ru")))//
		.row(new Row().field(name.is("Olya")).field(man.is(false)).field(age.is(19)).field(mail.is("olga@aol.com")))//
		.row(new Row().field(name.is("Vika")).field(man.is(false)).field(age.is(21)).field(mail.is("avictorya@gmail.com")))//
		.row(new Row().field(name.is("Misha")).field(man.is(true)).field(age.is(21)).field(mail.is("mike@mail.ru")))//
		.row(new Row().field(name.is("Glasha")).field(man.is(false)).field(age.is(20)).field(mail.is("glasha@gmail.com")))//
		;
	/* Toggle women = man.is().not();
	 //age.is().moreOrEquals(20);
	 View womenonly = addrBook.select(women).afterRefresh(new Task() {

	 @Override public void doTask() {
	 //System.out.println("afterRefresh");
	 }
	 }); */
	View dump = addrBook;//.sort(age.ascending());
	//womenonly.select(mail.is().like("gmail.com"));//.where(age.is().moreOrEquals(20));
	for (int r = 0; r < dump.rows.size(); r++) {
	    dump.move(r);
	    System.out.print(""
		    + ": name[" + name.is().value() + "]"
		    + ": age[" + age.is().value() + "]"
		    + ": email[" + mail.is().value() + "]"
		    + ": descr[" + descr.value() + "]");
	    System.out.println();
	}
	System.out.println("==========");
	dump = addrBook.sort(man.ascending());
	//.select(mail.is().like("gmail.com"));//.where(age.is().moreOrEquals(20));
	for (int r = 0; r < dump.rows.size(); r++) {
	    dump.move(r);
	    System.out.print(""
		    + ": name[" + name.is().value() + "]"
		    + ": age[" + age.is().value() + "]"
		    + ": email[" + mail.is().value() + "]"
		    + ": descr[" + descr.value() + "]");
	    System.out.println();
	}
	/* System.out.println("---");
	 addrBook.row(new Row().field(nm.is("Ira")).field(man.is(false)).field(age.is(21)).field(mail.is("irina@mail.ru")));
	 System.out.println("---");
	 for (int r = 0; r < dump.rows.size(); r++) {
	 dump.move(r);
	 System.out.print("" + dump.rows.get(r).nn
	 + ": name[" + nm.is().value() + "]"
	 + ": age[" + age.is().value() + "]"
	 + ": email[" + mail.is().value() + "]");
	 System.out.println();
	 } */
	/* Numeric idx = new Numeric().value(2);
	 Note curMail = mail.at(dump.row(idx));
	 System.out.println(curMail.value());
	 idx.value(1);
	 System.out.println(curMail.value());
	 idx.value(2);
	 System.out.println(curMail.value());
	 idx.value(-5);
	 System.out.println(curMail.value()); */
    }
}
