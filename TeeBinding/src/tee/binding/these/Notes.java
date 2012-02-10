package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

/**
 * 
 * @author User
 */
public class Notes extends These<String> {

    private Note currentNote;

    /**
     * 
     */
    public Notes() {
        super();
        currentNote = new Note().bind(super.value());
    }

    @Override
    public Note value() {
        return currentNote;
    }

    /**
     * 
     * @param it
     * @return
     */
    public Notes value(Note it) {
        super.value(it);
        return this;
    }

    public Notes select(int nn) {
        super.select(nn);
        return this;
    }
}
