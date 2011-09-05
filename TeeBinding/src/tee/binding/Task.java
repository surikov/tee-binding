package tee.binding;

public abstract class Task {
    public void start() {
        doTask();
    }
    public abstract void doTask() ;
}
