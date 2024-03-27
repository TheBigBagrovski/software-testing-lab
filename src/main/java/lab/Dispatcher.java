package lab;

import lab.task.Scheduler;
import lab.task.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class Dispatcher implements Runnable {

    private volatile boolean running = false;
    private int tickCounter = 0;

    private final BlockingQueue<Task> taskQueue;
    private Task currentTask;

    public Dispatcher() {
        taskQueue = new PriorityBlockingQueue<>();
//        Processor processor = new Processor();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(processor);
    }

    public void addTask(Task task) {
        synchronized (this) {
            taskQueue.add(task);
            System.out.println("В очередь добавлена задача " + task);
        }
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                currentTask = taskQueue.take(); // Получаем задачу из очереди
                int ticksLeft = currentTask.getTicksToComplete();
                System.out.println("Начато выполнение задачи " + currentTask);
                while (ticksLeft > 0) {
                    Task topTask = taskQueue.peek();
                    if (!taskQueue.isEmpty() && topTask.compareTo(currentTask) < 0) {
                        // Если в очереди есть задача с более высоким приоритетом, прерываем выполнение текущей
                        System.out.println(currentTask + " сменяется, т. к. в очереди есть задача с приоритетом " + topTask.getPriority());
                        taskQueue.add(currentTask); // Возвращаем текущую задачу в очередь
                        break;
                    }
                    Thread.sleep(1000); // Имитируем один такт (1 секунда)
                    ticksLeft--;
                    tickCounter++;
                    System.out.println("tick #" + tickCounter);
                    System.out.println("left: " + ticksLeft);

                }
                if (ticksLeft == 0) {
                    System.out.println("Task completed after " + currentTask.getTicksToComplete() + " ticks.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
