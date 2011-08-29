package tee.binding;

public class Toggle extends It<Boolean> {
    @Override public Toggle afterChange(Task newValue) {
        super.afterChange(newValue);
        return this;
    }
    public Toggle and(Toggle value) {
        final Toggle fvalue = value;
        final Toggle me = this;
        final Toggle retvalue = new Toggle().value(value() && fvalue.value());
        new Toggle().bind(fvalue).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(me.value() && fvalue.value());
            }
        });
        return retvalue;
    }
    public Toggle or(Toggle value) {
        final Toggle fvalue = value;
        final Toggle me = this;
        final Toggle retvalue = new Toggle().value(value() || fvalue.value());
        new Toggle().bind(fvalue).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(me.value() || fvalue.value());
            }
        });
        return retvalue;
    }
    public Toggle similar(String a, Note b) {
        return similar(new Note().value(a), b);
    }
    public Toggle similar(Note a, String b) {
        return similar(a, new Note().value(b));
    }
    public Toggle similar(Note a, Note b) {
        final Note aa = a;
        final Note bb = b;
        final Toggle retvalue = new Toggle().value(a.value().equals(b.value()));
        new Note().bind(a).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value().equals(bb.value()));
            }
        });
        new Note().bind(b).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value().equals(bb.value()));
            }
        });
        return retvalue;
    }
    public Toggle equals(double a, Numeric b) {
        return equals(new Numeric().value(a), b);
    }
    public Toggle equals(int a, Numeric b) {
        return equals(new Numeric().value(a), b);
    }
    public Toggle equals(Numeric a, double b) {
        return equals(a, new Numeric().value(b));
    }
    public Toggle equals(Numeric a, int b) {
        return equals(a, new Numeric().value(b));
    }
    public Toggle equals(Numeric a, Numeric b) {
        final Numeric aa = a;
        final Numeric bb = b;
        final Toggle retvalue = new Toggle().value(a.value().equals(b.value()));
        new Numeric().bind(a).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value().equals(bb.value()));
            }
        });
        new Numeric().bind(b).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value().equals(bb.value()));
            }
        });
        return retvalue;
    }
    public Toggle less(double a, Numeric b) {
        return less(new Numeric().value(a), b);
    }
    public Toggle less(int a, Numeric b) {
        return less(new Numeric().value(a), b);
    }
    public Toggle less(Numeric a, double b) {
        return less(a, new Numeric().value(b));
    }
    public Toggle less(Numeric a, int b) {
        return less(a, new Numeric().value(b));
    }
    public Toggle less(Numeric a, Numeric b) {
        final Numeric aa = a;
        final Numeric bb = b;
        final Toggle retvalue = new Toggle().value(a.value() < b.value());
        new Numeric().bind(a).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value() < bb.value());
            }
        });
        new Numeric().bind(b).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value() < bb.value());
            }
        });
        return retvalue;
    }
    public Toggle lessOrEquals(double a, Numeric b) {
        return lessOrEquals(new Numeric().value(a), b);
    }
    public Toggle lessOrEquals(int a, Numeric b) {
        return lessOrEquals(new Numeric().value(a), b);
    }
    public Toggle lessOrEquals(Numeric a, double b) {
        return lessOrEquals(a, new Numeric().value(b));
    }
    public Toggle lessOrEquals(Numeric a, int b) {
        return lessOrEquals(a, new Numeric().value(b));
    }
    public Toggle lessOrEquals(Numeric a, Numeric b) {
        final Numeric aa = a;
        final Numeric bb = b;
        final Toggle retvalue = new Toggle().value(a.value() <= b.value());
        new Numeric().bind(a).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value() <= bb.value());
            }
        });
        new Numeric().bind(b).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value() <= bb.value());
            }
        });
        return retvalue;
    }
    public Toggle more(double a, Numeric b) {
        return less(new Numeric().value(a), b);
    }
    public Toggle more(int a, Numeric b) {
        return less(new Numeric().value(a), b);
    }
    public Toggle more(Numeric a, double b) {
        return less(a, new Numeric().value(b));
    }
    public Toggle more(Numeric a, int b) {
        return less(a, new Numeric().value(b));
    }
    public Toggle more(Numeric a, Numeric b) {
        final Numeric aa = a;
        final Numeric bb = b;
        final Toggle retvalue = new Toggle().value(a.value() > b.value());
        new Numeric().bind(a).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value() > bb.value());
            }
        });
        new Numeric().bind(b).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value() > bb.value());
            }
        });
        return retvalue;
    }
    public Toggle moreOrEquals(double a, Numeric b) {
        return moreOrEquals(new Numeric().value(a), b);
    }
    public Toggle moreOrEquals(int a, Numeric b) {
        return moreOrEquals(new Numeric().value(a), b);
    }
    public Toggle moreOrEquals(Numeric a, double b) {
        return moreOrEquals(a, new Numeric().value(b));
    }
    public Toggle moreOrEquals(Numeric a, int b) {
        return moreOrEquals(a, new Numeric().value(b));
    }
    public Toggle moreOrEquals(Numeric a, Numeric b) {
        final Numeric aa = a;
        final Numeric bb = b;
        final Toggle retvalue = new Toggle().value(a.value() >= b.value());
        new Numeric().bind(a).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value() >= bb.value());
            }
        });
        new Numeric().bind(b).afterChange(new Task() {
            @Override public void doTask() {
                retvalue.value(aa.value() >= bb.value());
            }
        });
        return retvalue;
    }
    @Override public Toggle value(Boolean newValue) {
        super.value(newValue);
        return this;
    }
    public Toggle bind(Toggle it) {
        super.bind(it);
        return this;
    }
    @Override public Toggle bind(It<Boolean> it) {
        super.bind(it);
        return this;
    }
    public static void main(String args[]) {
        System.out.println("\nToggle\n");
        System.out.println("/c = true and b or c");
        Toggle a = new Toggle().value(true);
        Toggle b = new Toggle().value(true);
        Toggle c = new Toggle().value(true).and(b).or(a);
        System.out.println("c is " + c.value());
        System.out.println("/let b = false");
        b.value(false);
        System.out.println("c is " + c.value());
        System.out.println("/let a = false");
        a.value(false);
        System.out.println("c is " + c.value());
        System.out.println("/let cc = aa similar bb");
        Note aa = new Note().value("X");
        Note bb = new Note().value("Y");
        Toggle cc = new Toggle().similar(aa, bb);
        System.out.println("cc is " + cc.value());
        bb.value("X");
        System.out.println("cc is " + cc.value());

        System.out.println("/let c2 = a2 less b2");
        System.out.println("/let a2 = 100, b2 = 200");
        Numeric a2 = new Numeric().value(100);
        Numeric b2 = new Numeric().value(200);
        Toggle c2 = new Toggle().less(a2, b2);
        System.out.println("c2 is " + c2.value());
        System.out.println("/let b2 = 0");
        b2.value(0);
        System.out.println("c2 is " + c2.value());
    }
}
