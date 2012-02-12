package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

/**
 *
 * @author User
 */
public class Bundle {
    private Vector<Series> rows;
    private Numeric select;
    private Task afterChange;
    private Vector<Bundle> _binded;
    public Bundle() {
	rows = new Vector<Series>();
	_binded = new Vector< Bundle>();
	select = new Numeric().value(-1).afterChange(new Task() {
	    @Override
	    public void doTask() {
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
    public void probe(int nn) {
	if (nn >= 0 && nn < rows.size()) {
	    rows.get(nn).probe(nn);
	}
    }
    /**
     *
     * @return
     */
    public Numeric select() {
	return select;
    }
    /**
     *
     * @param nn
     * @return
     */
    public Bundle select(int nn) {
	select.value(nn);
	return this;
    }
    /**
     *
     * @param nn
     * @return
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
    /**
     *
     * @param nn
     */
    public void drop(int nn) {
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
    /**
     *
     * @param row
     * @return
     */
    public Bundle series(Series row) {
	seriesForEachBindedItem(row, new Vector<Bundle>());
	return this;
    }
    /**
     *
     * @return
     */
    public int size() {
	if (rows == null) {
	    return 0;
	}
	else {
	    return rows.size();
	}
    }
    public Bundle where(Toggle t) {
	return this;
    }
    /**
     *
     * @param to
     * @return
     */
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
	this.rows = new Vector<Series>();
	this.rows.addAll(to.rows);
	this.select().bind(to.select());
	if (this.afterChange != null) {
	    afterChange.start();
	}
	return this;
    }
    /**
     *
     * @param to
     */
    public void unbind(Bundle to) {
	if (to == null) {
	    return;
	}
	if (!this._binded.contains(to)) {
	    this._binded.remove(to);
	}
	to._binded.remove(this);
	this.select().unbind(to.select());
    }
    /**
     *
     */
    public void unbind() {
	for (int i = 0; i < _binded.size(); i++) {
	    _binded.get(i).unbind(this);
	}
    }
    /**
     *
     * @param it
     * @return
     */
    public Bundle afterChange(Task it) {
	this.afterChange = it;
	return this;
    }
    /**
     *
     * @param a
     */
    public static void main(String[] a) {
	Notes fio = new Notes();
	Numerics age = new Numerics();
	Notes mail = new Notes();
	Toggles man = new Toggles();
	Bundle addressBook = new Bundle()//
		.series(new Series().field(fio.value("Vasya")).field(man.value(true)).field(age.value(19)).field(mail.value("vpupkin@mail.ru")))//
		.series(new Series().field(fio.value("Petya")).field(man.value(true)).field(age.value(22)).field(mail.value("petrpetrov@gmail.com")))//
		.series(new Series().field(fio.value("Sasha")).field(man.value(true)).field(age.value(20)).field(mail.value("alxndr@aol.com")))//
		.series(new Series().field(fio.value("Masha")).field(man.value(false)).field(age.value(21)).field(mail.value("masha@mail.ru")))//
		.series(new Series().field(fio.value("Kolya")).field(man.value(true)).field(age.value(21)).field(mail.value("nikolay@gmail.com")))//
		.series(new Series().field(fio.value("Vanya")).field(man.value(true)).field(age.value(22)).field(mail.value("ivan@mail.ru")))//
		.series(new Series().field(fio.value("Olya")).field(man.value(false)).field(age.value(19)).field(mail.value("olga@aol.com")))//
		.series(new Series().field(fio.value("Vika")).field(man.value(false)).field(age.value(21)).field(mail.value("avictorya@gmail.com")))//
		.series(new Series().field(fio.value("Misha")).field(man.value(true)).field(age.value(21)).field(mail.value("mike@mail.ru")))//
		.series(new Series().field(fio.value("Glasha")).field(man.value(false)).field(age.value(20)).field(mail.value("glasha@gmail.com")))//
		;
	Bundle gmail=addressBook.where(mail.is().like("gmail.com"));
	for (int i = 0; i < gmail.size(); i++) {
	    //addressBook.select(i);
	    //System.out.println(i + ": " + fio.value().value() + ": " + age.value().value() + ": " + mail.value().value() + ": " + man.value().value());
	    //addressBook.probe(i);
	    //System.out.println(fio.at(i) + " - " + fio.is().like("ash").value());
	    //System.out.println(mail.at(i) + " - " + mail.is().like("gmail.com").value());
	    gmail.probe(i);
	    System.out.println(mail.at(i) + " - " + mail.is().like("gmail.com").value());
	}
    }
}
