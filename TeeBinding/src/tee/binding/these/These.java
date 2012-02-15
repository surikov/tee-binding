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
    private It<Kind> _value;
    private Vector<It<Kind>> values;
    private Vector<Task> watchers;
    private Numeric select;
    private It<Kind> _is;
    private int oldSel = -1;
    private Task startWatchers;
    /**
     *
     */
    public These() {
	_value = new It<Kind>();
	_is = new It<Kind>();
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
		    _value.unbind(values.get(oldSel));
		}
		if (select != null) {
		    if (select.value() != null) {
			int nn = select.value().intValue();
			if (nn >= 0 && nn < values.size()) {
			    _value.bind(values.get(nn));
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
    public It<Kind> current() {
	return _value;
    }
    public It<Kind> is() {
	return _is;
    }
    /**
     *
     * @param nn
     * @return
     */
    private Kind at(int nn) {
	if (nn < 0 || nn >= values.size()) {
	    return null;
	}
	else {
	    return values.get(nn).value();
	}
    }
    /**
     *
     * @param nn
     * @param val
     * @return
     */
    /*public These<Kind> at(int nn, Kind val) {
	if (nn >= 0 && nn < values.size()) {
	    values.get(nn).value(val);
	}
	return this;
    }*/
    /**
     *
     * @param it
     * @return
     */
    public These<Kind> is(It<Kind> it) {
	It<Kind> v = new It<Kind>().bind(it).afterChange(startWatchers);
	values.add(v);
        probe(0);
	return this;
    }
    /**
     *
     * @param it
     * @return
     */
    public These<Kind> is(Kind it) {
	It<Kind> v = new It<Kind>().value(it).afterChange(startWatchers);
	values.add(v);
        probe(0);
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
    public void clear(){
        this.values.removeAllElements();
        startWatchers.start();
    }
    public void probe(int nn) {
	
	if (nn >= 0 && nn < values.size()) {
	    _is.value(at(nn));
	    //System.out.println(_is.value());
	}

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
    /*public void drop(int nn) {
	if (nn >= 0 && nn < values.size()) {
	    It<Kind> it = values.remove(nn);
	    it.unbind();
	    it.afterChange(null);
	    if (nn == select.value().intValue()) {
		if (nn < values.size()) {
		    _value.bind(values.get(nn));
		}
	    }
	    startWatchers.start();
	}
    }*/
    /**
     *
     * @return
     */
    public int size() {
	return this.values.size();
    }
}
