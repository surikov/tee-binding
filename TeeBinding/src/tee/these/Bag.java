package tee.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

public class Bag {

    private Vector<Series> rows;
    private Numeric select;
    private Task afterInsert;
    private Task afterDrop;

    public Bag() {
	rows = new Vector<Series>();
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

    public void drop(int nn) {
	rows.get(nn).drop(nn);
	rows.remove(nn);
	if (this.afterDrop != null) {
	    afterDrop.start();
	}
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
	for (int i = 0; i < sh.count(); i++) {
	    sh.select(i);
	    System.out.println(i + ": " + fio.is().value() + ": " + age.is().value() + ": " + mail.is().value() + ": " + man.is().value());
	    //System.out.println(name.select(i).is().value());
	}
	System.out.println("--");
	sh.drop(6);
	for (int i = 0; i < sh.count(); i++) {
	    sh.select(i);
	    System.out.println(i + ": " + fio.is().value() + ": " + age.is().value() + ": " + mail.is().value() + ": " + man.is().value());
	    //System.out.println(name.select(i).is().value());
	}
    }
}
