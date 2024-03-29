package lab;

import lab.task.ExtendedTask;
import lab.task.State;
import lab.task.Task;

import java.util.concurrent.PriorityBlockingQueue;

public class CustomQueue extends PriorityBlockingQueue<Task> {

    private static final int MAX_CAPACITY = 15;

    public CustomQueue() {
        super(MAX_CAPACITY);
    }

    @Override
    public boolean add(Task task) {
        synchronized (this) {
            if (task.getCurrentState() == State.WAITING || remainingCapacity() > 0) {
                if (task.getCurrentState() != State.WAITING) {
                    task.activate();
                } else {
                    task.release();
                }
                super.add(task);
                System.out.println("В очередь добавлена задача " + task + ". Мест в очереди: " + remainingCapacity());
                return true;
            } else {
                System.out.println(task + " не добавлена в очередь, достигнут лимит");
                return false;
            }
        }
    }

    @Override
    public Task take() throws InterruptedException {
        Task t;
        try {
            t = super.take();
        } catch (InterruptedException e) {
            return null;
        }
        return t;
    }

    @Override
    public int remainingCapacity() {
        return MAX_CAPACITY - this.size();
    }

}
