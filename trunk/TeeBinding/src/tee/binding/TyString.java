package tee.binding;

public class TyString extends TyValue<String> {

    public TyString() {
    }

    public TyString(String i) {
	super(i);
    }

    public TyString(TyValue<String> i) {
	super(i);
    }

    public TyString append(String value) {
	final String fvalue = value;
	return new TyString(new TyCalculation<String>(this, new TyValue<String>(get() + fvalue)) {

	    @Override
	    public String calculateFirst() {
		return second().get();
	    }

	    @Override
	    public String calculateSecond() {
		return first().get() + fvalue;
	    }
	}.second());
    }

    public TyString append(TyString value) {
	final TyString fvalue = value;
	final TyString me = this;
	final TyString retvalue = new TyString(get() + fvalue.get());
	new TyValue<String>(fvalue) {

	    @Override
	    public void afterChange() {
		retvalue.set(me.get() + fvalue.get());
	    }
	};
	return retvalue;
    }

    public static void main(String a[]) {
	TyString item = new TyString();
	TyString s = new TyString(new TyString("now item is ").append(item).append("!!!"));
	System.out.println(s.get());
	System.out.println("let item = A");
	item.set("A");
	System.out.println(s.get());
	System.out.println("let item = B");
	item.set("B");
	System.out.println(s.get());
	System.out.println("let item = C");
	item.set("C");
	System.out.println(s.get());
    }
}
