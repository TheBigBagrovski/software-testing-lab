package lab;

import lab.task.Task;

import java.util.concurrent.PriorityBlockingQueue;

public class CustomQueue extends PriorityBlockingQueue<Task> {



    @Override
    public boolean add(Task task) {
        synchronized (this) {
            task.activate();
            super.add(task);
            System.out.println("В очередь добавлена задача " + task);
        }
        return true;
    }

    @Override
    public Task take() throws InterruptedException {
        return super.take();
    }

}
