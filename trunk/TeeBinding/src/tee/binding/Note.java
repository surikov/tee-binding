package tee.binding;

public class Note extends It<String> {
    @Override public Note afterChange(Task newValue) {
	super.afterChange(newValue);
	return this;
    }
    @Override public Note value(String newValue) {
	super.value(newValue);
	return this;
    }
    public Note bind(Note it) {
	super.bind(it);
	return this;
    }
    public Note bind(It<String> it) {
	super.bind(it);
	return this;
    }
    public Note append(String value) {
	final String fvalue = value;
	Note s = new Note().bind(
		new Calculation<String>(this, new Note().value(value() + fvalue)) {
		    @Override public String calculateFirst() {
			if (second() == null) {
			    return "";
			} else {
			    return second().value();
			}
		    }
		    @Override public String calculateSecond() {
			if (first() == null) {
			    return "";
			} else {
			    return first().value() + fvalue;
			}
		    }
		}.second());
	return s;
    }
    public Note append(It<String> value) {
	Note n = new Note().bind(value);
	return append(n);
    }
    public Note append(Note value) {
	final Note fvalue = value;
	final Note me = this;
	final Note retvalue = new Note().value(value() + fvalue.value());
	new Note().bind(fvalue).afterChange(new Task() {
	    @Override public void job() {
		retvalue.value(me.value() + fvalue.value());
	    }
	});
	return retvalue;
    }
    public static void main(String a[]) {
	System.out.println("\nCharacters\n");
	Note item = new Note();
	Note s = new Note().bind(new Note().value("A ").append(item).append(" apple."));
	System.out.println(s.value());
	System.out.println("/let item = red");
	item.value("red");
	System.out.println(s.value());
	System.out.println("/let item = green");
	item.value("green");
	System.out.println(s.value());
	System.out.println("/let item = yellow");
	item.value("yellow");
	System.out.println(s.value());
    }
}
