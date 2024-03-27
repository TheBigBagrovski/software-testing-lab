package lab;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Dispatcher dispatcher = new Dispatcher();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(dispatcher);

        while (true) {
            dispatcher.addTask(TaskGenerator.generateRandomTask());
            Thread.sleep(3000L);
        }
    }

}
