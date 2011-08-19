package tee.binding;

public class Toggle extends It<Boolean> {
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
    @Override public Toggle afterChange(Task newValue) {
	super.afterChange(newValue);
	return this;
    }
    public Toggle equals() {
	return this;
    }
    @Override
    public Toggle value(Boolean newValue) {
	super.value(newValue);
	return this;
    }
    public static void main(String a[]) {
	Toggle b = new Toggle().value(true);
	System.out.println(b.value());
    }
}
