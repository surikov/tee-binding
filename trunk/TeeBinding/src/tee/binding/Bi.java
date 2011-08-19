package tee.binding;

public class Bi extends It<Boolean> {
    /*
    public TyBoolean() {
    super(false);
    }
    
    public TyBoolean(Boolean i) {
    super(i);
    }
    
    public TyBoolean(It<Boolean> i) {
    super(i);
    }
     */

    public Bi equals() {
	return this;
    }

    @Override
    public Bi value(Boolean newValue) {
	super.value(newValue);
	return this;
    }

    public static void main(String a[]) {
	Bi b = new Bi().value(true);
	System.out.println(b.value());
    }
}
