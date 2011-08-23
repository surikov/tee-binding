package tee.binding;

public class Bough {
    private String _name;
    private Note _note;
    private Numeric _Numeric;
    private String _raw;
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
    public Note asNote(String defaultValue) {
	if (_note == null) {
	    _note = new Note().value(defaultValue);
	    if (_raw != null) {
		_note.value(_raw);
	    }
	}
	return _note;
    }
}
