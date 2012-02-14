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
    private Task adjust;
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
				rows.get(nn).select();
			    }
			}
		    }
		}
	    }
	});
    }
    public void probe(int nn) {
	if (nn >= 0 && nn < rows.size()) {
	    rows.get(nn).probe();
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
	int ord = -1;
	for (int i = 0; i < rows.size(); i++) {
	    if (rows.get(i).order() == nn) {
		ord = i;//rows.get(i).order();
		//System.out.println(i+"/"+ord);
		break;
	    }
	}
	if (ord > -1) {
	    rows.remove(ord);
	}
	cashe.add(this);
	for (int i = 0; i < _binded.size(); i++) {
	    if (!cashe.contains(_binded.get(i))) {
		_binded.get(i).dropForEachBindedItem(nn, cashe);
	    }
	}
	cashe.remove(this);
	if (this.adjust != null) {
	    adjust.start();
	}
	if (this.afterChange != null) {
	    afterChange.start();
	}
    }
    /**
     *
     * @param nn
     */
    public void drop() {
	int nn = select().value().intValue();
	dropForEachBindedItem(rows.get(nn).order(), new Vector<Bundle>());
    }
    private void clearForEachBindedItem(Vector<Bundle> cashe) {
	rows.removeAllElements();
	cashe.add(this);
	for (int i = 0; i < _binded.size(); i++) {
	    if (!cashe.contains(_binded.get(i))) {
		_binded.get(i).clearForEachBindedItem(cashe);
	    }
	}
	cashe.remove(this);
	if (this.afterChange != null) {
	    afterChange.start();
	}
    }
    public void clear() {
	clearForEachBindedItem(new Vector<Bundle>());
    }
    private void seriesForEachBindedItem(Series row, Vector<Bundle> cashe) {
	Series r = new Series();
	r.columns = row.columns;
	r.order(rows.size());
	rows.add(r);
	cashe.add(this);
	for (int i = 0; i < _binded.size(); i++) {
	    if (!cashe.contains(_binded.get(i))) {
		_binded.get(i).seriesForEachBindedItem(row, cashe);
	    }
	}
	cashe.remove(this);
	if (this.adjust != null) {
	    adjust.start();
	}
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
    public Bundle where(final Toggle t) {
	final Bundle wh = new Bundle();
	wh.adjust = new Task() {
	    @Override
	    public void doTask() {
		for (int i = 0; i < wh.size(); i++) {
		    wh.probe(i);
		    //System.out.println(i+"/"+(!t.value())+"/"+wh.size());
		    if (!t.value()) {
			wh.rows.remove(i);
			i--;
		    }		    
		}
	    }
	};
	wh.bind(this);
	return wh;
    }
    public Bundle sort(final Comparator comparator) {
	final Bundle srt = new Bundle();
	srt.adjust = new Task() {
	    @Override
	    public void doTask() {
		Collections.sort(srt.rows, comparator);
	    }
	};
	srt.bind(this);
	return srt;
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
	if (adjust != null) {
	    adjust.start();
	}
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
		.series(new Series().field(fio.is("Vasya")).field(man.is(true)).field(age.is(19)).field(mail.is("vpupkin@mail.ru")))//
		//.series(new Series().field(fio.value("Petya")).field(man.value(true)).field(age.value(22)).field(mail.value("petrpetrov@gmail.com")))//
		.series(new Series().field(fio.is("Sasha")).field(man.is(true)).field(age.is(20)).field(mail.is("alxndr@aol.com")))//
		.series(new Series().field(fio.is("Masha")).field(man.is(false)).field(age.is(21)).field(mail.is("masha@mail.ru")))//
		//.series(new Series().field(fio.value("Kolya")).field(man.value(true)).field(age.value(21)).field(mail.value("nikolay@gmail.com")))//
		//.series(new Series().field(fio.value("Vanya")).field(man.value(true)).field(age.value(22)).field(mail.value("ivan@mail.ru")))//
		//.series(new Series().field(fio.value("Olya")).field(man.value(false)).field(age.value(19)).field(mail.value("olga@aol.com")))//
		//.series(new Series().field(fio.value("Vika")).field(man.value(false)).field(age.value(21)).field(mail.value("avictorya@gmail.com")))//
		//.series(new Series().field(fio.value("Misha")).field(man.value(true)).field(age.value(21)).field(mail.value("mike@mail.ru")))//
		.series(new Series().field(fio.is("Glasha")).field(man.is(false)).field(age.is(20)).field(mail.is("glasha@gmail.com")))//
		;
	for (int i = 0; i < addressBook.size(); i++) {
	    addressBook.select(i);
	    System.out.println(i + ": " + fio.current().value() + ": " + age.current().value() + ": " + mail.current().value() + ": " + man.current().value());
	    //addressBook.probe(i);
	    //System.out.println(fio.at(i) + " - " + fio.is().like("ash").value());
	    //System.out.println(mail.at(i) + " - " + mail.is().like("gmail.com").value());
	    //addressBook.probe(i);
	    //System.out.println(mail.is().value() + " - " + mail.is().like("gmail.com").value());
	}
	System.out.println("--");
	Bundle aux = addressBook.where(man.is().equal(false))//.sort(age.ascending())
		.afterChange(new Task() {
	    @Override
	    public void doTask() {
		//System.out.println("change byName");
	    }
	});
	for (int i = 0; i < aux.size(); i++) {
	    aux.select(i);
	    System.out.println(i + ": " + fio.current().value() + ": " + age.current().value() + ": " + mail.current().value() + ": " + man.current().value());
	}

	/*addressBook.select(1);
	System.out.println("drop " + fio.current().value());
	addressBook.drop();
	for (int i = 0; i < addressBook.size(); i++) {
	addressBook.select(i);
	System.out.println(i + ": " + fio.current().value() + ": " + age.current().value() + ": " + mail.current().value() + ": " + man.current().value());
	}
	System.out.println("--");
	for (int i = 0; i < byName.size(); i++) {
	byName.select(i);
	System.out.println(i + ": " + fio.current().value() + ": " + age.current().value() + ": " + mail.current().value() + ": " + man.current().value());
	}*/
	/*System.out.println("add " );
	addressBook.series(new Series().field(fio.is("Kolya")).field(man.is(true)).field(age.is(21)).field(mail.is("nikolay@gmail.com")));
	for (int i = 0; i < addressBook.size(); i++) {
	addressBook.select(i);
	System.out.println(i + ": " + fio.current().value() + ": " + age.current().value() + ": " + mail.current().value() + ": " + man.current().value());
	}
	System.out.println("--");
	for (int i = 0; i < aux.size(); i++) {
	aux.select(i);
	System.out.println(i + ": " + fio.current().value() + ": " + age.current().value() + ": " + mail.current().value() + ": " + man.current().value());
	}*/

	/*
	 * Bundle gmail=addressBook.where(mail.is().like("gmail.com")); for (int
	 * i = 0; i < gmail.size(); i++) { //addressBook.select(i);
	 * //System.out.println(i + ": " + fio.value().value() + ": " +
	 * age.value().value() + ": " + mail.value().value() + ": " +
	 * man.value().value()); //addressBook.probe(i);
	 * //System.out.println(fio.at(i) + " - " +
	 * fio.is().like("ash").value()); //System.out.println(mail.at(i) + " -
	 * " + mail.is().like("gmail.com").value()); gmail.probe(i);
	 * System.out.println(mail.is().value() + " - " +
	 * mail.is().like("gmail.com").value()); }
	 */
    }
}
