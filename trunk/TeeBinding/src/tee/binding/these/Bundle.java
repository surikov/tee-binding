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

    public void drop(int nn) {
        /*
         * if (rows != null) { rows.get(nn).drop(nn); rows.remove(nn); }
         */
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
        seriesForEachBindedItem(row, new Vector<Bundle>());
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
        this.rows = new Vector<Series>();
        this.rows.addAll(to.rows);
        this.select().bind(to.select());
        if (this.afterChange != null) {
            afterChange.start();
        }
        return this;
    }

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
        Bundle scnd = new Bundle().bind(sh).afterChange(new Task() {

            @Override
            public void doTask() {
                System.out.println("----------drop");
            }
        });
        for (int i = 0; i < sh.size(); i++) {
            sh.select(i);
            System.out.println(i + ": " + fio.value().value() + ": " + age.value().value() + ": " + mail.value().value() + ": " + man.value().value());
        }
        System.out.println("--");
        sh.drop(6);
        for (int i = 0; i < sh.size(); i++) {
            sh.select(i);
            System.out.println(i + ": " + fio.value().value() + ": " + age.value().value() + ": " + mail.value().value() + ": " + man.value().value());
        }
        System.out.println("--");
        sh.series(new Series().field(fio.value("Glasha2")).field(man.value(false)).field(age.value(20)).field(mail.value("glasha@gmail.com2")));
        for (int i = 0; i < sh.size(); i++) {
            sh.select(i);
            System.out.println(i + ": " + fio.value().value() + ": " + age.value().value() + ": " + mail.value().value() + ": " + man.value().value());
        }
    }
}
