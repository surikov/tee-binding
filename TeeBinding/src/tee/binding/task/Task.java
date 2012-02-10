package tee.binding.task;

/**
 * 
 * @author User
 */
public abstract class Task {
    /**
     * 
     */
    public void start() {
        doTask();
    }
    /**
     * 
     */
    public abstract void doTask() ;
}
