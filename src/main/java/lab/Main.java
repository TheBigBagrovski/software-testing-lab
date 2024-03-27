package lab;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(dispatcher);
    }

}
