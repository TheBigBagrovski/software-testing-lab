package lab;

import lab.task.Task;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

@Getter
public class Dispatcher implements Runnable {

    private volatile boolean isRunning = false;
    private int tickCounter = 0;
    int ticksLeft;

    public final CustomQueue taskQueue;
    private Task currentTask;

    public Dispatcher() {
        taskQueue = new CustomQueue();
//        Processor processor = new Processor();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(processor);
    }

    public void addTask(Task task) throws InterruptedException {
//        synchronized (this) {
            taskQueue.add(task);
            if (currentTask == null) {
                currentTask = taskQueue.take();
                ticksLeft = currentTask.getTicksToComplete();
                currentTask.start();
                System.out.println("Начато выполнение задачи " + currentTask);
                return;
            }
            Task topTask = taskQueue.peek();
            if (!taskQueue.isEmpty() && topTask.compareTo(currentTask) < 0) {
                System.out.println(currentTask + " сменяется, т. к. в очереди есть задача с приоритетом " + topTask.getPriority());
                currentTask.terminate();
                updateCurrentTask();
            }
//        }
    }

    public void updateCurrentTask() throws InterruptedException {
//        synchronized (this) {
            currentTask = taskQueue.take(); // Получаем задачу из очереди
            currentTask.start();
            ticksLeft = currentTask.getTicksToComplete();
            System.out.println("Начато выполнение задачи " + currentTask);
//        }
    }

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            while (isRunning) {
                try {
                    while (currentTask != null) {
                        synchronized (this) {
                            while (ticksLeft > 0) {
                                ticksLeft--;
                                tickCounter++;
                                System.out.println("tick #" + tickCounter);
                                System.out.println("left: " + ticksLeft);
                                Thread.sleep(1000); // имитируем один такт (1 секунда)
                            }
                            if (ticksLeft == 0) {
                                System.out.println("Task completed after " + currentTask.getTicksToComplete() + " ticks.");
                                updateCurrentTask();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
