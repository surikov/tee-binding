package tee.binding;

public class Fork<Kind> extends It<Kind> {

    It<Kind> then = new It<Kind>() {

	@Override
	public void afterChange() {
	    decide();
	}
    };
    It<Kind> otherwise = new It<Kind>() {

	@Override
	public void afterChange() {
	    decide();
	}
    };
    Bi condition = new Bi() {

	@Override
	public void afterChange() {
	    decide();
	}
    }.value(true);

    private void decide() {
	if (condition == null) {
	    return;
	}
	if (condition.value()) {
	    value(then.value());
	} else {
	    value(otherwise.value());
	}
    }

    public Fork condition(Bi it) {
	condition.tie(it);
	return this;
    }

    public Fork then(It<Kind> it) {
	then.tie(it);
	return this;
    }

    public Fork otherwise(It<Kind> it) {
	otherwise.tie(it);
	return this;
    }

    public static void main(String a[]) {
	It<String> yes = new It<String>().value("yes");
	It<String> no = new It<String>().value("no");
	Bi c = new Bi().value(true);
	Fork<String> f = new Fork<String>().condition(c).then(yes).otherwise(no);
	
	c.value(true);
	System.out.println(f.value());
	c.value(false);
	System.out.println(f.value());
    }
}
