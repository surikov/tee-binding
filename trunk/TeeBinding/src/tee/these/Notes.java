package tee.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

public class Notes extends These<String> {

    private Note currentNote;

    public Notes() {
	super();
	currentNote = new Note().bind(super.is());
    }

    @Override
    public Note is() {
	return currentNote;
    }

    public Notes is(Note it) {
	super.is(it);
	return this;
    }

    public Notes select(int nn) {
	super.select(nn);
	return this;
    }

    public static void main(String[] a) {
	Note a1 = new Note().value("first");
	Note a2 = new Note().value("second");
	Note a3 = new Note().value("third");
	Notes s = new Notes().is(a1).is(a2).is(a3).select(0);
	System.out.println("--");
	System.out.println(s.is().value());
	s.drop();
	System.out.println(s.is().value());
	s.drop();
	System.out.println(s.is().value());
	s.drop();
	System.out.println(s.is().value());
    }
}
