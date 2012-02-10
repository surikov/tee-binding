package tee.binding;

import tee.binding.it.Numeric;
import tee.binding.it.Toggle;
import tee.binding.it.Note;
import tee.binding.it.It;

/**
 * 
 * @author User
 */
public class About {
    /**
     * 
     * @return
     */
    public static String getVersion() {
	return "1.5.2";
    }
    /**
     * 
     * @param args
     */
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
