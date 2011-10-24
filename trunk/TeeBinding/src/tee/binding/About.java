package tee.binding;

public class About {
    public static String getVersion() {
	return "1.3.9";
    }
    public static void main(String[] args) {
	System.out.println("Examples of using");
	It.main(args);
	Calculation.main(args);
	Numeric.main(args);
	Fit.main(args);
	Note.main(args);
	Toggle.main(args);
	Fork.main(args);
//	Column.main(args);
	System.out.println(getVersion());
    }
}
