package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

/**
 *
 * @author User
 */
public class Notes extends These<String> {

    private Note valueNote;
    private Note isNote;

    /**
     *
     */
    public Notes() {
        super();
        valueNote = new Note().bind(super.value());
        isNote = new Note().bind(super.is());
    }

    @Override
    public Note is() {
        return isNote;
    }

    @Override
    public Note value() {
        return valueNote;
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

    @Override
    public Notes select(int nn) {
        super.select(nn);
        return this;
    }
}
