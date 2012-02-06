package tee.binding.these;

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

}
