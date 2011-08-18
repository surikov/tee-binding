package tee.binding;

public class TyFork<Kind> extends TyValue<Kind> {

    TyValue<Kind> then = new TyValue<Kind>() {

	@Override
	public void afterChange() {
	    decide();
	}
    };
    TyValue<Kind> otherwise = new TyValue<Kind>() {

	@Override
	public void afterChange() {
	    decide();
	}
    };
    TyBoolean condition = new TyBoolean(true) {

	@Override
	public void afterChange() {
	    decide();
	}
    };

    private void decide() {
	if (condition == null) {
	    return;
	}
	if (condition.get()) {
	    set(then.get());
	} else {
	    set(otherwise.get());
	}
    }

    public TyFork condition(TyBoolean it) {
	condition.tie(it);
	return this;
    }

    public TyFork then(TyValue<Kind> it) {
	then.tie(it);
	return this;
    }

    public TyFork otherwise(TyValue<Kind> it) {
	otherwise.tie(it);
	return this;
    }

    public static void main(String a[]) {
	TyValue<String> yes = new TyValue<String>("yes");
	TyValue<String> no = new TyValue<String>("no");
	TyBoolean c = new TyBoolean(true);
	TyFork<String> f = new TyFork<String>().condition(c).then(yes).otherwise(no);
	
	c.set(true);
	System.out.println(f.get());
	c.set(false);
	System.out.println(f.get());
    }
}
