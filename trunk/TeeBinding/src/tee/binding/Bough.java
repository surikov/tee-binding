package tee.binding;

import tee.binding.it.Numeric;
import tee.binding.it.Note;

/**
 * 
 * @author User
 */
public class Bough {
    private String _name;
    private Note _note;
    private Numeric _numeric;
    private String _raw;
    //private Attribute<Bough> _children = new Attribute<Bough>();
    /**
     * 
     * @param it
     * @return
     */
    public Bough name(String it) {
	_name = it;
	return this;
    }
    /**
     * 
     * @return
     */
    public String name() {
	return _name;
    }
    /**
     * 
     * @param it
     * @return
     */
    public Bough raw(String it) {
	_raw = it;
	return this;
    }
    /**
     * 
     * @return
     */
    public String raw() {
	return _raw;
    }
    /**
     * 
     */
    public void gather() {
        if (_note != null) {
            _raw = _note.value();
            return;
        }
        if (_numeric != null) {
            _raw = "" + _numeric.value();
            return;
        }
    }
    /*public Bough child(String name) {
	for (int i = 0; i < _children.size().value(); i++) {
	    if (_children.item(i).name().equals(name)) {
		return _children.item(i);
	    }
	}
	Bough b = new Bough().name(name);
	_children.item(b);
	return b;
    }*/
    /**
     * 
     * @param defaultValue
     * @return
     */
    public Note item(String defaultValue) {
	if (_note == null) {
	    _note = new Note().value(defaultValue);
	    if (_raw != null) {
		_note.value(_raw);
	    }
	}
	return _note;
    }
    /**
     * 
     * @param defaultValue
     * @return
     */
    public Numeric item(double defaultValue) {
	if (_numeric == null) {
	    _numeric = new Numeric().value(defaultValue);
	    double n = 0;
	    if (_raw != null) {
		try {
		    //n = Double.parseDouble(_raw);
		    n = Numeric.string2double(_raw);
		} catch (Throwable t) {
		    t.printStackTrace();
		}
		_numeric.value(n);
	    }
	}
	return _numeric;
    }
}
