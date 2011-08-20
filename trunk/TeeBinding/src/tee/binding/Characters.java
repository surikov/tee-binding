package tee.binding;

public class Characters extends It<String> {
    @Override public Characters afterChange(Task newValue) {
	super.afterChange(newValue);
	return this;
    }
    @Override public Characters value(String newValue) {
	super.value(newValue);
	return this;
    }
    public Characters tie(Characters it) {
	super.tie(it);
	return this;
    }
    public Characters tie(It<String> it) {
	super.tie(it);
	return this;
    }
    public Characters append(String value) {
	final String fvalue = value;
	Characters s = new Characters().tie(
		new Calculation<String>(this, new Characters().value(value() + fvalue)) {
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
    public Characters append(Characters value) {
	final Characters fvalue = value;
	final Characters me = this;
	final Characters retvalue = new Characters().value(value() + fvalue.value());
	new Characters().tie(fvalue).afterChange(new Task() {
	    @Override
	    public void job() {
		retvalue.value(me.value() + fvalue.value());
	    }
	});
	return retvalue;
    }
    public static void main(String a[]) {
	System.out.println("\nCharacters\n");
	Characters item = new Characters();
	Characters s = new Characters().tie(new Characters().value("A ").append(item).append(" apple."));
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
