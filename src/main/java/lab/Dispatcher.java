package lab;

import lab.task.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher implements Runnable {

    private Task currentTask;

    public Dispatcher() {
        Processor processor = new Processor();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(processor);
    }

    @Override
    public void run() {

    }
}
