package tee.binding;

public class Fork<Kind> extends It<Kind> {
    It<Kind> then = new It<Kind>().afterChange(new Task() {
        @Override public void job() {
            decide();
        }
    });
    It<Kind> otherwise = new It<Kind>().afterChange(new Task() {
        @Override public void job() {
            decide();
        }
    });
    Toggle condition = new Toggle().afterChange(new Task() {
        @Override public void job() {
            decide();
        }
    }).value(true);
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
    public Fork condition(Toggle it) {
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
        Toggle c = new Toggle().value(true);
        Fork<String> f = new Fork<String>().condition(c).then(yes).otherwise(no);

        c.value(true);
        System.out.println(f.value());
        c.value(false);
        System.out.println(f.value());
    }
}
