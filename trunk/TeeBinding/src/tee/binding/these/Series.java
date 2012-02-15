package tee.binding.these;

import java.util.*;
import tee.binding.view.*;

/**
 *
 * @author User
 */
public class Series {

    private int order = 0;
    protected Vector<These> columns;

    /**
     *
     */
    public Series() {
        columns = new Vector<These>();
    }
/*
    public int size() {
        return columns.size();
    }

    public These column(int nn) {
        return columns.get(nn);
    }*/

    public void order(int n) {
        order = n;
    }

    public int order() {
        return order;
    }

    /**
     *
     * @param nn
     */
    public void select() {

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).select(order);
            //System.out.println(order);
        }
    }

    public void probe() {

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).probe(order);
        }
    }

    /**
     *
     * @param nn
     */
    /*
     * public void drop(int nn) { for (int i = 0; i < columns.size(); i++) {
     * columns.get(i).drop(nn); } }
     */
    /**
     *
     * @param column
     * @return
     */
    public Series field(These column) {
        columns.add(column);
        return this;
    }
}
