package lab.task;

import lombok.Getter;

@Getter
public class Processor implements Runnable {

    private static int tickCounter = 0;

    private static final Object lock = new Object(); // Блокировка доступа к tickCounter
    private static final Object tickCompleteLock = new Object(); // Блокировка для ожидания завершения операции по тику

    public static void incrementTickCounter() {
        synchronized (lock) {
            tickCounter++;
            //System.out.println("TICK " + tickCounter);
            lock.notifyAll(); // Уведомляем все потоки, ожидающие изменения tickCounter
        }
    }

    public int getTickCounter() {
        synchronized (lock) {
            return tickCounter;
        }
    }

    public static void waitForTickComplete() throws InterruptedException {
        synchronized (tickCompleteLock) {
            tickCompleteLock.wait(); // Ожидаем завершения операции по тику
        }
    }

    public synchronized int getCurrentTick() {
        return tickCounter;
    }

    @Override
    public void run() {
        while (true) {
            incrementTickCounter(); // Увеличиваем счетчик
            System.out.println("--------------------------------------------------");
            System.out.println("такт #" + tickCounter);
            synchronized (tickCompleteLock) {
                tickCompleteLock.notifyAll(); // Сигнализируем о завершении операции
            }
            try {
                Thread.sleep(100); // Скорость процессора
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    @Override
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep(100);
//                tickCounter++;
//                System.out.println("--------------------------------------------------");
//                System.out.println("такт #" + tickCounter);
//                synchronized (lock) {
//                    notifyAll(); // Уведомляем ожидающие задачи
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}