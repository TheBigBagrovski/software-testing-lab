package lab;

import lab.task.Dispatcher;
import lab.task.Processor;
import lab.task.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        Dispatcher dispatcher = new Dispatcher(processor);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(processor);
        ExecutorService eS = Executors.newSingleThreadExecutor();
        eS.execute(dispatcher);

        int id = 0;
        while (true) {
            dispatcher.addTask(TaskGenerator.generateRandomTask(processor, id));
            id++;
            Thread.sleep(1000L);
        }
    }

}
