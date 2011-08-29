package tee.binding;

import java.util.*;

public class LazyTask extends Task {
    private Timer timer = null;
    private int laziness = 50;
    public LazyTask() {
    }
    public LazyTask laziness(int it) {
        laziness = it;
        return this;
    }
    public int laziness() {
        return laziness;
    }
    @Override public void start() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override public void run() {
                doTask();
                timer.cancel();
                timer.purge();
                timer = null;
            }
        }, laziness);
    }
    public static void main(String[] args) {

        System.out.println("\nLazyTask\n");
        final It<Integer> lazy = new It<Integer>().value(-1);
        lazy.afterChange(new LazyTask() {
            @Override public void doTask() {
                System.out.println("[lazy]: someone changed value to " + lazy.value());
            }
        });
        final It<Integer> quick = new It<Integer>().value(-2);
        quick.afterChange(new Task() {
            @Override public void doTask() {
                System.out.println("[quick]: someone changed value to " + quick.value());
            }
        });
        lazy.bind(quick);
        for (int i = 0; i < 5; i++) {
            System.out.println("/i: " + i);
            lazy.value(i);
        }
    }
}
