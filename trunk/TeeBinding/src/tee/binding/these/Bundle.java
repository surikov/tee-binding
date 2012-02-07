package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

public class Bundle {

    private Vector<Series> rows;
    private Numeric select;
    private Task afterChange;
    private Vector<Bundle> _binded;

    public Bundle() {
	rows = new Vector<Series>();
	_binded = new Vector< Bundle>();
	select = new Numeric().value(-1).afterChange(new Task() {

	    @Override public void doTask() {
		if (rows != null) {
		    if (select != null) {
			if (select.value() != null) {
			    int nn = select.value().intValue();
			    if (nn >= 0 && nn < rows.size()) {
				rows.get(nn).select(nn);
			    }
			}
		    }
		}
	    }
	});
    }

    public Numeric select() {
	return select;
    }

    /**

    @param nn
    @return
    */
    public Bundle select(int nn) {
	select.value(nn);
	return this;
    }

    /**

    @param nn
    @return
    */
    public Bundle select(Numeric nn) {
	select.bind(nn);
	return this;
    }

    private void dropForEachBindedItem(int nn, Vector<Bundle> cashe) {

	rows.remove(nn);
	cashe.add(this);
	for (int i = 0; i < _binded.size(); i++) {
	    if (!cashe.contains(_binded.get(i))) {
		_binded.get(i).dropForEachBindedItem(nn, cashe);
	    }
	}
	cashe.remove(this);
	if (this.afterChange != null) {
	    afterChange.start();
	}
    }

    public void drop(int nn) {
	/* if (rows != null) {
	 rows.get(nn).drop(nn);
	 rows.remove(nn);
	 } */
	rows.get(nn).drop(nn);
	dropForEachBindedItem(nn, new Vector<Bundle>());

    }
private void seriesForEachBindedItem(Series ss, Vector<Bundle> cashe) {
	rows.add(ss);
	cashe.add(this);
	for (int i = 0; i < _binded.size(); i++) {
	    if (!cashe.contains(_binded.get(i))) {
		_binded.get(i).seriesForEachBindedItem(ss, cashe);
	    }
	}
	cashe.remove(this);
	if (this.afterChange != null) {
	    afterChange.start();
	}
    }
    public Bundle series(Series row) {
	/*if (rows == null) {
	    rows = new Vector<Series>();
	}
	rows.add(row);
*/
	/* if (this.afterChange != null) {
	 afterChange.start();
	 } */
	seriesForEachBindedItem(row,new Vector<Bundle>());
	return this;
    }

    public int size() {
	if (rows == null) {
	    return 0;
	} else {
	    return rows.size();
	}
    }

    public Bundle bind(Bundle to) {
	if (to == null) {
	    return this;
	}
	if (!this._binded.contains(to)) {
	    this._binded.add(to);
	}
	if (!to._binded.contains(this)) {
	    to._binded.add(this);
	}
	//requery();
	//this.rows = to.rows;
	this.rows = new Vector<Series>();
	this.rows.addAll(to.rows);
	this.select().bind(to.select());
	if (this.afterChange != null) {
	    afterChange.start();
	}
	return this;
    }

    //private void requery() {
    //}
    public void unbind(Bundle to) {
	if (to == null) {
	    return;
	}
	if (!this._binded.contains(to)) {
	    this._binded.remove(to);
	    //this.rows = null;
	}
	to._binded.remove(this);
	this.select().unbind(to.select());
    }

    public void unbind() {
	for (int i = 0; i < _binded.size(); i++) {
	    _binded.get(i).unbind(this);
	}
    }

    public Bundle afterChange(Task it) {
	this.afterChange = it;
	return this;
    }

    public static void main(String[] a) {
	These<String> fio = new These<String>();
	Numerics age = new Numerics();
	Notes mail = new Notes();
	Toggles man = new Toggles();
	Bundle sh = new Bundle()//
		.series(new Series().field(fio.is("Vasya")).field(man.is(true)).field(age.is(19)).field(mail.is("vpupkin@mail.ru")))//
		.series(new Series().field(fio.is("Petya")).field(man.is(true)).field(age.is(22)).field(mail.is("petrpetrov@gmail.com")))//
		.series(new Series().field(fio.is("Sasha")).field(man.is(true)).field(age.is(20)).field(mail.is("alxndr@aol.com")))//
		.series(new Series().field(fio.is("Masha")).field(man.is(false)).field(age.is(21)).field(mail.is("masha@mail.ru")))//
		.series(new Series().field(fio.is("Kolya")).field(man.is(true)).field(age.is(21)).field(mail.is("nikolay@gmail.com")))//
		.series(new Series().field(fio.is("Vanya")).field(man.is(true)).field(age.is(22)).field(mail.is("ivan@mail.ru")))//
		.series(new Series().field(fio.is("Olya")).field(man.is(false)).field(age.is(19)).field(mail.is("olga@aol.com")))//
		.series(new Series().field(fio.is("Vika")).field(man.is(false)).field(age.is(21)).field(mail.is("avictorya@gmail.com")))//
		.series(new Series().field(fio.is("Misha")).field(man.is(true)).field(age.is(21)).field(mail.is("mike@mail.ru")))//
		.series(new Series().field(fio.is("Glasha")).field(man.is(false)).field(age.is(20)).field(mail.is("glasha@gmail.com")))//
		;
	Bundle scnd = new Bundle().bind(sh).afterChange(new Task() {

	    @Override public void doTask() {
		System.out.println("----------drop");
	    }
	});
	for (int i = 0; i < sh.size(); i++) {
	    sh.select(i);
	    System.out.println(i + ": " + fio.is().value() + ": " + age.is().value() + ": " + mail.is().value() + ": " + man.is().value());
	}
	System.out.println("--");
	sh.drop(6);
	for (int i = 0; i < sh.size(); i++) {
	    sh.select(i);
	    System.out.println(i + ": " + fio.is().value() + ": " + age.is().value() + ": " + mail.is().value() + ": " + man.is().value());
	}
	System.out.println("--");
	sh.series(new Series().field(fio.is("Glasha2")).field(man.is(false)).field(age.is(20)).field(mail.is("glasha@gmail.com2")));
	for (int i = 0; i < sh.size(); i++) {
	    sh.select(i);
	    System.out.println(i + ": " + fio.is().value() + ": " + age.is().value() + ": " + mail.is().value() + ": " + man.is().value());
	}
    }
}
