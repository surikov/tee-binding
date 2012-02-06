package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

public class Bag {

    private Vector<Series> rows;
    private Numeric select;
    private Task afterInsert;
    private Task afterDrop;
    private Vector<Bag> _binded;

    public Bag() {
	rows = new Vector<Series>();
	_binded = new Vector< Bag>();
	select = new Numeric().value(-1).afterChange(new Task() {

	    @Override public void doTask() {
		if (select != null) {
		    if (select.value() != null) {
			int nn = select.value().intValue();
			if (nn >= 0 && nn < rows.size()) {
			    rows.get(nn).select(nn);
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
    public Bag select(int nn) {
	select.value(nn);
	return this;
    }

    /**

    @param nn
    @return
    */
    public Bag select(Numeric nn) {
	select.bind(nn);
	return this;
    }

    private void dropForEachBindedItem(int nn, Vector<Bag> cashe) {

	cashe.add(this);
	for (int i = 0; i < _binded.size(); i++) {
	    if (!cashe.contains(_binded.get(i))) {
		_binded.get(i).dropForEachBindedItem(nn, cashe);
	    }
	}
	cashe.remove(this);
	if (this.afterDrop != null) {
	    afterDrop.start();
	}
    }

    public void drop(int nn) {
	rows.get(nn).drop(nn);
	rows.remove(nn);
	dropForEachBindedItem(nn, new Vector<Bag>());

    }

    public Bag series(Series row) {
	rows.add(row);
	if (this.afterInsert != null) {
	    afterInsert.start();
	}
	return this;
    }

    public int count() {
	return rows.size();
    }

    public Bag bind(Bag to) {
	if (to == null) {
	    return this;
	}
	if (!this._binded.contains(to)) {
	    this._binded.add(to);
	}
	if (!to._binded.contains(this)) {
	    to._binded.add(this);
	}
	requery();
	return this;
    }

    private void requery() {
    }

    public void unbind(Bag to) {
	if (to == null) {
	    return;
	}
	this._binded.remove(to);
	to._binded.remove(this);
    }

    public void unbind() {
	for (int i = 0; i < _binded.size(); i++) {
	    _binded.get(i).unbind(this);
	}
    }

    public Bag afterDrop(Task it) {
	this.afterDrop = it;
	return this;
    }

    public static void main(String[] a) {
	These<String> fio = new These<String>();
	Numerics age = new Numerics();
	Notes mail = new Notes();
	Toggles man = new Toggles();
	Bag sh = new Bag()//
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
	Bag scnd = new Bag().bind(sh).afterDrop(new Task() {

	    @Override public void doTask() {
		System.out.println("----------drop");
	    }
	});
	for (int i = 0; i < sh.count(); i++) {
	    sh.select(i);
	    System.out.println(i + ": " + fio.is().value() + ": " + age.is().value() + ": " + mail.is().value() + ": " + man.is().value());
	}
	System.out.println("--");
	sh.drop(6);
	for (int i = 0; i < sh.count(); i++) {
	    sh.select(i);
	    System.out.println(i + ": " + fio.is().value() + ": " + age.is().value() + ": " + mail.is().value() + ": " + man.is().value());
	}
	System.out.println("--");
	sh.series(new Series().field(fio.is("Glasha2")).field(man.is(false)).field(age.is(20)).field(mail.is("glasha@gmail.com2")));
	for (int i = 0; i < sh.count(); i++) {
	    sh.select(i);
	    System.out.println(i + ": " + fio.is().value() + ": " + age.is().value() + ": " + mail.is().value() + ": " + man.is().value());
	}
    }
}
