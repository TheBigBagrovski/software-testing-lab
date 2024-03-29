//package lab;
//
//import lab.task.Task;
//import lombok.Getter;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//@Getter
//public class Dispatcher implements Runnable {
//
//    private volatile boolean isRunning = false;
//    //    private int tickCounter = 0;
//    private Future<Task> future;
//    private final ExecutorService mainTaskExecutor = Executors.newSingleThreadExecutor();
//    int ticksLeft;
//    public final CustomQueue taskQueue;
//    private Task currentTask;
//    private final List<Task> completedTasks = new ArrayList<>();
//
//    public Dispatcher() {
//        taskQueue = new CustomQueue();
//    }
//
//    public void addTask(Task task) throws Exception {
//        taskQueue.add(task);
//        if (currentTask == null) {
//            currentTask = taskQueue.take();
////            ticksLeft = currentTask.getTicksToComplete();
//            currentTask.start();
//            System.out.println(currentTask + " стартует");
//            currentTask.call();
//            System.out.println("Начато выполнение задачи " + currentTask);
//            return;
//        }
//        Task topTask = taskQueue.peek();
//        if (!taskQueue.isEmpty() && topTask.compareTo(currentTask) < 0) {
////            currentTask.setTicksToComplete(ticksLeft);
//            System.out.println(currentTask + " сменяется, т. к. в очереди есть задача с приоритетом " + topTask.getPriority() + ". До выполнения осталось " + ticksLeft + " тактов.");
//            currentTask.terminate();
//            currentTask.
//                    System.out.println(currentTask + " уходит в подвешенное состояние");
//            taskQueue.add(currentTask);
//            updateCurrentTask(); // todo() лимит и подвешенное состояние???
//        }
//    }
//
//    public void updateCurrentTask() throws InterruptedException {
////        synchronized (this) {
//        currentTask = taskQueue.take();
////            ticksLeft = currentTask.getTicksToComplete();
//        currentTask.start();
//        System.out.println(currentTask + " стартует");
//        System.out.println("Начато выполнение задачи " + currentTask);
////        }
//    }
//
//    @Override
//    public void run() {
//        if (!isRunning) {
//            isRunning = true;
//            while (isRunning) {
//
//            }
//        }
//    }
//
//
////    public void addTask(Task task) throws InterruptedException {
////        taskQueue.add(task);
////        if (currentTask == null) {
////            currentTask = taskQueue.take();
////            ticksLeft = currentTask.getTicksToComplete();
////            currentTask.start();
////            System.out.println(currentTask + " стартует");
////            System.out.println("Начато выполнение задачи " + currentTask);
////            return;
////        }
////        Task topTask = taskQueue.peek();
////        if (!taskQueue.isEmpty() && topTask.compareTo(currentTask) < 0) {
////            currentTask.setTicksToComplete(ticksLeft);
////            System.out.println(currentTask + " сменяется, т. к. в очереди есть задача с приоритетом " + topTask.getPriority() + ". До выполнения осталось " + ticksLeft + " тактов.");
////            currentTask.terminate();
////            System.out.println(currentTask + " уходит в подвешенное состояние");
////            taskQueue.add(currentTask);
////            updateCurrentTask(); // todo() лимит и подвешенное состояние???
////        }
////    }
////
////    public void updateCurrentTask() throws InterruptedException {
////        synchronized (this) {
////            currentTask = taskQueue.take();
////            ticksLeft = currentTask.getTicksToComplete();
////            currentTask.start();
////            System.out.println(currentTask + " стартует");
////            System.out.println("Начато выполнение задачи " + currentTask);
////        }
////    }
////
////    @Override
////    public void run() {
////        if (!isRunning) {
////            isRunning = true;
////            while (isRunning) {
////                try {
////                    while (currentTask != null) {
////                        while (ticksLeft > 0) {
////                            ticksLeft--;
////                            tickCounter++;
////                            System.out.println("tick #" + tickCounter);
////                            System.out.println("left: " + ticksLeft);
////                            Thread.sleep(1000); // имитируем один такт (1 секунда)
////                        }
////                        if (ticksLeft == 0) {
////                            System.out.println("Задача закончила выполнение после " + currentTask.getTicksToComplete() + " тактов.");
////                            currentTask.preempt();
////                            System.out.println(currentTask + " покидает систему");
////                            completedTasks.add(currentTask);
////                            updateCurrentTask();
////                        }
////                    }
////                } catch (InterruptedException e) {
////                    Thread.currentThread().interrupt();
////                }
////            }
////        }
////    }
//}
