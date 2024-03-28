package lab;

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
            boolean added = (remainingCapacity() > 0) && super.add(task);
            if (added) {
                task.activate();
                System.out.println("В очередь добавлена задача " + task + ". Мест в очереди: " + remainingCapacity());
            } else {
                System.out.println(task + " не добавлена в очередь, достигнут лимит");
            }
            return added;
        }
    }

    @Override
    public Task take() throws InterruptedException {
        return super.take();
    }

    @Override
    public int remainingCapacity() {
        return MAX_CAPACITY - this.size();
    }

}
