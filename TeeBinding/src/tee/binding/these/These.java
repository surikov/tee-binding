package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.*;
import tee.binding.task.*;

/**

 @author User
 @param <Kind>
 */
public class These<Kind> {

    private It<Kind> current;
    private Vector<It<Kind>> values;
    //private Vector<It<Kind>> watchers;
    private Numeric select;
    private int oldSel = -1;
    private Task afterInsert;
    private Task afterDrop;
    private Task afterChange;
    //private These<Kind> me;

    public These<Kind> afterInsert(Task t) {
	afterInsert = t;
	return this;
    }

    public These<Kind> afterDrop(Task t) {
	afterDrop = t;
	return this;
    }
public These<Kind> afterChange(Task t) {
	afterChange = t;
	for(int i=0;i<values.size();i++)values.get(i).afterChange(t);
	return this;
    }
    /**

    */
    public These() {
	//me = this;
	current = new It<Kind>();
	values = new Vector<It<Kind>>();
	//watchers = new Vector<It<Kind>>();
	select = new Numeric().value(-1).afterChange(new Task() {

	    @Override public void doTask() {
		if (oldSel >= 0 && oldSel < values.size()) {
		    current.unbind(values.get(oldSel));
		}
		if (select != null) {
		    if (select.value() != null) {
			int nn = select.value().intValue();
			if (nn >= 0 && nn < values.size()) {
			    current.bind(values.get(nn));
			    oldSel = nn;
			    //System.out.println(nn+" / "+current.value());
			}
		    }
		}
	    }
	});
    }

    /**

    @return
    */
    public It<Kind> is() {
	return current;
    }

    public Kind at(int nn) {
	if (nn < 0 || nn >= values.size()) {
	    return null;
	} else {
	    return values.get(nn).value();
	}
    }

    /**

    @param it
    @return
    */
    public These<Kind> is(It<Kind> it) {
	It<Kind> v = new It<Kind>().bind(it).afterChange(afterChange);
	values.add(v);
	//doAfterInsert();
	if (this.afterInsert != null) {
	    afterInsert.start();
	}
	return this;
    }

    /**

    @param it
    @return
    */
    public These<Kind> is(Kind it) {
	It<Kind> v = new It<Kind>().value(it).afterChange(afterChange);
	values.add(v);
	//doAfterInsert();
	if (this.afterInsert != null) {
	    afterInsert.start();
	}
	return this;
    }

    /**

    @return
    */
    public Numeric select() {
	return select;
    }

    /**

    @param nn
    @return
    */
    public These<Kind> select(int nn) {
	select.value(nn);
	return this;
    }

    /**

    @param nn
    @return
    */
    public These<Kind> select(Numeric nn) {
	select.bind(nn);
	return this;
    }

    /**

    */
    public void drop(int nn) {
	//int nn = select.value().intValue();
	if (nn >= 0 && nn < values.size()) {
	    It<Kind> it = values.remove(nn);
	    it.unbind();
	    it.afterChange(null);
	    if (nn == select.value().intValue()) {
		//current.unbind(it);
		if (nn < values.size()) {
		    current.bind(values.get(nn));
		}
	    }
	    //doAfterDrop();
	    if (this.afterDrop != null) {
		afterDrop.start();
	    }
	}
    }
    /*
    private void doAfterInsert() {
	if (this.afterInsert != null) {
	    afterInsert.start();
	}
    }

    private void doAfterDrop() {
	if (this.afterDrop != null) {
	    afterDrop.start();
	}
    }
*/

    public int size() {
	return this.values.size();
    }

    /**

    @param a
    */
    public static void main(String[] a) {
	Note a1 = new Note().value("first");
	Note a2 = new Note().value("second");
	Note a3 = new Note().value("third");
	These<String> s = new These<String>().is(a1).is(a2).is(a3).select(0).afterChange(new Task(){

	    @Override
	    public void doTask() {
		System.out.println("!!!");
	    }
	});
	System.out.println("--");
	System.out.println(s.is().value());
	//s.drop(0);
	//System.out.println(s.is().value());
	//s.drop(0);
	//System.out.println(s.is().value());
	//s.drop(0);
	//System.out.println(s.is().value());
	s.is().value("111");
	System.out.println(s.is().value());
	a1.value("222");
	System.out.println(s.is().value());
    }
}
