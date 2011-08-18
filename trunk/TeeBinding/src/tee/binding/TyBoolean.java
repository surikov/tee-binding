package tee.binding;

public class TyBoolean extends TyValue<Boolean> {

    public TyBoolean() {
	super(false);
    }

    public TyBoolean(Boolean i) {
	super(i);
    }

    public TyBoolean(TyValue<Boolean> i) {
	super(i);
    }

    public TyBoolean equals() {
	return this;
    }

    public static void main(String a[]) {
	TyBoolean b = new TyBoolean();
	System.out.println(b.get());
    }
}
