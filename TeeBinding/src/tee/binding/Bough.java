package tee.binding;

public class Bough {
    private String _name;
    private Note _note;
    private Numeric _numeric;
    private String _raw;
    private Serie<Bough> _children = new Serie<Bough>();
    public Bough name(String it) {
	_name = it;
	return this;
    }
    public String name() {
	return _name;
    }
    public Bough raw(String it) {
	_raw = it;
	return this;
    }
    public String raw() {
	return _raw;
    }
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
    public Bough child(String name) {
	for (int i = 0; i < _children.size(); i++) {
	    if (_children.item(i).name().equals(name)) {
		return _children.item(i);
	    }
	}
	Bough b = new Bough().name(name);
	_children.append(b);
	return b;
    }
    public Note item(String defaultValue) {
	if (_note == null) {
	    _note = new Note().value(defaultValue);
	    if (_raw != null) {
		_note.value(_raw);
	    }
	}
	return _note;
    }
    public Numeric item(double defaultValue) {
	if (_numeric == null) {
	    _numeric = new Numeric().value(defaultValue);
	    double n = 0;
	    if (_raw != null) {
		try {
		    n = Double.parseDouble(_raw);
		} catch (Throwable t) {
		    t.printStackTrace();
		}
		_numeric.value(n);
	    }
	}
	return _numeric;
    }
}
