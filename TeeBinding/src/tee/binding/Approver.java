package tee.binding;

public abstract class Approver<Kind> {
    public abstract boolean approve(Kind it);
}
