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
            @Override public void job() {
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
            @Override public void job() {
                retvalue.value(me.value() || fvalue.value());
            }
        });
        return retvalue;
    }
    public Toggle similar(String a, Characters b) {
        return similar(new Characters().value(a), b);
    }
    public Toggle similar(Characters a, String b) {
        return similar(a, new Characters().value(b));
    }
    public Toggle similar(Characters a, Characters b) {
        final Characters aa = a;
        final Characters bb = b;
        final Toggle retvalue = new Toggle().value(a.value().equals(b));
        new Characters().bind(a).afterChange(new Task() {
            @Override public void job() {
                retvalue.value(aa.value().equals(bb.value()));
            }
        });
        new Characters().bind(b).afterChange(new Task() {
            @Override public void job() {
                retvalue.value(aa.value().equals(bb.value()));
            }
        });
        return retvalue;
    }
    //public Toggle equal(Numeric a, Numeric b) {
    //}
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
        Characters aa = new Characters().value("X");
        Characters bb = new Characters().value("Y");
        Toggle cc = new Toggle().similar(aa, bb);
        System.out.println("cc is " + cc.value());
        bb.value("X");
        System.out.println("cc is " + cc.value());
    }
}
