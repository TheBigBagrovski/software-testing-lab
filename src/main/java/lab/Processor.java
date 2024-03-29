//package lab;
//
//public class Processor implements Runnable {
//
//    public static final long TICK_TIME = 1000L;
//    private int tickCounter = 0;
//    private final Object lock = new Object();
//
//    @Override
//    public void run() {
//        synchronized (lock) {
//            while (true) {
//                // Выполняем действия на каждом такте
//                tickCounter++;
//                System.out.println("Tick " + tickCounter);
//                try {
//                    Thread.sleep(1000); // Подождать 1 секунду между тактами
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                lock.notify(); // Уведомляем поток, который ждет такты
//            }
//        }
//    }
//}
