package tee.binding;

public class Column<Kind> {

    protected It<Kind> value;

    public Column() {
	value=new It<Kind>();
    }
}
