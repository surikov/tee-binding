package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

/**
 *
 * @author User
 */
public class Notes extends These<String> {

    private Note valueNote;
    private Note isNote;

    /**
     *
     */
    public Notes() {
        super();
        valueNote = new Note().bind(super.current());
        isNote = new Note().bind(super.is());
    }

    @Override
    public Note is() {
        return isNote;
    }

    @Override
    public Note current() {
        return valueNote;
    }

    /**
     *
     * @param it
     * @return
     */
    public Notes current(Note it) {
        super.current(it);
        return this;
    }

    @Override
    public Notes select(int nn) {
        super.select(nn);
        return this;
    }

    public Comparator<Series> ascending() {
        return new Comparator<Series>() {

            @Override
            public int compare(Series o1, Series o2) {
                o1.probe();
                String s1 = is().value();
                o2.probe();
                String s2 = is().value();
                double n = s1.compareTo(s2);
                //System.out.println(n);
                //String s1 = values.get(o1.nn).value();
                //String s2 = values.get(o2.nn).value();
                //double n = s1.compareTo(s2);
                //return (int) (+n);
                return (int) (+n);
            }
        };
    }

    public Comparator<Series> descending() {
        return new Comparator<Series>() {

            @Override
            public int compare(Series o1, Series o2) {
                o1.probe();
                String s1 = is().value();
                o2.probe();
                String s2 = is().value();
                double n = s1.compareTo(s2);
                return (int) (-n);
            }
        };
    }
}
