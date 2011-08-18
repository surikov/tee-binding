package tee.binding;

public class TyFork<Kind> extends TyValue<Kind> {

    public TyFork condition(TyBoolean it) {
	return this;
    }
    public TyFork then(TyValue<Kind> it) {
	return this;
    }
    public TyFork otherwise(TyValue<Kind> it) {
	return this;
    }
}
