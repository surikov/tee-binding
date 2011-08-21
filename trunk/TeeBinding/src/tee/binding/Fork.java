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
        condition.bind(it);
        return this;
    }
    public Fork then(It<Kind> it) {
        then.bind(it);
        return this;
    }
    public Fork then(Kind it) {
        then.value(it);
        return this;
    }
    public Fork otherwise(It<Kind> it) {
        otherwise.bind(it);
        return this;
    }
    public Fork otherwise(Kind it) {
        otherwise.value(it);
        return this;
    }
    public static void main(String a[]) {
        System.out.println("\nFork\n");
        System.out.println("/n = -10");
        Numeric n = new Numeric().value(-10);
        Note r = new Note().bind(new Fork<String>()
                .condition(new Toggle().less(n, -5))
                .then("Frost")
                .otherwise(new Fork<String>()
                    .condition(new Toggle().less(n, +15))
                    .then("Cold")
                    .otherwise(new Fork<String>()
                        .condition(new Toggle().less(n, +30))
                        .then("Warm")
                        .otherwise("Hot")
                        )));
        System.out.println(r.value());
        System.out.println("/let n = +10");
        n.value(10);
        System.out.println(r.value());
        System.out.println("/let n = +20");
        n.value(20);
        System.out.println(r.value());
        System.out.println("/let n = +40");
        n.value(40);
        System.out.println(r.value());
    }
}
