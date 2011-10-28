package tee.binding.task;

public abstract class Task {
    public void start() {
        doTask();
    }
    public abstract void doTask() ;
}
