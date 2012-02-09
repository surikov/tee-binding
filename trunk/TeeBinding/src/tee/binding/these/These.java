package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.*;
import tee.binding.task.*;

/**
 *
 * @author User
 * @param <Kind>
 */
public class These<Kind> {

    private It<Kind> current;
    private Vector<It<Kind>> values;
    private Vector<Task> watchers;
    private Numeric select;
    private int oldSel = -1;
    private Task startWatchers;

    /**
     * 
     */
    public These() {
        current = new It<Kind>();
        values = new Vector<It<Kind>>();
        watchers = new Vector<Task>();
        startWatchers = new Task() {

            @Override
            public void doTask() {
                for (int i = 0; i < watchers.size(); i++) {
                    watchers.get(i).start();
                }
            }
        };
        select = new Numeric().value(-1).afterChange(new Task() {

            @Override
            public void doTask() {
                if (oldSel >= 0 && oldSel < values.size()) {
                    current.unbind(values.get(oldSel));
                }
                if (select != null) {
                    if (select.value() != null) {
                        int nn = select.value().intValue();
                        if (nn >= 0 && nn < values.size()) {
                            current.bind(values.get(nn));
                            oldSel = nn;
                        }
                    }
                }
            }
        });
    }

    /**
     * 
     * @param task
     * @return
     */
    public These<Kind> watch(Task task) {
        this.watchers.add(task);
        return this;
    }

    /**
     * 
     * @param task
     * @return
     */
    public These<Kind> unwatch(Task task) {
        this.watchers.remove(task);
        return this;
    }

    /**
     *
     * @return
     */
    public It<Kind> value() {
        return current;
    }

    /**
     * 
     * @param nn
     * @return
     */
    public Kind at(int nn) {
        if (nn < 0 || nn >= values.size()) {
            return null;
        } else {
            return values.get(nn).value();
        }
    }

    /**
     *
     * @param it
     * @return
     */
    public These<Kind> value(It<Kind> it) {
        It<Kind> v = new It<Kind>().bind(it).afterChange(startWatchers);
        values.add(v);
        return this;
    }

    /**
     *
     * @param it
     * @return
     */
    public These<Kind> value(Kind it) {
        It<Kind> v = new It<Kind>().value(it).afterChange(startWatchers);
        values.add(v);
        return this;
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
    public These<Kind> select(int nn) {
        select.value(nn);
        return this;
    }

    /**
     *
     * @param nn
     * @return
     */
    public These<Kind> select(Numeric nn) {
        select.bind(nn);
        return this;
    }

    /**
     *
     * @param nn 
     */
    public void drop(int nn) {
        if (nn >= 0 && nn < values.size()) {
            It<Kind> it = values.remove(nn);
            it.unbind();
            it.afterChange(null);
            if (nn == select.value().intValue()) {
                if (nn < values.size()) {
                    current.bind(values.get(nn));
                }
            }
            startWatchers.start();
        }
    }

    /**
     * 
     * @return
     */
    public int size() {
        return this.values.size();
    }
}
