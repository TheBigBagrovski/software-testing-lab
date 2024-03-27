package lab;

import lab.task.Scheduler;
import lab.task.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class Dispatcher implements Runnable {

    private final BlockingQueue<Task> taskQueue;
    private Task currentTask;

    public Dispatcher() {
        taskQueue = new PriorityBlockingQueue<>();
        Processor processor = new Processor();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(processor);
    }

    public void addTask(Task task){
        taskQueue.add(task);
        System.out.println(task);
    }

    @Override
    public void run() {

    }
}
