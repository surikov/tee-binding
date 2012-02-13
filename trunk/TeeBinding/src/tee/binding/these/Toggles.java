package tee.binding.these;

import java.util.*;
import tee.binding.it.*;
import tee.binding.task.*;

/**
 *
 * @author User
 */
public class Toggles extends These<Boolean> {

    private Toggle valueToggle;
    private Toggle isToggle;

    /**
     *
     */
    public Toggles() {
        super();
        valueToggle = new Toggle().bind(super.current());
        isToggle = new Toggle().bind(super.is());
    }

    @Override
    public Toggle is() {
        return isToggle;
    }

    @Override
    public Toggle current() {
        return valueToggle;
    }

    /**
     *
     * @param it
     * @return
     */
    public Toggles current(Toggle it) {
        super.current(it);
        return this;
    }

    @Override
    public Toggles select(int nn) {
        super.select(nn);
        return this;
    }
}