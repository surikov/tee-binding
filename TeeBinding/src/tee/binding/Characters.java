package tee.binding;

public class Characters extends It<String> {
    /*
    public TyString() {
    }
    
    public TyString(String i) {
    super(i);
    }
    
    public TyString(It<String> i) {
    super(i);
    }
     */

    public Characters value(String newValue) {
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

		    @Override
		    public String calculateFirst() {
			return second().value();
		    }

		    @Override
		    public String calculateSecond() {
			return first().value() + fvalue;
		    }
		}.second());
	return s;
    }

    public Characters append(Characters value) {
	final Characters fvalue = value;
	final Characters me = this;
	final Characters retvalue = new Characters().value(value() + fvalue.value());
	new It<String>(fvalue) {

	    @Override
	    public void afterChange() {
		retvalue.value(me.value() + fvalue.value());
	    }
	};
	return retvalue;
    }

    public static void main(String a[]) {
	Characters item = new Characters();
	Characters s = new Characters().tie(new Characters().value("now item is ").append(item).append("!!!"));
	System.out.println(s.value());
	System.out.println("let item = A");
	item.value("A");
	System.out.println(s.value());
	System.out.println("let item = B");
	item.value("B");
	System.out.println(s.value());
	System.out.println("let item = C");
	item.value("C");
	System.out.println(s.value());
    }
}
