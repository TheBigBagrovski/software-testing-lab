package lab.task;

import lab.CustomQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Dispatcher implements Runnable {

    private boolean isRunning = false;

    private final CustomQueue taskQueue = new CustomQueue();
    private final Processor processor;

    private Task currentTask;
    private Future<?> future;
    private final ExecutorService worker = Executors.newSingleThreadExecutor();
    private final List<Task> finishedTasks = new ArrayList<>();
    public Dispatcher(Processor processor) {
        this.processor = processor;
    }

    public void addTask(Task task) {
        synchronized (this) {
            boolean added = taskQueue.add(task);
//        Processor.waitForTickComplete();
            if (added && currentTask != null && task.compareTo(currentTask) < 0) {    // если в очереди есть задача с большим приоритетом
                future.cancel(true);                                   // прерываем текущую
                System.out.println(currentTask + " сменяется, т. к. в очереди есть задача с приоритетом " + task.getPriority() + ". До выполнения осталось " + currentTask.getRequiredTicks() + " тактов.");
//                        currentTask.terminate();
                        taskQueue.add(currentTask);                               // возвращаем прерванную в очередь
                currentTask = null;                                       // снимаем задачу с выполнения, сразу будет выбрана следующая из очереди, выполнение начнется на следующем такте
            }
//        taskQueue.add(task);
        }
    }

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            while (isRunning) {
                try {
//                    System.out.println(taskQueue);
                    Processor.waitForTickComplete();                          // ожидаем такт
//                    System.err.println(currentTask);
                    Thread.sleep(50);
//                    System.err.println(currentTask);
                    if (currentTask == null || currentTask.getRequiredTicks() == 0) { // если задачи нет или закончилась предыдущая
                        if (currentTask != null && currentTask.getCurrentState() == State.SUSPENDED) {                                // если обновляем задачу в связи с завершением предыдущей
//                            currentTask.preempt();
                            finishedTasks.add(currentTask);
                        }
                        //
                        currentTask = taskQueue.take();                           // берем новую задачу
                        System.out.println("Текущая задача: " + currentTask);
//                        currentTask.start();
//                        currentTask.activate();
                        future = worker.submit(currentTask);                      // запускаем выполнение задачи
                        System.out.println("Начато выполнение " + currentTask + " на такте " + processor.getCurrentTick());
                    } /*else if (!taskQueue.isEmpty() && taskQueue.peek().compareTo(currentTask) < 0) {    // если в очереди есть задача с большим приоритетом
                        future.cancel(true);                                   // прерываем текущую
                        System.out.println(currentTask + " сменяется, т. к. в очереди есть задача с приоритетом " + taskQueue.peek().getPriority() + ". До выполнения осталось " + currentTask.getRequiredTicks() + " тактов.");
//                        currentTask.terminate();
//                        taskQueue.add(currentTask);                               // возвращаем прерванную в очередь
                        addTask(currentTask);
                        currentTask = null;                                       // снимаем задачу с выполнения, сразу будет выбрана следующая из очереди, выполнение начнется на следующем такте
                    }*/ else if (currentTask.getCurrentState() == State.WAITING) {
                        future.cancel(true);
                        CompletableFuture.supplyAsync(currentTask::goWaiting).thenAccept(this::addTask);
                        currentTask = null;
                    }
                } catch (InterruptedException e) {
                    System.err.println("DISPATCHER INTERRUPTED");
                }
            }
        }
    }
}