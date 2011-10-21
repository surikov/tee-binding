package tee.binding;

public class ColumnNumeric extends Column<Double> {

    protected Numeric value;
     public ColumnNumeric() {
	 value=new Numeric();
    }
}
