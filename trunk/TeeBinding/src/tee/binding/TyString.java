
package tee.binding;

public class TyString  extends TyValue<String>{
    
    public TyString() {
    }
    public TyString(String i) {
	super(i);
    }
    public TyString(TyValue<String> i) {
	super(i);
    }
    public TyString plus(String value) {
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
    public TyString plus(TyValue<String> value) {
	final TyValue<String> fvalue = value;
	final TyCalculation<String> clc = new TyCalculation<String>(this, new TyValue<String>(get() + fvalue.get())) {

	    @Override
	    public String calculateFirst() {
		return second().get();
	    }
	    @Override
	    public String calculateSecond() {
		return first().get() + fvalue.get();
	    }
	};
	new TyValue<String>(fvalue) {

	    @Override
	    public void afterChange() {
		clc.second().set(clc.first().get() + fvalue.get());
	    }
	};
	return new TyString(clc.second());
    }
    public static void main(String a[]) {
	TyString a1 = new TyString("1111");
	TyString a2 = new TyString("2222");
	TyString a3 = new TyString("--");
	System.out.println(a1.get() + " / " + a2.get()+ " / " + a3.get());
	a1 = a2.plus(a3);
	System.out.println(a1.get() + " / " + a2.get()+ " / " + a3.get());
	a2.set("4");
	System.out.println(a1.get() + " / " + a2.get()+ " / " + a3.get());
	a3.set("!!!");
	System.out.println(a1.get() + " / " + a2.get()+ " / " + a3.get());
    }
}
